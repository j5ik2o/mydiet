package com.github.j5ik2o.mydiet.application.controller

import org.sisioh.trinity.domain.mvc.action.SimpleAction
import org.sisioh.trinity.domain.mvc.controller.ControllerSupport

class FoodController extends ControllerSupport {

  def createFood: SimpleAction = SimpleAction {
    request =>
      responseBuilder.toFuture
  }

}
