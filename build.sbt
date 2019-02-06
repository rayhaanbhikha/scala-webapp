name := "web-app"

version := "0.1"

scalaVersion := "2.12.8"

val finagleVersion = "19.1.0"

val finagle = "com.twitter" %% "finagle-http" % finagleVersion
val json4s =  "org.json4s" %% "json4s-jackson" % "3.6.4"
val finagleThrift = "com.twitter" %% "finagle-thrift" % finagleVersion

val commonSettings = libraryDependencies ++= Seq(finagle, json4s, finagleThrift)

lazy val userSystem = (project in file("."))
  .aggregate(userGateway, userDatastore, thrift)

lazy val userGateway = (project in file("user-gateway"))
  .settings(
    name := "userGateway",
    commonSettings
  )
  .dependsOn(thrift)

lazy val userDatastore = (project in file("user-datastore"))
  .settings(
    name := "userDatastore",
    commonSettings
  )
  .dependsOn(thrift)


lazy val thrift = (project in file("thrift"))
  .settings(commonSettings)