package io.github.sherzodv.edu.fp.scala.zero
package container

sealed trait Maybe[+A] {
  def isEmpty(): Bool
  def get: A
}

final case class Just[A](a: A) extends Maybe[A] {
  def isEmpty(): Bool = false
  def get: A = a
}

final case object Nothing extends Maybe[Initial] {
  def isEmpty(): Bool = true
  def get = !!!
}

object Maybe {

  def empty[A]: Maybe[A] = Nothing

  def apply[A](a: A): Maybe[A] = 
    if (a == null) {
      Nothing
    } else {
      Just(a)
    }

}
