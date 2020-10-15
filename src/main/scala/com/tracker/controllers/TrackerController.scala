package com.tracker.controllers

import java.util.UUID

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives
import com.tracker.repository.TrackerRepository
import com.tracker.mappings.JsonMappings
import akka.http.scaladsl.model.HttpResponse
import com.tracker.model.{Position, TrackerResponse}

import scala.util.{Failure, Success}

trait TrackerController extends Directives with JsonMappings {
  val trackerApi = concat(
    path("trackers") {
      concat(
        pathEndOrSingleSlash {
          concat(
            post {
              entity(as[Position]) { pos =>
                onComplete(TrackerRepository.createTracker(pos)) {
                  _ match {
                    case Success(id) => complete(TrackerResponse(id))
                    case Failure(throwable: Throwable) =>
                      complete(HttpResponse(InternalServerError, entity = throwable.getMessage))
                  }
                }
              }
            }
          )
        }
      )
    }
  )

}