import sbt._
import sbt.Keys._

object MydietBuild extends Build {

  val commonSettings = Project.defaultSettings ++ 
    net.virtualvoid.sbt.graph.Plugin.graphSettings ++ Seq(
    organization := "com.github.j5ik2o.mydiet",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.10.3",
    scalacOptions ++= Seq("-encoding", "UTF-8", "-feature", "-deprecation", "-unchecked"),
    javacOptions ++= Seq("-encoding", "UTF-8", "-deprecation"),
    resolvers ++= Seq(
      "Twitter Repository" at "http://maven.twttr.com/",
      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
      "Sonatype Release Repository" at "https://oss.sonatype.org/content/repositories/releases/",
      "Sonatype Snapshot Repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
      "Seasar Repository" at "http://maven.seasar.org/maven2/"
    ),
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % "2.10.3",
      "junit" % "junit" % "4.8.1" % "test",
      "org.hamcrest" % "hamcrest-all" % "1.3" % "test",
      "org.mockito" % "mockito-core" % "1.9.5" % "test",
      "org.specs2" %% "specs2" % "2.0" % "test",
      "org.seasar.util" % "s2util" % "0.0.1"
    ),
    fork in Test := true,
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := {
      _ => false
    }
  )

  val trinityVersion = "1.0.3-SNAPSHOT"

  lazy val mydiet = Project(
    id = "mydiet",
    base = file("."),
    settings = commonSettings ++ Seq(
      name := "mydiet",
      libraryDependencies ++= Seq(
        "org.sisioh" %% "scala-dddbase-core" % "0.2.0-SNAPSHOT",
        "org.sisioh" %% "baseunits-scala" % "0.1.12-SNAPSHOT",
        "org.sisioh" %% "trinity-core" % trinityVersion,
        "org.sisioh" %% "trinity-test" % trinityVersion
      )
    )
  )
}
