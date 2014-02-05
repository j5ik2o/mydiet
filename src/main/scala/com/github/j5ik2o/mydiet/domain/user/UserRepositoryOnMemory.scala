package com.github.j5ik2o.mydiet.domain.user

import org.sisioh.dddbase.core.lifecycle.memory.mutable.async.AbstractAsyncRepositoryOnMemory

class UserRepositoryOnMemory
  extends AbstractAsyncRepositoryOnMemory[UserId, User] with UserRepository {

  type This = UserRepositoryOnMemory

}
