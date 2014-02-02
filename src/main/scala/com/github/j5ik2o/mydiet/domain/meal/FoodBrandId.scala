package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.model.Identifier
import java.util.UUID

trait FoodBrandId extends Identifier[UUID]

object FoodBrandId {

  def apply(value: UUID): FoodBrandId = Default(value)

  private[mydiet] case class Default(value: UUID) extends FoodBrandId

}
