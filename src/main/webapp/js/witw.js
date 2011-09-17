$(document).ready(function() {
  var chicago = new google.maps.LatLng(41.875696,-87.624207);
  var myOptions = {
    zoom: 2,
    center: chicago,
    mapTypeId: google.maps.MapTypeId.TERRAIN
  };



  var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

  var ctaLayer = new google.maps.KmlLayer('http://localhost:8080/test.kml');
  ctaLayer.setMap(map);
});

