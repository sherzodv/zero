package io.github.sherzodv.edu.fp.scala.zero
package core
package instances

package list {
  import container._

  trait ListMonoid[A] extends Monoid[List[A]] {
    def empty(): List[A] = Nil
    def combine(sm: List[A], lg: List[A]): List[A] = {
      @tailrec
      def loop(acc: List[A], small: List[A]): List[A] = small match {
        case Nil => acc
        case h :: tail => loop(h :: acc, tail)
      }
      loop(lg, sm.reverse)
    }
  }

  trait ListMonad extends Monad[List] with Foldable[List] {
    def pure[A](a: A): List[A] = a :: Nil
    def flatMap[A, B](fa: List[A])(k: A => List[B]): List[B] = {
      @tailrec
      def loop(inc: List[B], dec: List[A]): List[B] = dec match {
        case Nil => inc
        case h :: tail => loop(combine(k(h), inc), tail)
      }
      loop(Nil, fa).reverse
    }
    def foldLeft[A, B](fa: List[A])(b: B)(f: (B, A) => B): B = fa match {
      case Nil => b
      case h :: tail => foldLeft(tail)(f(b, h))(f)
    }
  }
}

package object list {
  import container._
  implicit def listMonoid[A]: Monoid[List[A]] = new ListMonoid[A]{}
  implicit val listMonad: Monad[List] = new ListMonad{}
}
