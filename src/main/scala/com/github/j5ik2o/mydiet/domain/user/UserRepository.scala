package com.github.j5ik2o.mydiet.domain.user

<<<<<<< HEAD
import scala.language.higherKinds
import org.sisioh.dddbase.core.lifecycle.Repository
import org.sisioh.dddbase.core.lifecycle.memory.mutable.async.AbstractAsyncRepositoryOnMemory
import scala.concurrent.Future
import org.sisioh.dddbase.core.lifecycle.async.AsyncRepository

trait UserRepository[M[+ _]] extends Repository[UserId, User, M] {
=======
import org.sisioh.dddbase.core.lifecycle.async.AsyncRepository

trait UserRepository extends AsyncRepository[UserId, User] {
>>>>>>> master

}

trait AsyncUserRepository extends AsyncRepository[UserId, User] with UserRepository[Future]

class AsyncUserRepositoryOnMemoryImpl
  extends AbstractAsyncRepositoryOnMemory[UserId, User]() with AsyncUserRepository {

<<<<<<< HEAD
  type This = AsyncUserRepositoryOnMemoryImpl

}

object AsyncUserRepository {

  def ofMemory: AsyncUserRepository = new AsyncUserRepositoryOnMemoryImpl
=======
  def ofMemory: UserRepository = new UserRepositoryOnMemory
>>>>>>> master

}
