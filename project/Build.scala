import sbt._
import Keys._

object ScalaBenchmarkingTemplateBuild extends Build {

  lazy val root = Project("root", file(".")) settings(
    organization := "com.example",

    name := "scala-benchmarking-template",

    version := "1.0.0-SNAPSHOT",

    scalaVersion := "2.9.1",

    resolvers ++= Seq(
      "Sonatype OSS Repo" at "http://oss.sonatype.org/content/repositories/snapshots"
    ),

    libraryDependencies ++= Seq(
      "com.google.guava" % "guava" % "10.0.1",
      "com.google.code.caliper" % "caliper" % "1.0-SNAPSHOT",
      "com.google.code.gson" % "gson" % "2.1"
    ),

    fork in run := true,

    javaOptions in run <<= (javaOptions, fullClasspath in Runtime) map {
      (opts, cp) =>
        opts ++ Seq("-cp", cp map {
          case Attributed(file) =>
            file.getAbsolutePath
        } mkString (":"))
    })

}

