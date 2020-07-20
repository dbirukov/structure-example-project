package com.tenengroup.example.job.sharing

import org.scalatest.FlatSpec

class BarTest extends FlatSpec with ExampleTest with Bar {
  "Bar" should behave like valueDefaultTest()
}
