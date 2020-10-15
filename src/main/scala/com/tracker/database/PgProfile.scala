package com.tracker.database

import com.github.tminglei.slickpg._

trait PgProfile extends ExPostgresProfile
  with PgSprayJsonSupport
  with PgArraySupport {
  override val api = MyApi

  def pgjson = "jsonb"

  object MyApi extends API with ArrayImplicits with JsonImplicits {
    implicit val longListTypeMapper = new SimpleArrayJdbcType[Long]("int8").to(_.toList)
  }
}

object PgProfile extends PgProfile
