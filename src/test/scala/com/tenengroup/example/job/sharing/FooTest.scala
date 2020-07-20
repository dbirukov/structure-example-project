package com.tenengroup.example.job.sharing

import org.scalatest.FlatSpec

class FooTest extends FlatSpec with ExampleTest with Foo {
  "Foo" should behave like valueDefaultTest()
}
