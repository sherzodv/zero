package io.github.sherzodv.edu.fp.scala.zero
package core

trait Semigroup[A] {
  def combine(x: A, y: A): A
}
