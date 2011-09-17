scalaVersion := "2.9.1"

scalacOptions ++= Seq("-deprecation")

resolvers ++= Seq(
  "repo.novus snaps" at "http://repo.novus.com/snapshots/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= {
  val liftVersion = "2.4-M4"
  Seq(
    "net.databinder" %% "dispatch-http" % "0.8.5",
    "net.liftweb" %% "lift-json" % liftVersion,
    "net.liftweb" %% "lift-util" % liftVersion,
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "com.mongodb.casbah" % "casbah-core_2.9.0-1" % "2.1.5-1",
    "org.specs2" %% "specs2" % "1.6.1" % "test",
    "com.novus" % "salat-core_2.9.0-1" % "0.0.8-SNAPSHOT",
    "se.scalablesolutions.akka" % "akka-actor" % "1.2-RC6",
    "ch.qos.logback" % "logback-classic" % "0.9.26",
    "org.eclipse.jetty" % "jetty-webapp" % "7.5.1.v20110908" % "jetty"
  )
}

seq(webSettings :_*)


