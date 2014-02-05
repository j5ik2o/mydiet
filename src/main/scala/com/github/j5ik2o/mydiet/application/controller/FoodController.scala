package com.github.j5ik2o.mydiet.application.controller

import org.sisioh.trinity._
import org.sisioh.trinity.domain.mvc.action.SimpleAction

case class FoodController() extends ControllerSupport {

  def listFoodsByUserId(creatorId: String, index: Int, chunkSize: Int): SimpleAction = SimpleAction {
    request =>
      responseBuilder.withContent("food").toFuture
  }

  def listFoods(index: Int, chunkSize: Int): SimpleAction = SimpleAction {
    request =>
      responseBuilder.withContent("food").toFuture
  }

  def createFood(): SimpleAction = SimpleAction {
    request =>
      responseBuilder.withContent("food").toFuture
  }

  def updateFood(foodId: String) = SimpleAction {
    request =>
      responseBuilder.withContent("food").toFuture
  }

  def deleteFood(foodId: String): SimpleAction = SimpleAction {
    request =>
      responseBuilder.withContent("food").toFuture
  }

}
