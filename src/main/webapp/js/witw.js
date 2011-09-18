$(document).ready(function() {
  var myOptions = {
    zoom: 2,
    mapTypeId: google.maps.MapTypeId.TERRAIN
  };

 var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

  var ctaLayer = new google.maps.KmlLayer('http://travel-map.tackers.cloudbees.net/guardian-travel.kml');
  ctaLayer.setMap(map);
});

