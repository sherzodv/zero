package io.github.sherzodv.edu.fp.scala.zero
package core

trait Apply[F[_]] extends Functor[F] {
  def ap[A, B](fa: F[A])(ff: F[A => B]): F[B]
}
