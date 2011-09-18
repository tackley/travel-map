package travel.loader

import org.joda.time.DateTime
import com.novus.salat.annotations._
import com.novus.salat.global._
import com.novus.salat.dao.SalatDAO
import travel.MongoStorage

case class ContentItem(
  @Key("_id") id: String,
  url: String, webTitle: String, trailText: Option[String], thumbnail: Option[String],
  lat: Option[Double], long: Option[Double],
  lastModified: DateTime) {

  def hasGeo = lat.isDefined && long.isDefined
  def isInteresting = hasGeo && trailText.isDefined
}

object ContentItem extends SalatDAO[ContentItem, String](collection = MongoStorage.collection("content")) {

}
