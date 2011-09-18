package travel.loader

import travel.MongoStorage
import com.mongodb.casbah.commons.MongoDBObject

object MongoConnectivityTest extends App {
  try {
    println("getting connection")
    val coll = MongoStorage.collection("hello")

    println("inserting")
    coll += MongoDBObject("hello" -> "again!")
  } catch {
    case e: Exception => println("err: " + e); e.printStackTrace()
  }
}