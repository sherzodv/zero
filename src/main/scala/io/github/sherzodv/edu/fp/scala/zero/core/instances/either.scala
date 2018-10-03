package io.github.sherzodv.edu.fp.scala.zero
package core
package instances

package either {
  import container._

  trait EitherMonad[L] extends Monad[Either[L, ?]] {
    def pure[A](a: A): Either[L, A] = Right(a)
    def flatMap[A, B](fa: Either[L, A])(k: A => Either[L, B]): Either[L, B] = fa match {
      case Left(e) => Left(e)
      case Right(a) => k(a)
    }
  }
}

package object either {
  import container._

  implicit def eitherMonad[L]: Monad[Either[L, ?]] = new EitherMonad[L]{}
}
