scalaVersion := "2.9.1"

scalacOptions ++= Seq("-deprecation")

resolvers ++= Seq(
  "repo.novus snaps" at "http://repo.novus.com/snapshots/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Guardian Github Releases" at "http://guardian.github.com/maven/repo-releases"
)

libraryDependencies ++= {
  val liftVersion = "2.4-M4"
  Seq(
    "net.databinder" %% "dispatch-http" % "0.8.5",
    "net.liftweb" %% "lift-json" % liftVersion,
    "net.liftweb" %% "lift-util" % liftVersion,
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "com.mongodb.casbah" % "casbah-core_2.9.0-1" % "2.1.5-1",
    "org.mongodb" % "mongo-java-driver" % "2.6.5",
    "com.novus" % "salat-core_2.9.0-1" % "0.0.8-SNAPSHOT",
    "se.scalablesolutions.akka" % "akka-actor" % "1.2-RC6",
    "ch.qos.logback" % "logback-classic" % "0.9.26",
    "com.gu.openplatform" %% "content-api-client" % "1.13-SNAPSHOT",
    "com.weiglewilczek.slf4s" %% "slf4s" % "1.0.7",
    "org.specs2" %% "specs2" % "1.6.1" % "test",
    "org.eclipse.jetty" % "jetty-webapp" % "7.5.1.v20110908" % "jetty"
  )
}

// salat (incorrectly) brings in slf4j simple - we don't want that as we use logback!
ivyXML :=
         <dependencies>
           <exclude module="slf4j-simple"/>
         </dependencies>


seq(webSettings :_*)


