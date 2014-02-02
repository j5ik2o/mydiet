package com.github.j5ik2o.mydiet.domain.user

import org.sisioh.dddbase.core.lifecycle.async.AsyncRepository

trait UserRepository extends AsyncRepository[UserId, User] {

}


object UserRepository {

  def ofMemory: UserRepository = new UserRepositoryOnMemory

}
