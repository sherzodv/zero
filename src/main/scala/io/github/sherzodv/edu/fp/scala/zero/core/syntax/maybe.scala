package io.github.sherzodv.edu.fp.scala.zero
package core
package syntax

package object maybe {
  import container.Maybe
  implicit class MaybeFunctorOps[A](fa: Maybe[A]) {
    def map[B](f: A => B)(implicit functor: Functor[Maybe]) = fmap(fa)(f)
    def >>=[B](f: A => Maybe[B])(implicit monad: Monad[Maybe]) = {
      monad.flatMap(fa)(f)
    }
  }
}
