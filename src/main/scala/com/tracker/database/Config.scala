package com.tracker.database

import com.typesafe.config.ConfigFactory

trait Config {
  private val config = ConfigFactory.load()
  private val dbConfig = config.getConfig("db")

  val dbUrl = dbConfig.getString("url")
  println(dbUrl)
  val dbUser = dbConfig.getString("user")
  val dbPassword = dbConfig.getString("password")
}