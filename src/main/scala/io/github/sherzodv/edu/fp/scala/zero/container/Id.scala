package io.github.sherzodv.edu.fp.scala.zero

package container {
}

package object container {
  import core.Monoid
  type Id[A] = A
  def Id[A](a: A): Id[A] = a
  object Id {
    def empty[A]()(implicit monoid: Monoid[A]): Id[A] = monoid.empty
  }
}
