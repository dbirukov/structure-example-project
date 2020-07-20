package com.tenengroup.example.job

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataType, StructType}

trait SparkSessionTestWrapper {
  lazy val testSpark: SparkSession = {
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("spark test example")
      .getOrCreate()

    spark.conf.set("spark.sql.session.timeZone", "UTC")
    spark.sparkContext.setLogLevel("ERROR")

    spark
  }

  def assertField(fieldName: String, fieldType: DataType, schema: StructType): Unit = {
    val fieldOpt = schema.fields.find(f => f.name == fieldName && f.dataType == fieldType)
    assert(fieldOpt.nonEmpty, s"$fieldOpt field was not found in $schema")
  }
}
