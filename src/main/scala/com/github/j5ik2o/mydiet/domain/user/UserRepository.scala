package com.github.j5ik2o.mydiet.domain.user

import org.sisioh.dddbase.core.lifecycle.Repository
import scala.concurrent.Future
import scala.util.Try

trait UserRepository extends Repository[UserId, User, Try] {

}


object UserRepository {

    def ofMemory: UserRepository = new UserRepositoryOnMemory

}
