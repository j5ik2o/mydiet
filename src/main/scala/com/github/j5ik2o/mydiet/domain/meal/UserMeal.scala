package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.model.Entity
import org.sisioh.baseunits.scala.time.TimePoint
import com.github.j5ik2o.mydiet.domain.user.UserId
import scala.concurrent.{ExecutionContext, Await}
import scala.concurrent.duration.Duration
import org.sisioh.dddbase.core.lifecycle.async.AsyncEntityIOContext

trait UserMeal extends Entity[UserMealId] {

  val userId: UserId

  val date: TimePoint

  val foodIds: Seq[FoodId]

  val foodRepository: FoodRepository

  val ingredient: FoodIngredient

}

object UserMeal {

  def apply(identifier: UserMealId,
            userId: UserId,
            date: TimePoint,
            foodIds: Seq[FoodId])(
            implicit foodRepository: FoodRepository): UserMeal = Default(identifier, userId, date, foodIds, foodRepository)

  private[mydiet]
  case class Default
  (identifier: UserMealId,
   userId: UserId,
   date: TimePoint,
   foodIds: Seq[FoodId],
   foodRepository: FoodRepository)
    extends UserMeal {

    override val ingredient: FoodIngredient = {
      implicit val executor = ExecutionContext.Implicits.global
      implicit val ctx = AsyncEntityIOContext()
      val future = foodRepository.multiResolveBy(foodIds: _*).map {
        _.map(_.ingredient).reduceLeft(_ + _)
      }
      Await.result(future, Duration.Inf)
    }
  }

}
