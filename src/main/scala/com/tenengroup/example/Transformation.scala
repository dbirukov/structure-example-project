package com.tenengroup.example

trait Transformation[TS, TD, TLModel, TTModel]
  extends PureTransformation [TLModel, TTModel] {

  def main(args: Array[String]): Unit = {
    val sourceCfg = sourceConfig(args)
    val destCfg = destConfig(args)

    execute(sourceCfg, destCfg)
  }

  def sourceConfig(args: Array[String]): TS
  def destConfig(args: Array[String]): TD
  def load(source: TS): TLModel
  def save(df: TTModel, dest: TD): Unit
  def execute(source: TS, dest: TD): Unit = {
    val df = load(source)
    val transformed = transformation(df)
    save(transformed, dest)
  }
}
