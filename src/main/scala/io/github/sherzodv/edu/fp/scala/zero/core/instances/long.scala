package io.github.sherzodv.edu.fp.scala.zero
package core
package instances

package long {
  trait LongSumMonoid extends Monoid[Long] {
    def empty(): Long = 0L
    def combine(x: Long, y: Long): Long = x + y
  }
}

package object long {
  object sum {
    implicit val longSumMonoid: Monoid[Long] = new LongSumMonoid{}
  }
}
