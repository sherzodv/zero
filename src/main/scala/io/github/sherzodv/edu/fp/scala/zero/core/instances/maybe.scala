package io.github.sherzodv.edu.fp.scala.zero
package core
package instances

package maybe {
  import container._

  trait MaybeMonoid[A] extends Semigroup[Maybe[A]] with Monoid[Maybe[A]] {
    protected implicit def semigroupA: Semigroup[A]
    def empty(): Maybe[A] = Nothing
    def combine(mx: Maybe[A], my: Maybe[A]): Maybe[A] = mx match {
      case Nothing => my
      case Just(x) => my match {
        case Nothing => mx
        case Just(y) => Just(semigroupA.combine(x, y))
      }
    }
  }

  trait MaybeMonad extends Monad[Maybe] with Foldable[Maybe] {
    def pure[A](a: A): Maybe[A] = if (a == null) Nothing else Just(a)
    def flatMap[A, B](fa: Maybe[A])(k: A => Maybe[B]): Maybe[B] = fa match {
      case Nothing => Nothing
      case Just(a) => k(a)
    }
    def foldLeft[A, B](fa: Maybe[A])(b: B)(f: (B, A) => B): B = fa match {
      case Nothing => b
      case Just(a) => f(b, a)
    }
  }
}

package object maybe {
  import container._

  implicit def maybeMonoid[A](implicit sa: Semigroup[A]): Semigroup[Maybe[A]] with Monoid[Maybe[A]] = new MaybeMonoid[A]{
    override def semigroupA: Semigroup[A] = sa
  }

  implicit val maybeMonad: Monad[Maybe] = new MaybeMonad{}
}
