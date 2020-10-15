package com.tracker.database

import org.flywaydb.core.Flyway

trait Migrations extends Config {
  private val flyway = Flyway.configure().dataSource(dbUrl, dbUser, dbPassword).load()

  def migrate() = {
    flyway.baseline()
    flyway.migrate()
  }
  def reloadSchema() = {
    flyway.clean()
    flyway.migrate()
  }
}
