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

  import scopt.OptionParser
  val parser = new OptionParser[Config]("UniqueNamesJob") {
    head("this is example app")
    opt[String]("dogsPath")
      .required()
      .valueName("<path>")
      .action((value, config) => config.copy(dogsPath = value))
      .text("dogsPath is required")
    opt[String]("catsPath")
      .required()
      .valueName("<path>")
      .action((value, config) => config.copy(catsPath = value))
      .text("catsPath is required")
    opt[String]("dest")
      .required()
      .valueName("<path>")
      .action((value, config) => config.copy(dest = value))
      .text("dest is required")
  }

  parser.parse(args, Config()) match {
    case Some(x) => println(x)
      val sparkSession = initSpark(Some("UniqueNamesJob"))
      val job = new UniqueNamesQuery(sparkSession)
      job.main(args)

      sparkSession.close()
    case None =>
  }

}
