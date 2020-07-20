package com.tenengroup.example.job

import java.io.File

import org.scalactic.StringNormalizations._
import org.scalatest.{FlatSpec, Matchers}

class MatchersTest extends FlatSpec with Matchers {
  "Matchers" should "use different ways of asserting using matchers" in {
    val result = 3
    result should be(3)
    result should ===(3)
    result shouldEqual 3
    result shouldBe 3
    2 shouldEqual 3 +- 1
  }

  it should "test array equality" in {
    Array(1, 2) should equal(Array(1, 2))
  }

  it should "check if the strings are equal without considering case" in {
    "HI" should equal("Hi")(after being lowerCased)
  }

  it should "check the length of the list" in {
    val list = List(1, 2, 3)
    list should have length 3
    list should have size 3
  }

  it should "do some of the special matchers" in {
    val tmp = File.createTempFile("hello", "world")
    tmp should be a 'file
  }

  it should "check the class of an object" in {
    case class Employee(name: String)
    val employee = Employee("Jane")
    employee shouldBe a[Employee]
  }

  it should "the values of a custom object" in {
    case class Book(title: String, author: String)
    val book = Book("SomeBook", "Jane")
    book should have(
      'title("SomeBook"),
      'author("Jane")
    )
  }

  it should "the values of a custom object, only subset of the object's properties" in {
    case class Book(title: String, author: String, pages: Int)
    val book = Book("SomeBook", "Jane", 512)
    book should have(
      'title("SomeBook"),
      'author("Jane")
    )
  }

  it should "the values of a custom object, fail" in {
    case class Book(title: String, author: String, pages: Int)
    val book = Book("SomeBook", "Jane", 512)
    book should have(
      'title("SomeBook"),
      'author("John")
    )
  }

  it should "the values of a custom object in comparison with case class" in {
    case class Book(title: String, author: String)
    val book = Book("SomeBook", "Jane")
    book should be(
      Book("SomeBook", "John")
    )
  }

}
