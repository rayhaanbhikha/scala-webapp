name := "web-app"

version := "0.1"

scalaVersion := "2.12.8"

val finagle = "com.twitter" %% "finagle-http" % "18.12.0"
val json4s =  "org.json4s" %% "json4s-jackson" % "3.6.4"

val commonSettings = Seq(finagle, json4s)

lazy val userGateway = (project in file("user-gateway"))
  .settings(
    name := "userGateway",
    libraryDependencies ++= commonSettings
  )

lazy val userDatastore = (project in file("user-datastore"))
  .settings(
    name := "userDatastore",
    libraryDependencies ++= commonSettings
  )
