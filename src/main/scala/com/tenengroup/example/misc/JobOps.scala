package com.tenengroup.example.misc

import org.apache.spark.sql.{DataFrame, SparkSession}

trait JobOps {
  def initSpark(jobName: Option[String]): SparkSession = {
    val isLocalRun = scala.util.Properties.envOrElse("SPARK_LOCAL_RUN", "false").toBoolean
    val sparkSessionBuilder = SparkSession.builder()
      .appName(jobName.getOrElse(s"Fact_orders_payment ${getClass.getSimpleName}"))
    val spark = if(isLocalRun) {
      sparkSessionBuilder
        .master("local[*]")
        .getOrCreate()
    } else {
      sparkSessionBuilder.getOrCreate()
    }
    spark.sparkContext.setLogLevel("ERROR")
    spark.conf.set("spark.sql.session.timeZone", "UTC")
    spark
  }

  def getArgumentByIndex(args: Array[String], index: Int, errorMessage: String): String = {
    val maxPossibleArgs = 20
    val arguments = (1 to maxPossibleArgs).zipAll(args, null, null).map(_._2)
    val parquetRootPath = Option(arguments(index))
      .fold(throw new IllegalArgumentException(errorMessage))(identity)
    parquetRootPath
  }

}
