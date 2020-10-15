package com.tracker.database

trait DbConfig extends Config {
  def db = PgProfile.api.Database.forConfig("db")

  implicit val session = db.createSession()
}
