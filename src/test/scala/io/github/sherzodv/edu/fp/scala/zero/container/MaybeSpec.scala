package io.github.sherzodv.edu.fp.scala.zero
package container

import org.scalatest._

class MaybeSpec extends FlatSpec with Matchers {

  "A Maybe" must "be constructible" in {
    (Maybe(1)) shouldBe Just(1)
    (Maybe(null)) shouldBe Nothing
  }

}
