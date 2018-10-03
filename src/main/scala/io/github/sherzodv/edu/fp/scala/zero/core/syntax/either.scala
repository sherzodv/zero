package io.github.sherzodv.edu.fp.scala.zero
package core
package syntax

package object either {
  import container.Either
  implicit class EitherOps[L, A](fa: Either[L, A]) {
    def pure(a: A)(implicit applicative: Applicative[Either[L, ?]]) = applicative.pure(a)
    def map[B](f: A => B)(implicit functor: Functor[Either[L, ?]]) = functor.map(fa)(f)
    def flatMap[B](f: A => Either[L, B])(implicit monad: Monad[Either[L, ?]]) = monad.flatMap(fa)(f)
    def >>=[B](f: A => Either[L, B])(implicit monad: Monad[Either[L, ?]]) = monad.flatMap(fa)(f)
  }
}
