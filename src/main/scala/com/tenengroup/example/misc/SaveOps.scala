package com.tenengroup.example.misc

import java.util.UUID

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

trait SaveOps {
  def save(df: DataFrame, parquetTablePath: String, sparkSession: SparkSession, partitionsNum: Int = 20): Unit = {
    val fs: FileSystem = FileSystem.get(sparkSession.sparkContext.hadoopConfiguration)

    val parquetTablePathTemp = s"$parquetTablePath-${UUID.randomUUID().toString}"

    df.coalesce(partitionsNum).write.mode(SaveMode.Overwrite).parquet(s"$parquetTablePathTemp")

    val isDelete = fs.delete(new Path(parquetTablePath), true)
    val isRename = fs.rename(new Path(parquetTablePathTemp), new Path(parquetTablePath))
  }
}
