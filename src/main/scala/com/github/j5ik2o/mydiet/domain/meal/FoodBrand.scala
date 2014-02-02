package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.model.{EntityCloneable, Entity}

trait FoodBrand extends Entity[FoodBrandId] with EntityCloneable[FoodBrandId, FoodBrand] with Ordered[FoodBrand] {
  val name: String
}

object FoodBrand {

  def apply(identifier: FoodBrandId, name: String): FoodBrand = Default(identifier, name)

  private[mydiet]
  case class Default(identifier: FoodBrandId, name: String) extends FoodBrand {
    override def compare(that: FoodBrand): Int = ???
  }

}
