package com.github.j5ik2o.mydiet.domain.user

import org.sisioh.dddbase.core.lifecycle.Repository
import org.sisioh.dddbase.core.lifecycle.memory.mutable.async.AbstractAsyncRepositoryOnMemory
import scala.concurrent.Future
import scala.util.Try
import org.sisioh.dddbase.core.lifecycle.async.AsyncRepository
import org.sisioh.dddbase.core.lifecycle.sync.SyncRepository
import org.sisioh.dddbase.core.lifecycle.memory.mutable.sync.AbstractSyncRepositoryOnMemory

trait UserRepository[M[+ _]] extends Repository[UserId, User, M] {

}

trait AsyncUserRepository extends AsyncRepository with UserRepository[Future]

trait SyncUserRepository extends SyncRepository with UserRepository[Try]

class AsyncUserRepositoryOnMemoryImpl
  extends AbstractAsyncRepositoryOnMemory[UserId, User]() with AsyncUserRepository {

  type This = AsyncUserRepositoryOnMemoryImpl

}

class SyncUserRepositoryOnMemoryImpl
  extends AbstractSyncRepositoryOnMemory[UserId, User]() with SyncUserRepository {

  type This = AsyncUserRepositoryOnMemoryImpl

}

object UserRepository {

  def ofMemory: UserRepository = new UserRepositoryOnMemory

}
