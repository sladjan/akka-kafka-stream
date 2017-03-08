// enable scala code formatting //
import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform

name := "Kafka-Akka-Streams"

version := "1.0.0"

organization := "www.agileitsolution.com"
scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
 
  "com.typesafe.akka" %% "akka-stream" % "2.4.17",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.13", 
  "com.typesafe.play" %% "play-json" % "2.5.12",

  // for serialization of case class
  "com.novus" %% "salat" % "1.9.9",
  // Joda dates for Scala
  "com.github.nscala-time" %% "nscala-time" % "2.14.0",

  "org.apache.kafka" % "kafka_2.11" % "0.10.1.0",
  //"org.apache.kafka" % "kafka-clients" % "0.10.1.0",

  // https://mvnrepository.com/artifact/commons-io/commons-io
  "commons-io" % "commons-io" % "2.5"

)


// Scalariform settings
SbtScalariform.autoImport.scalariformPreferences := SbtScalariform.autoImport.scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)

//lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

parallelExecution in Test := false

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")


