package io.github.sherzodv.edu.fp.scala.zero
package core

trait Monoid[A] extends Semigroup[A] {
  def empty(): A
}
