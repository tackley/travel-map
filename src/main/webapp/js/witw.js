$(document).ready(function() {
  var myOptions = {
    zoom: 2,
    mapTypeId: google.maps.MapTypeId.TERRAIN
  };

 var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

  var ctaLayer = new google.maps.KmlLayer('http://tackley.github.com/test3.kml');
  ctaLayer.setMap(map);
});

