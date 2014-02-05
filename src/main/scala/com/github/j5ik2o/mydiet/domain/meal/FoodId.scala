package com.github.j5ik2o.mydiet.domain.meal

import java.util.UUID
import org.sisioh.dddbase.core.model.Identifier

trait FoodId extends Identifier[UUID]

object FoodId {

  def apply(value: UUID): FoodId = Default(value)

  private[mydiet]
  case class Default(value: UUID) extends FoodId

}
