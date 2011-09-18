package travel

import com.mongodb.casbah.MongoConnection._
import com.mongodb.casbah.MongoConnection
import com.mongodb.{MongoURI, WriteConcern}
import net.liftweb.util.Props
import com.weiglewilczek.slf4s.Logging


object MongoStorage extends Logging {
  com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers()

  private lazy val host = Props.get("mongo.host", "localhost")
  private lazy val port = Props.getInt("mongo.port", 27017)

  private lazy val username = Props.get("mongo.user")
  private lazy val password = Props.get("mongo.pass")

  private lazy val mongoConn = MongoConnection(host, port)

  private lazy val db = mongoConn("travel-map")
  db.setWriteConcern(WriteConcern.SAFE)
  for (user <- username; pass <- password) db.authenticate(user, pass)

  def collection(name: String) = db(name)
}
