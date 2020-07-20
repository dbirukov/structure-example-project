package com.tenengroup.example.job

import org.scalatest.{BeforeAndAfterAll, FlatSpec}

class BeforeAndAfterAllTest extends FlatSpec with BeforeAndAfterAll{
  override def beforeAll() {
    println("printing before all the test case")
  }

  override def afterAll() {
    println("printing after all the test case")
  }

  "BeforeAndAfterAll" should "run first test case" in {
    println("1st test case")
  }

  it should "should run second test case" in {
    println("2nd test case")
  }
}
