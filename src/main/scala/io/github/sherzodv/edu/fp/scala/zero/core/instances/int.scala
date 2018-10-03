package io.github.sherzodv.edu.fp.scala.zero
package core
package instances

package int {
  trait IntSumMonoid extends Monoid[Int] {
    def empty(): Int = 0
    def combine(x: Int, y: Int): Int = x + y
  }
}

package object int {
  object sum {
    implicit val intSumMonoid: Monoid[Int] = new IntSumMonoid{}
  }
}
