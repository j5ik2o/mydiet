package com.github.j5ik2o.mydiet.domain.meal

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * 献立。
 *
 * 複数の食品を集約した食品。
 */
trait Settable {
  this: Food =>

  val materialIds: Seq[FoodId]

  val foodRepository: FoodRepository

  override val ingredient: FoodIngredient = {
    val future = foodRepository.multiResolveBy(materialIds: _*).map {
      entities =>
        entities.map(_.ingredient).reduceLeft(_ + _)
    }
    Await.result(future, Duration.Inf)
  }

}
