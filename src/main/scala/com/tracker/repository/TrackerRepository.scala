package com.tracker.repository

import java.util.UUID

import akka.actor.ActorSystem
import com.tracker.model.{Position, Tracker}
import com.tracker.database.PgProfile.api._
import com.uber.h3core.H3Core
import javax.management.openmbean.KeyAlreadyExistsException

import scala.collection.JavaConverters._
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object TrackerRepository extends DbProvider {
  lazy val actorSystem = ActorSystem("TrackerRepository")
  lazy val scheduler = actorSystem.scheduler
  implicit lazy val executionContext = actorSystem.dispatcher
  lazy val h3Instance = H3Core.newInstance()

  def getTrackerById(trackerId: UUID): Future[Tracker] = trackers.filter(_.id === trackerId).result.head

  def getTrackerByDriverId(driverId: Int): Future[Tracker] = trackers.filter(_.driverId === driverId).result.head

  def createSchema(): Unit = trackers.schema.createIfNotExists

  def updatePos(driverId: Int, lat: Double, lng: Double): Future[Int] = {
    println(lat, lng)
    val newH3: Long = h3Instance.geoToH3(lat, lng, 7)
    val newKRing: List[Long] = h3Instance.kRing(newH3, 1).asScala.toList.map { _.toLong }
    trackers.filter(_.driverId === driverId)
      .map(tracker => (tracker.lat, tracker.lng, tracker.h3, tracker.kRing))
      .update((lat, lng, newH3, newKRing))
  }

  def createTracker(pos: Position): Future[UUID] = {
    val lookup = exists(pos.driverId)
    Await.result(lookup, Duration.Inf)
    val newH3: Long = h3Instance.geoToH3(pos.lat, pos.lng, 7)
    val newKRing: List[Long] = h3Instance.kRing(newH3, 1).asScala.toList.map { _.toLong }
    lazy val uuid = UUID.randomUUID()
    val tracker = Tracker(
      uuid,
      pos.lat,
      pos.lng,
      newH3,
      newKRing,
      pos.driverId,
    )
    val q =
      (trackers returning trackers.map(_.id)) += tracker
    val newId = db.run(q)
    newId.onComplete {
      case Success(_) => db.close()
      case Failure(exception) => {
        db.close();
        throw exception
      }
    }
    newId
  }

  private def exists(driverId: Int) = db.run {
    trackers.filter(i => i.driverId === driverId).exists.result
  }.map{ case true => Future.failed(throw new KeyAlreadyExistsException)}
}
