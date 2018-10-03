package io.github.sherzodv.edu.fp.scala

package object zero {
  type Int = scala.Int
  type Long = scala.Long
  type Double = scala.Double
  type String = java.lang.String
  type Seq[A] = scala.Seq[A]
  type Array[A] = scala.Array[A]
  type Initial = scala.Nothing
  type Bool = scala.Boolean
  type tailrec = scala.annotation.tailrec
  def ??? = throw new scala.NotImplementedError
  def !!! = throw new java.util.NoSuchElementException

  def identity[A](a: A) = a
  def implicitly[A](implicit a: A) = a
}

package zero {
  sealed trait Unit
  final case object Terminal extends Unit
}
