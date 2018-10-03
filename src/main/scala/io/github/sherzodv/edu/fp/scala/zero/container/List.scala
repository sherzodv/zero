package io.github.sherzodv.edu.fp.scala.zero
package container

sealed trait List[+A] extends Iterable[A] {
  def isEmpty(): Bool
  def head(): A
  def last(): A
  def tail(): List[A]
  def size(): Long
  def reverse(): List[A]
}

final case object Nil extends List[Initial] {
  def isEmpty(): Bool = true
  def head() = !!!
  def last() = !!!
  def tail() = Nil
  def size() = 0
  def reverse() = Nil

  def iterator: Iterator[Initial] = new Iterator[Initial] {
    def hasNext: Bool = false
    def next: (Initial, Iterator[Initial]) = !!!
    def toList: List[Initial] = Nil
  }
}

final case class Cons[A](a: A, next: List[A]) extends List[A] {
  self =>
  def isEmpty(): Bool = false
  def head() = a
  def last() = {
    @tailrec
    def loop(a: A, list: List[A]): A = list match {
      case Nil => a
      case h :: tail => loop(h, tail)
    }
    loop(a, next)
  }
  def tail() = next
  def size() = {
    @tailrec
    def loop(sz: Long, list: List[A]): Long = list match {
      case Nil => sz
      case _ :: tail => loop(sz + 1, tail)
    }
    loop(0, this)
  }
  def reverse() = {
    @tailrec
    def loop(inc: List[A], dec: List[A]): List[A] = dec match {
      case Nil => inc
      case h :: tail => loop(h :: inc, tail)
    }
    loop(Nil, this)
  }

  def iterator: Iterator[A] = new Iterator[A] {
    def hasNext: Bool = true
    def next: (A, Iterator[A]) = (a, self.next.iterator)
    def toList: List[A] = self
  }
}

object :: {
  def unapply[A](xs: List[A]): Maybe[(A, List[A])] = {
    if (xs.isEmpty) {
      Nothing
    } else {
      Maybe((xs.head, xs.tail))
    }
  }
}

object List {
  def empty[A]: List[A] = Nil

  def apply[A](a: A*): List[A] = {
    def mklist(i: Int, a: Seq[A], l: List[A]): List[A] = {
      if (i >= a.length) {
        l
      } else {
        a(i) :: mklist(i + 1, a, l)
      }
    }
    mklist(0, a, Nil)
  }

  implicit sealed class ConsList[A](l: List[A]) {
    def ::(a: A): List[A] = Cons(a, l)
    def :+(a: A): List[A] = (a :: l.reverse).reverse
  }
}
