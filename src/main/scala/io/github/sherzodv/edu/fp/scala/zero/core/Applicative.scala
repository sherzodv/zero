package io.github.sherzodv.edu.fp.scala.zero
package core

trait Applicative[F[_]] extends Apply[F] {
  def pure[A](a: A): F[A]
}
