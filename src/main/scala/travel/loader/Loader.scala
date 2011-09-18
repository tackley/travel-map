package travel.loader

import org.joda.time.DateTime
import com.weiglewilczek.slf4s.{Logging, Logger}
import akka.actor.Actor._
import akka.actor.{Scheduler, Actor}
import java.util.concurrent.TimeUnit

class Loader extends Actor with Logging {
  var latestContentProcessed: Option[DateTime] = None

  def receive = {
    case "poll" =>
      val processFrom = latestContentProcessed getOrElse MongoWriter.mostRecentLastModified

      logger.info("Reading content modified since " + processFrom)
      val content = ApiReader.contentModifiedSince(processFrom)

      logger.debug("items read = %d first = %s last = %s" format (content.size, content.headOption.map(_.lastModified), content.lastOption.map(_.lastModified)))

      MongoWriter.write(content filter { _.isInteresting } )

      latestContentProcessed = content.lastOption.map(_.lastModified) orElse latestContentProcessed
  }

}

object Loader {
  val loader = actorOf[Loader].start()

  def start() = {
    Scheduler.schedule(loader, "poll", 1, 5, TimeUnit.MINUTES)
  }

  def shutdown() { Scheduler.shutdown() }

}