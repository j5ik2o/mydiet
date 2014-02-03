package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.model.{EntityCloneable, Entity}
import com.github.j5ik2o.mydiet.domain.user.UserId

trait FoodCarbohydratesInternalValue {
  val sugar: Float
  val fibre: Float
  val total = sugar + fibre

  def +(other: FoodCarbohydratesInternalValue): FoodCarbohydratesInternalValue

  def -(other: FoodCarbohydratesInternalValue): FoodCarbohydratesInternalValue
}

object FoodCarbohydratesInternalValue {

  private[mydiet]
  case class Default(sugar: Float, fibre: Float)
    extends FoodCarbohydratesInternalValue {

    override def +(other: FoodCarbohydratesInternalValue): FoodCarbohydratesInternalValue =
      copy(sugar = sugar + other.sugar, fibre = fibre + other.fibre)

    override def -(other: FoodCarbohydratesInternalValue): FoodCarbohydratesInternalValue =
      copy(sugar = sugar + other.sugar, fibre = fibre + other.fibre)
  }

}

/**
 * 炭水化物
 */
trait FoodCarbohydrates {

  val value: Float

  val internal: Option[FoodCarbohydratesInternalValue]

  def +(other: FoodCarbohydrates): FoodCarbohydrates

  def -(other: FoodCarbohydrates): FoodCarbohydrates
}

object FoodCarbohydrates {


  private[mydiet]
  case class Default
  (value: Float,
   internal: Option[FoodCarbohydratesInternalValue])
    extends FoodCarbohydrates {

    override def +(other: FoodCarbohydrates): FoodCarbohydrates = {
      copy(
        value = value + other.value,
        internal = for {
          me <- internal
          you <- other.internal
        } yield {
          me + you
        }
      )
    }

    override def -(other: FoodCarbohydrates): FoodCarbohydrates = {
      copy(
        value = value - other.value,
        internal = for {
          me <- internal
          you <- other.internal
        } yield {
          me - you
        }
      )
    }

  }

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

  def +(other: FoodIngredient): FoodIngredient

  def -(other: FoodIngredient): FoodIngredient

}

object FoodIngredient {

  def apply
  (calorie: Float,
   protein: Float,
   lipid: Float,
   carbohydrates: FoodCarbohydrates,
   na: Float): FoodIngredient =
    Default(calorie, protein, lipid, carbohydrates, na)

  private[mydiet]
  case class Default
  (calorie: Float,
   protein: Float,
   lipid: Float,
   carbohydrates: FoodCarbohydrates,
   na: Float)
    extends FoodIngredient {

    override def +(other: FoodIngredient): FoodIngredient = {
      copy(
        calorie = calorie + other.calorie,
        protein = protein + other.protein,
        lipid = lipid + other.lipid,
        carbohydrates = carbohydrates + other.carbohydrates,
        na = na + other.na
      )
    }

    override def -(other: FoodIngredient): FoodIngredient = {
      copy(
        calorie = calorie - other.calorie,
        protein = protein - other.protein,
        lipid = lipid - other.lipid,
        carbohydrates = carbohydrates - other.carbohydrates,
        na = na - other.na
      )
    }
  }

}


trait Food
  extends Entity[FoodId] with EntityCloneable[FoodId, Food] with Ordered[Food] {

  val name: String

  val ingredient: FoodIngredient

  val creatorId: Option[UserId]

  val brandId: Option[FoodBrandId]

}

object Food {

  def apply(identifier: FoodId, name: String, ingredient: FoodIngredient,
            creatorId: Option[UserId] = None,
            brandId: Option[FoodBrandId] = None): Food =
    Default(identifier, name, ingredient, creatorId, brandId)


  def ofSettable(identifier_ : FoodId,
                 name_ : String,
                 materialIds_ : Seq[FoodId],
                 creatorId_ : Option[UserId] = None,
                 brandId_ : Option[FoodBrandId] = None)(implicit fr: FoodRepository): Food =
    new Food with Settable {
      override val identifier: FoodId = identifier_
      override val brandId: Option[FoodBrandId] = brandId_
      override val creatorId: Option[UserId] = creatorId_
      override val name: String = name_
      override val foodRepository: FoodRepository = fr
      override val materialIds: Seq[FoodId] = materialIds_

      override def compare(that: Food): Int = this.identifier.value.compareTo(that.identifier.value)
    }

  private[mydiet] case class Default
  (identifier: FoodId,
   name: String,
   ingredient: FoodIngredient,
   creatorId: Option[UserId] = None,
   brandId: Option[FoodBrandId] = None)
    extends Food {
    override def compare(that: Food): Int = this.identifier.value.compareTo(that.identifier.value)
  }


}





