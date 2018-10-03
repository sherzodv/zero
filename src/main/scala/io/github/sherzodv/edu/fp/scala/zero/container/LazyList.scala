package io.github.sherzodv.edu.fp.scala.zero
package container

sealed trait LazyList[+A] extends Iterable[A] {
  def isEmpty: Bool
  def head: A
  def tail: LazyList[A]
  def take(count: Int): LazyList[A]
  def toList: List[A]
}

final case object Nul extends LazyList[Initial] {
  def isEmpty: Bool = true
  def head = !!!
  def tail = Nul
  def take(count: Int) = Nul
  def iterator = new Iterator[Initial] {
    def hasNext: Bool = false
    def next: (Initial, Iterator[Initial]) = !!!
    def toList = Nil
  }
  def toList = Nil
}

final case class LazyCons[A](a: A, next: () => LazyList[A]) extends LazyList[A] {
  self =>
  def isEmpty: Bool = false
  def head = a
  def tail = next()
  def take(count: Int) =
    if (count == 1) {
      LazyCons(a, () => Nul)
    } else {
      LazyCons(a, () => next().take(count - 1))
    }
  def iterator = new Iterator[A] {
    def hasNext: Bool = true
    def next: (A, Iterator[A]) = (a, self.next().iterator)
    def toList: List[A] = {
      @tailrec
      def loop(acc: List[A], tail: Iterator[A]): List[A] = {
        if (tail.hasNext) {
          val (a, i) = tail.next
          loop(a :: acc, i)
        } else {
          acc
        }
      }
      loop(Nil, this).reverse
    }
  }
  def toList = iterator.toList
}

object LazyList {
  def apply[A](): LazyList[A] = Nul
  def empty[A]: LazyList[A] = Nul
  def from(start: Int, step: Int): LazyList[Int] = LazyCons(start, () => from(start + step))
  def from(start: Int): LazyList[Int] = from(start, 1)
}
