package io.github.sherzodv.edu.fp.scala.zero
package container

import org.scalatest._

class ListSpec extends FlatSpec with Matchers {

  "A ConsList" must "be constructible" in {
    (Nil) shouldBe List()
    (Nil) shouldBe List.empty[Int]
    (1 :: Nil) shouldBe List(1)
    (1 :: 2 :: Nil ) shouldBe List(1, 2)
    (1 :: 2 :: 3 :: Nil) shouldBe List(1, 2, 3)
  }

  "A ConsList" must "be able to return last element" in {
    List(1).last shouldBe 1
    List(1, 2).last shouldBe 2
    List(1, 2, 3).last shouldBe 3
    List(1, 2, 3, 4).last shouldBe 4
  }

  "A ConsList" must "be reversible" in {
    Nil.reverse shouldBe Nil
    List(1).reverse shouldBe List(1)
    List(1, 2).reverse shouldBe List(2, 1)
    List(1, 2, 3).reverse shouldBe List(3, 2, 1)
  }

  "A ConsList" must "be appendable" in {
    Nil :+ 1 shouldBe List(1)
    List(1) :+ 2 shouldBe List(1, 2)
    List(1, 2) :+ 3 shouldBe List(1, 2, 3)
    List(1, 2, 3) :+ 4 shouldBe List(1, 2, 3, 4)
  }

}
