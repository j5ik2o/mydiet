package com.github.j5ik2o.mydiet.domain.user

import org.sisioh.dddbase.core.model.{EntityCloneable, Entity}

trait User extends Entity[UserId] with EntityCloneable[UserId, User] with Ordered[User] {

  val name: String

  val height: Float

}
