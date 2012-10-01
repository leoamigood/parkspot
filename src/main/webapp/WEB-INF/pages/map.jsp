<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript">
        function initialize() {
            var myLatlng = new google.maps.LatLng(${location.latitude}, ${location.longitude});
            var mapOptions = {
                zoom: 16,
                center: myLatlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
            }
            var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);

            var marker = new google.maps.Marker({
              position: myLatlng,
              map: map,
              title:"Hello World!"
            });

            marker.setMap(map);

            var result = $.getJSON('/parkspot/location/${location.latitude},${location.longitude}',
            function(data, textStatus, jqXHR){
                $.each(data, function(key, val) {
                    alert(key + val);
                });
            });
        }
     </script>

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        html { height: 100% }
        body { height: 100%; margin: 0; padding: 0 }
        #map_canvas { height: 100% }
    </style>

    <script type="text/javascript"
        src="http://maps.googleapis.com/maps/api/js?key=${apiKey}&sensor=false">
    </script>
<body onload="initialize()">
    <div id="map_canvas" style="width:100%; height:100%"></div>
</body>
</html>