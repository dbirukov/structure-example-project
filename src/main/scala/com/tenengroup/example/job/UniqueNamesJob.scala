package com.tenengroup.example.job

import org.apache.spark.sql.SparkSession

object UniqueNamesJob extends App {
  def initSpark(jobName: Option[String]): SparkSession = {
    val isLocalRun = scala.util.Properties.envOrElse("SPARK_LOCAL_RUN", "true").toBoolean

    val sparkSessionBuilder = SparkSession.builder()
      .appName(jobName.getOrElse(getClass.getSimpleName))

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


  val sparkSession = initSpark(Some("UniqueNamesJob"))
  val job = new UniqueNamesQuery(sparkSession)
  job.main(args)

  sparkSession.close()
}
