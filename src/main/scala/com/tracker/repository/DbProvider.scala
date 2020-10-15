package com.tracker.repository

import com.tracker.database.DbConfig
import com.tracker.model.TrackersTable
import slick.dbio.NoStream
import slick.lifted.TableQuery
import slick.sql.{FixedSqlStreamingAction, SqlAction}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

trait DbProvider extends DbConfig {

  implicit val trackers = TableQuery[TrackersTable]

  protected implicit def executeFromDb[A](action: SqlAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = {
    val run = db.run(action)
    run.onComplete {
      case _ => db.close
    }
    run
  }

  protected implicit def executeReadStreamFromDb[A](action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]): Future[Seq[A]] = {
    db.run(action)
  }
}