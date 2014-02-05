package com.github.j5ik2o.mydiet.application

import org.sisioh.trinity.Methods._
import org.sisioh.trinity.RouteDsl._
import org.sisioh.trinity._
import com.github.j5ik2o.mydiet.application.controller.FoodController

object Application extends ConsoleApplication {

  private val foodController = FoodController()

  override protected lazy val routingFilter = Some(RoutingFilter.createForActions {
    implicit pathPatternParser =>
      Seq(
        Get % "/foods/" -> {
          request =>
            foodController.listFoods(
              request.getParamAsInt("index", 0),
              request.getParamAsInt("chunkSize", 100)
            )(request)
        },
        Post % "/food/create" -> foodController.createFood,
        Post % "/food/update/:id" -> {
          request =>
            foodController.updateFood(request.routeParams("id"))(request)
        },
        Post % "/food/delete/:id" -> {
          request =>
            foodController.deleteFood(request.routeParams("id"))(request)
        }
      )
  })

  startWithAwait()

}
