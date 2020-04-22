name := "structure-example-project"

version := "0.0.1"
val sparkVersion = "2.4.4"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

