package io.github.sherzodv.edu.fp.scala.zero
package core

import org.scalatest._

class EitherInstancesSpec extends FlatSpec with Matchers {
  import container._
  import syntax.monad._
  import syntax.either._
  import instances.either._

  "A pure" must "lift value to a Either functor" in {
    pure(2) shouldBe Either.right[String, Int](2)
    pure(0L) shouldBe Either.right[String, Long](0L)
  }

  "A fmap" must "transform Either functor" in {
    Either.right[String, Int](2) map (_ + 2) shouldBe Right(4)
  }

  "A monad join" must "compose nested maybes" in {
    join(Either.right[String, Either[String, Int]](Either.right[String, Int](1))) shouldBe Either.right[String, Int](1)
  }

  "A monad bind" must "fmap Kleisli arrow over the maybe" in {
    def f[A] = (x: Int) => Either.right[String, Int](x * 2)

    bind(Either.left[String, Int]("oops"))(f) shouldBe Left("oops")
    bind(Either.right[String, Int](1))(f) shouldBe Either.right[String, Int](2)

    (Either.left[String, Int]("oops") >>= f) shouldBe Left("oops")
    (Either.right[String, Int](1) >>= f) shouldBe Either.right[String, Int](2)
  }

  "A monad fish" must "compose Kleisli arrows" in {
    val f = (a: Int) => Either.right[String, Int](a + 1)
    val g = (a: Int) => Either.right[String, Int](a * 2)
    fish(g)(f)( 0 ) shouldBe Either.right[String, Int](2)
    (g >=> f)( 0 ) shouldBe Either.right[String, Int](2)
  }
}
