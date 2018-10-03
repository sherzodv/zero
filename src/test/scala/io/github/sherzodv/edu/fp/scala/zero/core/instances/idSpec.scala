package io.github.sherzodv.edu.fp.scala.zero
package core

import org.scalatest._

class IdInstancesSpec extends FlatSpec with Matchers {

  import container._
  import syntax.monad._
  import instances.id._
  import instances.int.sum._

  "A semigroup" must "concatenate two lists" in {
    combine( Id(1), Id(2) ) shouldBe Id(3)
  }

  "A monoid" must "be able to produce empty Id" in {
    mempty[Id[Int]] shouldBe Id(0)
  }

  "A pure" must "lift value to a Id functor" in {
    pure(2) shouldBe Id(2)
    pure(0L) shouldBe Id(0L)
  }

  "A fmap" must "transform Id functor" in {
    fmap( Id(1) )(_ + 2) shouldBe Id(3)
  }

  "A monad join" must "compose nested lists" in {
    join(Id.empty[Id[Int]]) shouldBe Id(0)
    join(Id(Id(1))) shouldBe Id(1)
  }

  "A monad bind" must "fmap Kleisli arrow over the list" in {
    def f[A] = (x: Int) => Id(x + 2)

    bind(Id.empty[Int])(f) shouldBe Id(2)
    bind(Id(1))(f) shouldBe Id(3)

    (Id.empty[Int] >>= f) shouldBe Id(2)
    (Id(1) >>= f) shouldBe Id(3)
  }

  "A monad fish" must "compose Kleisli arrows" in {
    val f = (a: Int) => Id(a + 1)
    val g = (a: Int) => Id(a * 2)
    (g >=> f)( 0 ) shouldBe Id(2)
    fish(g)(f)( 0 ) shouldBe Id(2)
  }

}
