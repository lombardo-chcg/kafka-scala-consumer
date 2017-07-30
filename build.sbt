name := "sbt-starter"

version := "0.0.1"

mainClass in assembly := Some("com.lombardo.app.Main")

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "0.11.0.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3"
)

logLevel := Level.Error

scalaVersion := "2.12.1"
