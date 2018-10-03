package io.github.sherzodv.edu.fp.scala.zero
package container

sealed trait Either[L, A]

final case class Left[L, A](value: L) extends Either[L, A]
final case class Right[L, A](value: A) extends Either[L, A]

object Either {
  def left[L, A](l: L): Either[L, A] = Left(l)
  def right[L, A](a: A): Either[L, A] = Right(a)
}
