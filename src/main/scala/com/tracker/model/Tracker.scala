package com.tracker.model

import java.util.UUID


case class Tracker(id: UUID, lat: Double, lng: Double, h3: Long, kRing: List[Long], driverId: Int) {
  require(!lat.isInstanceOf[Long] & !lng.isInstanceOf[Long], "Invalid Position")
}



