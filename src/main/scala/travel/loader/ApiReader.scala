package travel.loader

import net.liftweb.util.Helpers._
import org.joda.time.DateTime
import com.gu.openplatform.contentapi.Api
import com.gu.openplatform.contentapi.connection.JavaNetHttp
import com.gu.openplatform.contentapi.model.Content
import org.joda.time.format.ISODateTimeFormat
import com.weiglewilczek.slf4s.Logging


object ApiReader {
  object Api extends Api with JavaNetHttp with Logging{
    override def GET(urlString: String, headers: Iterable[(String, String)]) = {
      logger.debug("API: " + urlString)
      super.GET(urlString, headers)
    }
  }

  def contentModifiedSince(dt: DateTime) = {
    val results: List[Content] = Api.item.itemId("travel/travel").fromDate(dt).useDate("last-modified")
      .showFields("latitude,longitude,lastModified,trailText,thumbnail")
      .orderBy("oldest").pageSize(50).results

    for {
      content <- results
      fields <- content.fields
      lastMod <- fields.get("lastModified")
    } yield {
      ContentItem(
        id = content.id,
        webTitle = content.webTitle,
        url = content.webUrl,
        trailText = fields.get("trailText"),
        thumbnail = fields.get("thumbnail"),
        lat = fields.get("latitude").map(_.toDouble),
        long = fields.get("longitude").map(_.toDouble),
        lastModified = ISODateTimeFormat.dateTimeParser().parseDateTime(lastMod)
        )
    }

  }

}