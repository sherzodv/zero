package io.github.sherzodv.edu.fp.scala.zero
package core
package syntax

package object list {
  import container.List
  implicit class ListOps[A](fa: List[A]) {
    def pure(a: A)(implicit applicative: Applicative[List]) = applicative.pure(a)
    def map[B](f: A => B)(implicit functor: Functor[List]) = fmap(fa)(f)
    def flatMap[B](f: A => List[B])(implicit monad: Monad[List]) = bind(fa)(f)
    def >>=[B](f: A => List[B])(implicit monad: Monad[List]) = {
      monad.flatMap(fa)(f)
    }
  }
}
