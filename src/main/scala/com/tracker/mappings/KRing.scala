package com.tracker.mappings

import play.api.libs.json.Json
import slick.jdbc.PostgresProfile.api._


case class KRing(value: List[Long])

object KRing {
  implicit val KRingFormat = Json.format[KRing]
  implicit val KRingColumnType = MappedColumnType.base[KRing, String](
    kring => Json.stringify(Json.toJson(kring)),
    column => Json.parse(column).as[KRing]
  )
}
