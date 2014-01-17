import sbt._
import sbt.Keys._

object MydietBuild extends Build {

  lazy val mydiet = Project(
    id = "mydiet",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "mydiet",
      organization := "com.github.j5ik2o.mydiet",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.3"
      // add other settings here
    )
  )
}
