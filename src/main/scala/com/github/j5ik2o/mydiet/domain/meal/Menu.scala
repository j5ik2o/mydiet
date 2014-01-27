package com.github.j5ik2o.mydiet.domain.meal

/**
 * 献立。
 *
 * 複数の食品を集約した食品。
 */
trait Menu extends Food {

  val materialIds: Seq[FoodId]

}
