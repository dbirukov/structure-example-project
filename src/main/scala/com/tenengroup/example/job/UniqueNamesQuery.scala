package com.tenengroup.example.job

import com.tenengroup.example.Transformation
import org.apache.spark.sql.{DataFrame, SparkSession}

class UniqueNamesQuery(spark: SparkSession)
  extends Transformation[(String, String), String, (DataFrame, DataFrame), DataFrame]
    with  UniqueNamesJobOps {

  override def sourceConfig(args: Array[String]): (String, String) = {
    val catsPath = args(0)
    val dogsPath = args(1)

    (catsPath, dogsPath)
  }

  override def destConfig(args: Array[String]): String = args(2)

  override def load(source: (String, String)): (DataFrame, DataFrame) = {
    val (catsPath, dogsPath) = source
    val catsDF = spark.read.option("header", "true").csv(catsPath)
    val dogsDF = spark.read.option("header", "true").csv(dogsPath)

    (catsDF, dogsDF)
  }

  override def save(df: DataFrame, dest: String): Unit = df.write.parquet(dest)

  override def transformation(df: (DataFrame, DataFrame)): DataFrame = {
    val (catsDF, dogsDF) = df

    val catsNamesDF = catsDF.transform(selectCatsNames)
    val dogsNamesDF = dogsDF.transform(selectDogsNames)

    dogsNamesDF
      .transform(unionCatsDogsNames(catsNamesDF))
      .transform(distinct)
  }
}

