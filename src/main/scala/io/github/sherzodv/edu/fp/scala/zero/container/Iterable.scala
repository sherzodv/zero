package io.github.sherzodv.edu.fp.scala.zero
package container

trait Iterable[+A] {
  def iterator: Iterator[A]
}
