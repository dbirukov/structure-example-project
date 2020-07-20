package com.tenengroup.example.job

import org.scalatest.FlatSpec

class AssertionsTest extends FlatSpec {
  "Assertion test" should "run few simple assertions" in {
    val i: Int = 1
    val j: Int = 2
    assert(i != j)
    assert(i == j || i > j)
  }

  it should "assert some unrecognized expressions" in {
    val xs = List(1,2,3)
    assert(None.isDefined)
    assert(xs.exists(i => i > 10))
  }

  it should "provide our own message if in case assert fails" in {
    val i = 1
     assert(i == 2, "value of i should be 1")
  }

  it should "use assertResult to assert complex expressions" in {
    val a = 1
    val b = 2
    val c = 3
    assertResult(4) {
      ((a + c - b) * 10) / 5
    }
  }

  it should "force fail using assertion" in {
      fail("this test is failed because of fail api")
  }

  it should "expect the exception when passing invalid index to charAt function" in {
    val string = "hi"
    assertThrows[IndexOutOfBoundsException] {
      string.charAt(-1)
    }
  }

  it should "expect the exception and catch that exception using intercept" in {
    val string = "hi"
    val exception =
      intercept[IndexOutOfBoundsException] {
        string.charAt(-1)
      }
    assert(exception.getMessage.indexOf("-1") != -1)
  }

  it should "cancel the testcase" in {
    cancel("cancelled testcase because the time taken exceeds the limit")
  }
}