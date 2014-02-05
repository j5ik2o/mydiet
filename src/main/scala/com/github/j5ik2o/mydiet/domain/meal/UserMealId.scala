package com.github.j5ik2o.mydiet.domain.meal

import java.util.UUID
import org.sisioh.dddbase.core.model.Identifier

trait UserMealId extends Identifier[UUID]

object userMealId {

  private[mydiet] case class UserMealIdImpl(value: UUID) extends UserMealId

}

