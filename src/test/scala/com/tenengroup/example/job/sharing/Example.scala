package com.tenengroup.example.job.sharing

trait Example {
  def someValue(): Int = 42
}

trait Foo extends Example {
  override def toString: String = "Foo"
}

trait Bar extends Example {
  override def toString: String = "Bar"
}

trait Baz extends Example {

  override def someValue(): Int = 16

  override def toString: String = "Bar"
}

