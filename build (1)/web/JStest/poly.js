// This example creates a simple polygon representing the Bermuda Triangle.

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: {lat: -22.903857, lng: -47.060312},
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    // Define the LatLng coordinates for the polygon's path.
    var triangleCoords = [
        {lat: -22.902, lng: -47.057},
        {lat: -22.901, lng: -47.057},
        {lat: -22.897, lng: -47.058},
        {lat: -22.897, lng: -47.058},
        {lat: -22.894, lng: -47.061},
        {lat: -22.893, lng: -47.061},
        {lat: -22.888, lng: -47.058},
        {lat: -22.888, lng: -47.056},
        {lat: -22.887, lng: -47.051},
        {lat: -22.890, lng: -47.051},
        {lat: -22.890, lng: -47.052},
        {lat: -22.895, lng: -47.052},
        {lat: -22.896, lng: -47.051},
        {lat: -22.895, lng: -47.049},
        {lat: -22.898, lng: -47.050},
        {lat: -22.898, lng: -47.050},
        {lat: -22.901, lng: -47.049},
        {lat: -22.900, lng: -47.050},
        {lat: -22.900, lng: -47.052},
        {lat: -22.902, lng: -47.057}
    ];

    var triangleCoords2 = [
        {lat: 25.774, lng: -80.190},
        {lat: 18.466, lng: -66.118},
        {lat: 32.321, lng: -64.757}
    ];

    // Construct the polygon.
    var bermudaTriangle = new google.maps.Polygon({
        paths: triangleCoords,
        strokeColor: '#62BE44', //#FF0000 #62be44
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: '#62BE44',
        fillOpacity: 0.35
    });
    
        var bermudaTriangle2 = new google.maps.Polygon({
        paths: triangleCoords2,
        strokeColor: '#FF0000', //#FF0000 #62be44
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: '#FF0000',
        fillOpacity: 0.35
    });
    
    bermudaTriangle.setMap(map);
    bermudaTriangle2.setMap(map);
}