name := """assetApp"""
organization := "hackproject"
version := "1.0.0-SNAPSHOT"

// Using the latest Long-Term Support (LTS) release for maximum enterprise stability
scalaVersion := "3.3.8"

// Assuming Play Framework to provide a highly structured, MVC architecture 
// (very similar to the structure and flow you get with Spring Boot)
lazy val root = (project in file("."))
  .enablePlugins(PlayScala)

// --- The Supply Chain (Dependencies) ---
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.2"

libraryDependencies ++= Seq(
  // 1. Core Framework & Dependency Injection
  guice,
  
  // 2. The Database Vaults (PostgreSQL + Slick ORM for Type-Safe Queries)
  "org.postgresql" % "postgresql" % "42.7.3",
  "com.typesafe.slick" %% "slick" % "3.5.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.5.1",
  
  // 3. Security & Access Control (RFID Badge System)
  // Used to enforce Admin/Asset Manager/Employee roles via JWT
  "com.auth0" % "java-jwt" % "4.4.0",
  "com.auth0" % "jwks-rsa" % "0.22.1",
  
  // 4. Caching Layer (The Receptionist's Clipboard)
  // Redis for caching KPI dashboard metrics to keep the app blazing fast
  "com.github.cb372" %% "scalacache-redis" % "1.0.0-M6",

  // 5. Concurrency & Background Jobs
  // Akka is included with Play, adding typed actors for overdue notifications logic
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.5",
  
  // 6. Enterprise Testing Suite
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
)

// Ensure strict compilation rules to catch state-transition errors early
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Wunused:all")      // Warns if you declare variables you don't use 