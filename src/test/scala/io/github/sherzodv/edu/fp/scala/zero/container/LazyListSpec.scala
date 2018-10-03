package io.github.sherzodv.edu.fp.scala.zero
package container

import org.scalatest._

class LazyListSpec extends FlatSpec with Matchers {

  "A LazyList" must "be constructible" in {
    (Nul) shouldBe LazyList()
    (Nul) shouldBe LazyList.empty[Int]
  }

  "A from method" must "construct infinite list" in {
    LazyList.from(1).take(5).toList shouldBe List(1, 2, 3, 4, 5)
    val list = LazyList.from(1).take(500).toList

    list.size shouldBe 500
    list.head shouldBe 1
    list.last shouldBe 500
  }

  "A from method" must "construct infinite list and take without stack overflow" in {
    val list = LazyList.from(1).take(1000000).toList

    list.size shouldBe 1000000
    list.head shouldBe 1
    list.last shouldBe 1000000
  }

}
