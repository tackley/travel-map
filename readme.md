Where in the World?
===================

This project is a quick experiment to see whether a maps-style interface can improve accessiblity of
some of the Guardian's travel writing.  Some of the content is already geo-tagged, so this app just
takes those geotags and links to the articles from a google map. It scans for new content in the API
and will include that automatically, though Google heavily cache the map overlays so I'm not sure what
the latency is yet.

The Tech
--------

There are around [33,000 items of guardian content tagged travel](http://content.guardianapis.com/travel/travel),
though many of these are not geotagged.

The API only lets you query for 50 at once, and cannot (currently) filter on only geotagged items.

So, a background process scans for modified content, and maintains a database in MongoDB. The runtime app
uses this database to build a [KML file](http://code.google.com/apis/kml/documentation/)
that [Google Maps can use as an overlay](http://code.google.com/apis/maps/documentation/javascript/overlays.html#KMLLayers)

The app itself is implemented in Scala, using [akka](http://akka.io) for background scheduling,
[casbah](https://github.com/mongodb/casbah)
and [salat](https://github.com/novus/salat) to talk to mongo, [the lift web framework](http://liftweb.net), the Guardian's
[Content API scala client](https://github.com/guardian/open-platform-content-api-scala-client) and 
[Bootstrap, from Twitter](http://twitter.github.com/bootstrap/).

Right now, it's hosted - for free - by [CloudBees](http://www.cloudbees.com) and [MongoHQ](http://mongohq.com).
You can see it running at [here](http://travel-map.tackers.cloudbees.net/).


Important Disclaimer
-------------------

Although I work for the Guardian, and helped implement the Open Platform Content API, this
is entirely a personal project and has no connection at all with the Guardian.





