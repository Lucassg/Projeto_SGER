window.exibeRotaMultiplosPontos = function () {
    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer;
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 6
    });
    directionsDisplay.setMap(map);
    criaParadas(directionsService, directionsDisplay);
    google.maps.event.addDomListener(map, 'load', exibeRotaMultiplosPontos);
}

function criaParadas(directionsService, directionsDisplay) {
    var waypts = [];
    var enderecosJSON = buscaEnderecosJSON();
    var end_estabelecimento = "Km 133, Rod. Dom Pedro I - Jardim Nilópolis, Campinas - SP, 13097-670, Brasil";
    
    for (var i = 0; i < enderecosJSON.length; i++) {
        waypts.push({
            location: enderecosJSON[i],
            stopover: true
        });
    }

    directionsService.route({
        origin: end_estabelecimento,
        destination: end_estabelecimento,
        waypoints: waypts,
        optimizeWaypoints: true,
        travelMode: google.maps.TravelMode.DRIVING
    }, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        } else {
            window.alert('O Pedido Directions falhou devido ' + status);
        }
    });
}

function buscaEnderecosJSON() {

    var arrayJSON = [];
    $.ajax({
        url: 'JSON/enderecos_multiplos_pontos.json',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (json) {
            $.each(json, function (index, endereco) {
                arrayJSON[index] = endereco;
            });
        }
    });
    return arrayJSON;
}