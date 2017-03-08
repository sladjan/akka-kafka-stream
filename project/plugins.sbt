// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.12")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")

// to format scala source code
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")
