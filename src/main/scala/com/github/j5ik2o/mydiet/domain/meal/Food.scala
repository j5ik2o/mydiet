package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.model.Entity
import com.github.j5ik2o.mydiet.domain.user.UserId


/**
 * 炭水化物
 */
trait FoodCarbohydrates {

  trait InternalValue {
    val sugar: Float
    val fibre: Float
  }

  val value: Float

  val internal: Option[InternalValue]

}

/**
 * 食品成分
 */
trait FoodIngredient {

  val perUnit: Int = 100

  val calorie: Float

  val protein: Float

  val lipid: Float

  val carbohydrates: FoodCarbohydrates

  val na: Float

}

trait Food extends Entity[FoodId] {

  val creatorId: Option[UserId]

  val name: String

  def ingredient: FoodIngredient

}





