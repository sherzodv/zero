package io.github.sherzodv.edu.fp.scala.zero
package core

import org.scalatest._

class ListInstancesSpec extends FlatSpec with Matchers {

  import container._
  import syntax.monad._
  import instances.list._

  "A semigroup" must "concatenate two lists" in {
    combine( List.empty[Int], Nil ) shouldBe List()
    combine( Nil, 1 :: 2 :: Nil ) shouldBe List(1, 2)
    combine( 1 :: 2 :: Nil, Nil ) shouldBe List(1, 2)
    combine( 1 :: 2 :: Nil, 3 :: 4 :: Nil ) shouldBe List(1, 2, 3, 4)
  }

  "A monoid" must "be able to produce empty list" in {
    mempty[List[Int]] shouldBe List()
  }

  "A pure" must "lift value to a List functor" in {
    pure(2) shouldBe List(2)
    pure(0L) shouldBe List(0L)
  }

  "A fmap" must "transform List functor" in {
    fmap( List.empty[Int] )(_ + 2) shouldBe List()
    fmap( 1 :: Nil )(_ + 2) shouldBe List(3)
    fmap( 1 :: 2 :: Nil )(_ + 2) shouldBe List(3, 4)
    fmap( 1 :: 2 :: 3 :: Nil )(_ + 2) shouldBe List(3, 4, 5)
  }

  "A fmap" must "transform large List without SO" in {
    val l = fmap( LazyList.from(1).take(100000).toList )(_ + 1)
    l.head shouldBe 2
    l.size shouldBe 100000
    l.last shouldBe 100001
  }

  "A monad join" must "compose nested lists" in {
    join(List.empty[List[Int]]) shouldBe List()
    join(List(1) :: List(2) :: Nil) shouldBe List(1, 2)
  }

  "A monad bind" must "fmap Kleisli arrow over the list" in {
    def f[A] = (x: A) => List(x, x)

    bind(List.empty[Int])(f) shouldBe List()
    bind(1 :: 2 :: 3 :: Nil)(f) shouldBe List(1, 1, 2, 2, 3, 3)

    (List.empty[Int] >>= f) shouldBe List()
    (1 :: 2 :: 3 :: Nil >>= f) shouldBe List(1, 1, 2, 2, 3, 3)
  }

  "A monad fish" must "compose Kleisli arrows" in {
    val f = (a: Int) => List(a + 1, a + 2)
    val g = (a: Int) => List(a * 2, a * 2)
    (g >=> f)( 0 ) shouldBe List(2, 2, 4, 4)
    fish(g)(f)( 0 ) shouldBe List(2, 2, 4, 4)
  }

}
