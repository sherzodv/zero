package io.github.sherzodv.edu.fp.scala.zero
package core
package syntax

package object monad {
  implicit class FishOperator[M[_], A, B, C](g: B => M[C]) {
    def >=>(f: A => M[B])(a: A)(implicit monad: Monad[M]) = {
      bind(f(a))(g)
    }
  }
  implicit class BindOperator[M[_], A](ma: M[A]) {
    def >>=[B](f: A => M[B])(implicit monad: Monad[M]) = {
      monad.flatMap(ma)(f)
    }
  }
}
