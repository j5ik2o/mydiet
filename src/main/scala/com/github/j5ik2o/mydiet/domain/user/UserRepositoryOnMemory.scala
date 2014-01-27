package com.github.j5ik2o.mydiet.domain.user

import org.sisioh.dddbase.core.lifecycle.memory.mutable.sync.AbstractSyncRepositoryOnMemory

class UserRepositoryOnMemory
  extends AbstractSyncRepositoryOnMemory[UserId, User] with UserRepository {

  type This = UserRepositoryOnMemory

}
