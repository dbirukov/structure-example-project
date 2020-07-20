package com.tenengroup.example.job

import org.apache.spark.sql.types.StringType
import org.scalatest.{FlatSpecLike, Matchers}

class UniqueNamesJobOpsSpec
  extends FlatSpecLike
  with Matchers
  with SparkSessionTestWrapper
  with UniqueNamesJobOps {
  import testSpark.implicits._

  "unique_name ops" should "select only name col from casts df" in {
    val df = List(
      ("test1", "test2", "test3"),
      ("test11", "test21", "test31")
    ).toDF("test_col", "name", "test_extra_col")

    val resultDF = df.transform(selectCatsNames)

    resultDF.schema.fields.length should be(1)
    assertField("name", StringType, resultDF.schema)
    resultDF.where($"name" === "test2").count() should be(2)
    resultDF.where($"name" === "test21").count() should be(1)
  }

}
