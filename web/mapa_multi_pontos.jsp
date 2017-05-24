<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            html, body { height: 100%; margin: 0; padding: 0; }
            #map { height: 100%; }
        </style>
    </head>
    <body>
        <div id="map"></div>
        <!--
        CSS do mapa caso seja necessário ver a output na página
        style="height: 90%; width: 90%"
        -->
        <script src="JSProjeto/jquery-3.1.1.min.js" type="text/javascript" async="true"></script>
        <script src="JSProjeto/maps_multiplos_pontos.js" type="text/javascript" async="true"></script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCj0AreC_ic-Nrs3BBrPuNmBaaTeaGr-eQ&callback=exibeRotaMultiplosPontos">
        </script>
    </body>
</html>

<jsp:include page="footer.jsp"/>