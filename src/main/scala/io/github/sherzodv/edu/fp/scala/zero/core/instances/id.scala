package io.github.sherzodv.edu.fp.scala.zero
package core
package instances

package id {
  import container._

  trait IdSemigroup[A] extends Semigroup[Id[A]] {
    protected implicit def semigroupA: Semigroup[A]
    def combine(mx: Id[A], my: Id[A]): Id[A] = semigroupA.combine(mx, my)
  }

  trait IdMonoid[A] extends IdSemigroup[A] with Monoid[Id[A]] {
    protected implicit def monoidA: Monoid[A]
    def empty(): Id[A] = monoidA.empty
  }

  trait IdMonad extends Monad[Id] with Foldable[Id] {
    def pure[A](a: A): Id[A] = Id(a)
    def flatMap[A, B](fa: Id[A])(k: A => Id[B]): Id[B] = k(fa)
    def foldLeft[A, B](fa: Id[A])(b: B)(f: (B, A) => B): B = f(b, fa)
  }
}

package object id {
  import container._

  implicit def idSemigroup[A](implicit sa: Semigroup[A]): Semigroup[Id[A]] = new IdSemigroup[A]{
    override def semigroupA: Semigroup[A] = sa
  }

  implicit def idMonoid[A](implicit ma: Monoid[A], sa: Semigroup[A]): Monoid[Id[A]] = new IdMonoid[A]{
    override def semigroupA: Semigroup[A] = sa
    override def monoidA: Monoid[A] = ma
  }

  implicit val idMonad: Monad[Id] with Foldable[Id] = new IdMonad{}
}
