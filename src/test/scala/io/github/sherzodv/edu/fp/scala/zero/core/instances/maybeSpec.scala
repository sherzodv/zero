package io.github.sherzodv.edu.fp.scala.zero
package core

import org.scalatest._

class MaybeInstancesSpec extends FlatSpec with Matchers {

  import container._
  import syntax.monad._
  import instances.maybe._

  "A pure" must "lift value to a Maybe functor" in {
    pure(2) shouldBe Maybe(2)
    pure(0L) shouldBe Maybe(0L)
  }

  "A fmap" must "transform Maybe functor" in {
    fmap( Maybe.empty[Int] )(_ + 2) shouldBe Nothing
    fmap( Maybe(1) )(_ + 2) shouldBe Maybe(3)
  }

  "A monad join" must "compose nested maybes" in {
    join(Maybe.empty[Maybe[Int]]) shouldBe Nothing
    join(Maybe(Maybe(1))) shouldBe Maybe(1)
  }

  "A monad bind" must "fmap Kleisli arrow over the maybe" in {
    def f[A] = (x: Int) => Maybe(x * 2)

    bind(Maybe.empty[Int])(f) shouldBe Nothing
    bind(Maybe(1))(f) shouldBe Maybe(2)

    (Maybe.empty[Int] >>= f) shouldBe Nothing
    (Maybe(1) >>= f) shouldBe Maybe(2)
  }

  "A monad fish" must "compose Kleisli arrows" in {
    val f = (a: Int) => Maybe(a + 1)
    val g = (a: Int) => Maybe(a * 2)
    fish(g)(f)( 0 ) shouldBe Maybe(2)
    (g >=> f)( 0 ) shouldBe Maybe(2)
  }

}
