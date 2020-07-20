package com.tenengroup.example.job.sharing

import org.scalatest.{FlatSpec, Matchers}

trait ExampleTest extends FlatSpec with Example with Matchers {

  def valueDefaultTest(): Unit = {
    it should "check that value eq 42" in {
      someValue() should be(42)
    }
  }

}
