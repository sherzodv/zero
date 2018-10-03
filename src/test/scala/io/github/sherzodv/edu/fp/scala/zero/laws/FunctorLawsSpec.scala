package io.github.sherzodv.edu.fp.scala.zero
package core

import org.scalatest._

class FunctorLawsSpec extends FlatSpec with Matchers {
  import container._
  import instances.id._
  import instances.list._
  import instances.maybe._
  import instances.int.sum._
  import laws.FunctorLaw

  val emptyList = List.empty[Int]
  val nonEmptyList = 1 :: 2 :: Nil

  val emptyMaybe = Maybe.empty[Int]
  val nonEmptyMaybe = Maybe(1)

  val emptyId = Id.empty[Int]
  val nonEmptyId = Id(1)

  def f = (x: Int) => x + 1
  def g = (x: Int) => x + 2

  "Identity law" must "hold for lists" in {
    FunctorLaw.of[List].preserveIdentity(emptyList) shouldBe true
    FunctorLaw.of[List].preserveIdentity(nonEmptyList) shouldBe true
  }

  "Composition law" must "hold for lists" in {
    FunctorLaw.of[List].preserveComposition(emptyList)(g)(f) shouldBe true
    FunctorLaw.of[List].preserveComposition(nonEmptyList)(g)(f) shouldBe true
  }

  "Identity law" must "hold for maybes" in {
    FunctorLaw.of[Maybe].preserveIdentity(emptyMaybe) shouldBe true
    FunctorLaw.of[Maybe].preserveIdentity(nonEmptyMaybe) shouldBe true
  }

  "Composition law" must "hold for maybes" in {
    FunctorLaw.of[Maybe].preserveComposition(emptyMaybe)(g)(f) shouldBe true
    FunctorLaw.of[Maybe].preserveComposition(nonEmptyMaybe)(g)(f) shouldBe true
  }

  "Identity law" must "hold for ids" in {
    FunctorLaw.of[Id].preserveIdentity(emptyId) shouldBe true
    FunctorLaw.of[Id].preserveIdentity(nonEmptyId) shouldBe true
  }

  "Composition law" must "hold for ids" in {
    FunctorLaw.of[Id].preserveComposition(emptyId)(g)(f) shouldBe true
    FunctorLaw.of[Id].preserveComposition(nonEmptyId)(g)(f) shouldBe true
  }
}
