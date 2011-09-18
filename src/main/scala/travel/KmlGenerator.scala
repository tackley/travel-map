package travel

import loader.ContentItem
import com.mongodb.casbah.commons.MongoDBObject
import xml.{NodeSeq, Unparsed}

object KmlGenerator {
  def kml = {
    println("generating real kml!!!")

    <kml xmlns="http://www.opengis.net/kml/2.2">
      <Document>
        <name>Guardian Travel Content</name>
        <description>The Guardian's GeoTagged Travel Content</description>

        {
          for {
            c <- ContentItem.find(MongoDBObject())
            lat <- c.lat
            long <- c.long
          } yield {
            <Placemark>
              <name>{c.webTitle}</name>
              <description>
                { img(c.thumbnail) + c.trailText.getOrElse("") + link(c.url)}
              </description>
              <Point>
                <coordinates>{long},{lat},0</coordinates>
              </Point>
            </Placemark>
          }
        }
      </Document>
    </kml>

  }

  def link(url: String) = (<p><a href={url}>See the whole article on the Guardian.</a></p>).toString()
  def img(thumbnail: Option[String]) = thumbnail.map(t=> <p><img src={t} width="140" height="84" /></p>.toString()) getOrElse ""

}