package io.github.sherzodv.edu.fp.scala.zero
package core

import org.scalatest._

class MonadLawsSpec extends FlatSpec with Matchers {
  import container._
  import instances.id._
  import instances.list._
  import instances.maybe._
  import instances.int.sum._
  import laws.MonadLaw

  val emptyList = List.empty[Int]
  val nonEmptyList = 1 :: 2 :: Nil

  val emptyMaybe = Maybe.empty[Int]
  val nonEmptyMaybe = Maybe(1)

  val emptyId = Id.empty[Int]
  val nonEmptyId = Id(1)

  def lf = (x: Int) => List(x + 1)
  def lg = (x: Int) => List(x + 2)

  def mf = (x: Int) => Maybe(x + 1)
  def mg = (x: Int) => Maybe(x + 2)

  def idf = (x: Int) => Id(x + 1)
  def idg = (x: Int) => Id(x + 2)

  "Left identity law" must "hold for lists" in {
    MonadLaw.of[List].leftIdentity(lf)(1) shouldBe true
    MonadLaw.of[List].leftIdentity(lf)(1) shouldBe true
  }

  "Right identity law" must "hold for lists" in {
    MonadLaw.of[List].rightIdentity(emptyList) shouldBe true
    MonadLaw.of[List].rightIdentity(nonEmptyList) shouldBe true
  }

  "Composition associativity law" must "hold for lists" in {
    MonadLaw.of[List].compositionAssociativity(emptyList)(lg)(lf) shouldBe true
    MonadLaw.of[List].compositionAssociativity(nonEmptyList)(lg)(lf) shouldBe true
  }

  "Left identity law" must "hold for maybes" in {
    MonadLaw.of[Maybe].leftIdentity(mf)(1) shouldBe true
    MonadLaw.of[Maybe].leftIdentity(mf)(1) shouldBe true
  }

  "Right identity law" must "hold for maybes" in {
    MonadLaw.of[Maybe].rightIdentity(emptyMaybe) shouldBe true
    MonadLaw.of[Maybe].rightIdentity(nonEmptyMaybe) shouldBe true
  }

  "Composition associativity law" must "hold for maybes" in {
    MonadLaw.of[Maybe].compositionAssociativity(emptyMaybe)(mg)(mf) shouldBe true
    MonadLaw.of[Maybe].compositionAssociativity(nonEmptyMaybe)(mg)(mf) shouldBe true
  }

  "Left identity law" must "hold for ids" in {
    MonadLaw.of[Id].leftIdentity(idf)(1) shouldBe true
    MonadLaw.of[Id].leftIdentity(idf)(1) shouldBe true
  }

  "Right identity law" must "hold for ids" in {
    MonadLaw.of[Id].rightIdentity(emptyId) shouldBe true
    MonadLaw.of[Id].rightIdentity(nonEmptyId) shouldBe true
  }

  "Composition associativity law" must "hold for ids" in {
    MonadLaw.of[Id].compositionAssociativity(emptyId)(idg)(idf) shouldBe true
    MonadLaw.of[Id].compositionAssociativity(nonEmptyId)(idg)(idf) shouldBe true
  }
}
