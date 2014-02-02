package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.model.Entity
import org.sisioh.baseunits.scala.time.TimePoint
import com.github.j5ik2o.mydiet.domain.user.UserId
import scala.concurrent.Await
import scala.concurrent.duration.Duration

trait UserMeal extends Entity[UserMealId] {

  val userId: UserId

  val date: TimePoint

  val foodIds: Seq[FoodId]

  val foodRepository: FoodRepository

  val ingredient: FoodIngredient

}

object UserMeal {

  private[mydiet]
  case class UserMealImpl
  (identifier: UserMealId,
   userId: UserId,
   date: TimePoint,
   foodIds: Seq[FoodId],
   foodRepository: FoodRepository) extends UserMeal {

    override val ingredient: FoodIngredient = {
      val future = foodRepository.multiResolveBy(foodIds: _*).map {
        _.map(_.ingredient).reduceLeft(_ + _)
      }
      Await.result(future, Duration.Inf)
    }
  }

}
