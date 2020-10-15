package com.tracker.repository

import akka.actor.ActorSystem

class RepositoryContext {
  lazy val actorSystem = ActorSystem("RepositoryContext")
  lazy val scheduler = actorSystem.scheduler
  implicit lazy val executionContext = actorSystem.dispatcher
}