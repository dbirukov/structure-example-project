package com.tenengroup.example.job

import org.apache.spark.sql.DataFrame

trait UniqueNamesJobOps {
  def selectCatsNames(df: DataFrame): DataFrame = df.select(df.col("name"))

  def selectDogsNames(df: DataFrame): DataFrame = df.select(df.col("dog_name").as("name"))

  def unionCatsDogsNames(catsDF: DataFrame)(dogsDF: DataFrame): DataFrame =
    catsDF.union(dogsDF)

  def distinct(df: DataFrame): DataFrame = df.distinct
}
