package com.github.j5ik2o.mydiet.domain.meal

import org.sisioh.dddbase.core.lifecycle.async.AsyncRepository
import org.sisioh.dddbase.core.lifecycle.memory.mutable.async.AbstractAsyncRepositoryOnMemory

trait FoodBrandRepository extends AsyncRepository[FoodBrandId, FoodBrand] {

}

object FoodBrandRepository {

  def apply(): FoodBrandRepository = Default()

  private[mydiet] case class Default()
    extends AbstractAsyncRepositoryOnMemory[FoodBrandId, FoodBrand]
    with FoodBrandRepository

}

