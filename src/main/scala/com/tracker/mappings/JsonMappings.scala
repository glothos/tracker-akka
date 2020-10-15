package com.tracker.mappings

import com.tracker.model.{Position, Tracker, TrackerResponse}
import spray.json.{DefaultJsonProtocol, JsString, JsValue, JsonFormat}
import spray.json._
import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

trait JsonMappings extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object UUIDFormat extends JsonFormat[UUID] {

    def write(uuid: UUID) = JsString(uuid.toString)

    override def read(value: JsValue): UUID = {
      value match {
        case JsString(uuid) => UUID.fromString(uuid)
      }
    }
  }
  implicit object KRingFormat extends JsonFormat[List[Long]] {
    def write(kRing: List[Long]) = kRing.toJson

    def read(kRing: JsValue) = kRing.convertTo[List[Long]]
  }
  implicit val trackerFormat = jsonFormat6(Tracker)
  implicit val positionFormat = jsonFormat3(Position)
  implicit val trackerResponseFormat = jsonFormat1(TrackerResponse)
}

