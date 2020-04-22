package com.tenengroup.example

trait PureTransformation[TLModel, TTModel] {
  def transformation(model: TLModel): TTModel
}
