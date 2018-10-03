package io.github.sherzodv.edu.fp.scala.zero
package laws

import core.fmap
import core.Functor

trait FunctorLaws[F[_]] {
  protected implicit def F: Functor[F]

  def preserveIdentity[A](fa: F[A]): Bool = {
    fmap(fa)(identity) == fa
  }

  def preserveComposition[A, B, C](fa: F[A])(g: B => C)(f: A => B): Bool = {
    fmap(fmap(fa)(f))(g) == fmap(fa)(f andThen g)
  }
}

object FunctorLaw {
  def of[F[_]](implicit functor: Functor[F]) = new FunctorLaws[F] {
    def F = functor
  }
}
