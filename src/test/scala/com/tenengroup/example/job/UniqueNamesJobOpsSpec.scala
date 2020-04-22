package com.tenengroup.example.job

import org.apache.spark.sql.types.StringType
import org.scalatest.{FlatSpecLike, Matchers}

class UniqueNamesJobOpsSpec
  extends UniqueNamesJobOps
    with FlatSpecLike
    with Matchers
    with SparkSessionTestWrapper{
  import testSpark.implicits._

  "unique_names ops" should "select only name col from cats df" in {
    val df = List(
      ("Alice", "Bob", "John"),
      ("James", "Martin", "Guido")
    ).toDF("test_col", "name", "test_extra_col")

    val resultDF = df.transform(selectCatsNames)

    resultDF.schema.fields.length should be(1)
    assertField("name", StringType, resultDF.schema)
    resultDF.where($"name" === "Bob").count() should be(1)
    resultDF.where($"name" === "Martin").count() should be(1)
  }

  it should "select only name col and ren from dogs df" in {
    val df = List(
      ("Alice", "Bob", "John"),
      ("James", "Martin", "Guido")
    ).toDF("test_col", "dog_name", "test_extra_col")

    val resultDF = df.transform(selectDogsNames)

    resultDF.schema.fields.length should be(1)
    assertField("name", StringType, resultDF.schema)
    resultDF.where($"name" === "Bob").count() should be(1)
    resultDF.where($"name" === "Martin").count() should be(1)
  }

  it should "union dogs and cats names dfs" in {
    val catNamsDF = List("Alice", "Bob", "John").toDF("name")
    val dogNamesDF = List("James", "Martin", "Guido").toDF("name")

    val resultDF = catNamsDF.transform(unionCatsDogsNames(dogNamesDF))

    assertField("name", StringType, resultDF.schema)
    resultDF.count() should be(6)
    resultDF.where($"name" === "Alice").count() should be(1)
    resultDF.where($"name" === "James").count() should be(1)
  }

  it should "distinct names dataset" in {
    val namesDF = List("Alice", "Bob", "John", "Alice", "Bob", "John").toDF("name")

    val resultDF = namesDF.transform(distinct)

    assertField("name", StringType, resultDF.schema)
    resultDF.count() should be(3)
    resultDF.where($"name" === "Alice").count() should be(1)
    resultDF.where($"name" === "John").count() should be(1)
    resultDF.where($"name" === "Bob").count() should be(1)
  }

}
