package com.tracker.model
import java.util.UUID

import com.tracker.database.PgProfile.api._

class TrackersTable(tag: Tag) extends Table[Tracker](tag, "trackers") {
  def id = column[UUID]("id", O.PrimaryKey)
  def lat = column[Double]("lat")
  def lng = column[Double]("lng")
  def h3 = column[Long]("h3")
  def kRing = column[List[Long]]("kRing")
  def driverId = column[Int]("driverId", O.Unique)

  def * = (id, lat, lng, h3, kRing, driverId) <> (Tracker.tupled, Tracker.unapply)
}
