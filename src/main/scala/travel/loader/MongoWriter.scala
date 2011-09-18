package travel.loader

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.MongoDBObject
import org.joda.time.DateTime
import com.weiglewilczek.slf4s.Logging

object MongoWriter extends Logging {
  def write(items: List[ContentItem]) {
    logger.info("Writing %d items to mongo..." format items.size)
    items foreach ContentItem.save
  }

  def mostRecentLastModified = {
    ContentItem.find(MongoDBObject())
      .sort(orderBy = MongoDBObject("lastModified" -> -1))
      .limit(1)
      .toList
      .headOption
      .map(_.lastModified)
      // why this magic date?
      // from looking at the content api data, nothing is geotagged before this date.
      // presumably the feature did not exist.
      .getOrElse(new DateTime(2008, 10, 1, 0, 0, 0, 0))
  }

}