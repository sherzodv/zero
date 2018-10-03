package io.github.sherzodv.edu.fp.scala.zero
package core

trait Foldable[F[_]] {
  def foldLeft[A, B](fa: F[A])(b: B)(f: (B, A) => B): B
  def fold[A](fa: F[A])(implicit monoid: Monoid[A]): A = {
    foldLeft(fa)(monoid.empty)(monoid.combine)
  }
}
