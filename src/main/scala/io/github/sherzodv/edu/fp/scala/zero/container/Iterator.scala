package io.github.sherzodv.edu.fp.scala.zero
package container

trait Iterator[+A] {
  def hasNext: Bool
  def next: (A, Iterator[A])
  def toList: List[A]
  // def toLazyList: LazyList[A]
}
