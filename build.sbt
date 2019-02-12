name := "web-app"

version := "0.1"

scalaVersion := "2.12.8"

val finagleVersion = "19.1.0"

val finagle = "com.twitter" %% "finagle-http" % finagleVersion
val json4s =  "org.json4s" %% "json4s-jackson" % "3.6.4"
val finagleThrift = "com.twitter" %% "finagle-thrift" % finagleVersion

val commonSettings = libraryDependencies ++= Seq(finagle, json4s, finagleThrift)

lazy val userSystem = (project in file("."))
  .aggregate(usergateway, userdatastore, thrift)


lazy val dockerConfig = Seq(
  packageName in Docker := s"web-app/${name.value}",
  maintainer in Docker := "sky",
  version in Docker := version.value,
  dockerBaseImage := "openjdk",
  dockerUsername := Some("identity")
)



def generateDockerProject(project: Project): Project = project
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .settings(commonSettings, dockerConfig)
  .dependsOn(thrift)


lazy val usergateway = generateDockerProject(project in file("user-gateway"))

lazy val userdatastore = generateDockerProject(project in file("user-datastore"))

lazy val thrift = (project in file("thrift")).settings(commonSettings)

