package com.tenengroup.example.job

import org.scalatest.FlatSpec

//@Ignore
class IgnoreTest extends FlatSpec {

  "ignore" should "ignore the test case" ignore {
    assert(1===2)
  }

  it should "be ignored only when classLevel Ignore is specified" in {
    assert(1!=2)
  }
}
