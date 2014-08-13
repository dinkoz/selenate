package net.selenate.server

import actors.ActorFactory
import driver.DriverPool
import sessions.SessionFactory

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object EntryPoint extends App {
  private val log = Log(EntryPoint.getClass)

  try {
    log.info("Selenate Server now starting.")
    log.info("Press ENTER to shut down.")

    log.info("Loading configuration...")
    log.info("  Branch:       " + C.branch)
    log.info("  ServerHost:   " + C.Server.host)
    log.info("  Loaded pools: " + C.Server.Pool.poolInfoList.map(_.name ))

    SessionFactory
    DriverPool

    readLine
    log.info("Selenate Server now shutting down.")
    ActorFactory.shutdown()
    Runtime.getRuntime.halt(0)
  } catch {
    case e: Exception =>
      log.error("An unexpected error occured!")
      log.error(e.stackTrace)
      log.error("HALTING!")
      Runtime.getRuntime.halt(-1)
  }
}
