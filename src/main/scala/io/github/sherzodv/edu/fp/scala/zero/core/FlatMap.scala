package io.github.sherzodv.edu.fp.scala.zero
package core

trait FlatMap[F[_]] extends Apply[F] {
  def ap[A, B](fa: F[A])(ff: F[A => B]): F[B] = flatMap(ff)(f => map(fa)(f))
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  def flatten[A](ffa: F[F[A]]): F[A] = {
    flatMap(ffa)(identity)
  }
}

trait AlterFlatMap[F[_]] extends Apply[F] {
  def ap[A, B](fa: F[A])(ff: F[A => B]): F[B] = flatMap(ff)(f => map(fa)(f))
  def flatten[A](ffa: F[F[A]]): F[A]
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B] = {
    flatten(map(fa)(f))
  }
}
