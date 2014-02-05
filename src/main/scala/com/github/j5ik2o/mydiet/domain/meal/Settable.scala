package com.github.j5ik2o.mydiet.domain.meal

import scala.concurrent.{ExecutionContext, Await}
import scala.concurrent.duration.Duration
import org.sisioh.dddbase.core.lifecycle.async.AsyncEntityIOContext

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
    implicit val executor = ExecutionContext.Implicits.global
    implicit val ctx = AsyncEntityIOContext()
    val future = foodRepository.multiResolveBy(materialIds: _*).map {
      entities =>
        entities.map(_.ingredient).reduceLeft(_ + _)
    }
    Await.result(future, Duration.Inf)
  }

}
