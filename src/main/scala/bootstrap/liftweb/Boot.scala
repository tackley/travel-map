package bootstrap.liftweb

import net.liftweb.common.Full
import net.liftweb.http._
import travel.loader.Loader
import travel.KmlGenerator

class Boot {
  def boot() {
    // use html5 not xhtml (lift's default) for both templates and output
    // see http://www.assembla.com/spaces/liftweb/wiki/HtmlProperties_XHTML_and_HTML5
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    LiftRules.statelessDispatchTable.append {
      case Req("test.kml" :: Nil, ext, GetRequest) => () => Full(XmlResponse(kml))
      case Req("guardian-travel.kml" :: Nil, ext, GetRequest) => () => Full(XmlResponse(KmlGenerator.kml))
    }

    Loader.start()
    LiftRules.unloadHooks.append { Loader.shutdown _ }
  }


  def kml = {
    println("hitting kml!!!")

  <kml xmlns="http://www.opengis.net/kml/2.2">
  <Placemark>
    <name>Simple placemark</name>
    <description>Attached to the ground. Intelligently places itself
       at the height of the underlying terrain.</description>
    <Point>
      <coordinates>-122.0822035425683,37.42228990140251,0</coordinates>
    </Point>
  </Placemark>
</kml>
  }
}