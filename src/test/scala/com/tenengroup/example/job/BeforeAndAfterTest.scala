package com.tenengroup.example.job

import org.scalatest.{BeforeAndAfterEach, FlatSpec}

import scala.collection.mutable.ListBuffer

class BeforeAndAfterTest extends FlatSpec with BeforeAndAfterEach {
  val builder = new StringBuilder
  val buffer = new ListBuffer[String]

  override def beforeEach() {
    builder.append("ScalaTest is ")
  }

  override def afterEach() {
    builder.clear()
    buffer.clear()
  }

  "BeforeAndAfterTest" should "test the builder value" in {
    builder.append("easy!")
    assert(builder.toString === "ScalaTest is easy!")
    assert(buffer.isEmpty)
    buffer += "sweet"
  }

  it should "test the builder and buffer value again" in {
    builder.append("fun!")
    assert(builder.toString === "ScalaTest is fun!")
    assert(buffer.isEmpty)
    buffer += "clear"
  }
}
