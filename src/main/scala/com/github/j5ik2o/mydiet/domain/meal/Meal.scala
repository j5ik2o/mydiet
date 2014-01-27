package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.model.Entity
import org.sisioh.baseunits.scala.time.TimePoint
import com.github.j5ik2o.mydiet.domain.user.UserId

trait Meal extends Entity[MealId] {

  val userId: UserId

  val date: TimePoint

  val foodIds: Seq[FoodId]

  def ingredient: FoodIngredient

}
