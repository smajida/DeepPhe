name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

resolvers += (
    "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository"
)

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.glassfish.jersey.core" % "jersey-client" % "2.22.1",
  "org.neo4j" % "neo4j" % "2.3.1",
  "org.neo4j" % "neo4j-ogm-http-driver" % "2.0.0-M04",
  "org.neo4j" % "neo4j-ogm-core" % "2.0.0-M04", 
  "org.healthnlp" % "deepphe-fhir" % "0.0.1-SNAPSHOT"
)


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true

// Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile)