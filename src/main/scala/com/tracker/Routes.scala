package com.tracker

import controllers.{ErrorHandlers, TrackerController}

trait Routes extends ErrorHandlers with TrackerController {
  lazy val routes = pathPrefix("v2") {
    trackerApi
  }
}
