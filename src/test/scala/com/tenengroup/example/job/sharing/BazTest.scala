package com.tenengroup.example.job.sharing

import org.scalatest.FlatSpec

class BazTest extends FlatSpec with ExampleTest with Baz {
  "Baz" should behave like valueDefaultTest()
}
