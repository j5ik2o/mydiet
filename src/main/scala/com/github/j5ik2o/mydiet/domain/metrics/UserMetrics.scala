package com.github.j5ik2o.mydiet.domain.metrics

import org.sisioh.dddbase.core.model.Entity
import com.github.j5ik2o.mydiet.domain.user.UserId
import org.sisioh.baseunits.scala.time.TimePoint

trait UserMetrics extends Entity[UserMetricsId] {

  val userId: UserId

  val date: TimePoint

  val weight: Float

  val fatRate: Float

}
