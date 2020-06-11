package com.tenengroup.example.misc

trait PureTransformation[TLModel, TTModel] {
  def transformation(model: TLModel): TTModel
}
