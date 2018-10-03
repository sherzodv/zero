package io.github.sherzodv.edu.fp.scala.zero
package laws

import core._
import syntax.monad._

trait MonadLaws[M[_]] {
  protected implicit def M: Monad[M]

  def leftIdentity[A, B](f: A => M[B])(a: A): Bool = {
    bind(pure(a))(f) == f(a) &&
    (pure(a) >>= f) == f(a)
  }

  def rightIdentity[A](ma: M[A]): Bool = {
    bind(ma)(pure(_)) == ma &&
    (ma >>= (x => pure(x))) == ma
  }

  def compositionAssociativity[A, B, C](ma: M[A])(g: C => M[B])(f: A => M[C]): Bool = {
    bind(bind(ma)(f))(g) == bind(ma)(x => bind(f(x))(g)) &&
    ((ma >>= f) >>= g) == (ma >>= (x => f(x) >>= g))
  }
}

object MonadLaw {
  def of[M[_]](implicit monad: Monad[M]) = new MonadLaws[M] {
    override protected def M = monad
  }
}
