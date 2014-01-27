package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.model.Entity

trait Recipe extends Entity[RecipeId] {

  val foodId: FoodId

  val description: Option[String]

  val process: Seq[String]

  val remarks: Option[String]

}
