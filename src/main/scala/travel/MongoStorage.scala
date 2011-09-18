package travel

import com.mongodb.casbah.MongoConnection._
import com.mongodb.WriteConcern
import com.mongodb.casbah.MongoConnection


object MongoStorage {
  com.mongodb.casbah.commons.conversions.scala.RegisterJodaTimeConversionHelpers()

  private lazy val mongoConn = MongoConnection()

  private lazy val db = mongoConn("travel")
  db.setWriteConcern(WriteConcern.SAFE)

  def collection(name: String) = db(name)
}
