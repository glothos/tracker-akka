package com.tracker.controllers
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler
import javax.management.openmbean.KeyAlreadyExistsException

trait ErrorHandlers {
  implicit def myExceptionHandler: ExceptionHandler = ExceptionHandler {
    case e: NoSuchElementException =>
      extractUri { uri =>
        complete(HttpResponse(NotFound, entity = s"Invalid id: ${e.getMessage}"))
      }
    case e: KeyAlreadyExistsException =>
      complete(HttpResponse(BadRequest, entity = "User already has a tracker"))
  }
}
