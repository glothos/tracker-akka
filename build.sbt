lazy val akkaHttpVersion = "10.0.11"
lazy val akkaVersion = "2.5.11"
lazy val bountyCastleVersion = "1.59"


lazy val root = (project in file(".")).
  settings(
    inThisBuild(
      List(
        organization := "com.tracker",
        version := "0.1",
        scalaVersion := "2.12.9"
      )
    ),
    name := "Trackers Service",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "ch.qos.logback" % "logback-classic" % "1.0.0" % "runtime",
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
      "com.typesafe.akka" %% "akka-http-caching" % akkaHttpVersion,
      "org.scalatest" %% "scalatest" % "3.0.1" % Test,
      "com.typesafe.slick" %% "slick" % "3.3.2",
      "org.slf4j" % "slf4j-nop" % "1.6.4",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
      "com.github.tminglei" %% "slick-pg" % "0.18.0",
      "org.flywaydb" % "flyway-core" % "5.2.4",
      "joda-time" % "joda-time" % "2.10.3",
      "com.typesafe.play" %% "play-json" % "2.7.0",
      "com.github.tminglei" %% "slick-pg_joda-time" % "0.18.0",
      "com.github.tminglei" %% "slick-pg_play-json" % "0.18.0",
      "com.github.tminglei" %% "slick-pg_spray-json" % "0.18.0",
      "org.joda" % "joda-convert" % "2.2.1",
      "org.postgresql" % "postgresql" % "42.2.5",
      "com.uber" % "h3" % "3.6.0",
      "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
    ),
  )