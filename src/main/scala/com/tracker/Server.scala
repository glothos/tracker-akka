package com.tracker

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.tracker.database.{Migrations}

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.Duration

object Server extends App with Migrations with Routes {

  private implicit val actorSystem = ActorSystem()
  protected implicit val materializer = ActorMaterializer()
  protected implicit val executor: ExecutionContext = actorSystem.dispatcher
//  protected val log: LoggingAdapter = Logging(actorSystem, getClass)

  migrate()

  Http().bindAndHandle(routes, "localhost", 9000)
  Await.result(actorSystem.whenTerminated, Duration.Inf)
}
