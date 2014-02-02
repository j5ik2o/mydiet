package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.lifecycle.async.AsyncRepository
import org.sisioh.dddbase.core.lifecycle.memory.mutable.async.AbstractAsyncRepositoryOnMemory

trait FoodRepository extends AsyncRepository[FoodId, Food]

object FoodRepository {

  def apply(): FoodRepository = Default()

  private[mydiet] case class Default()
    extends AbstractAsyncRepositoryOnMemory[FoodId, Food]
    with FoodRepository

}
