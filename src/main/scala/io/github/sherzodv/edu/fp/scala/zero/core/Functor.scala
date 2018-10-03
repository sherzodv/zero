package io.github.sherzodv.edu.fp.scala.zero
package core

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}
