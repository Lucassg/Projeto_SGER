google.charts.load('current', {packages: ['corechart']});
var point1, point2, dataArray = [];

function drawChart() {

    var teste = pedidosJSON();
    var teste2 = [];

    $.each(teste, function (i, obj) {
        teste2.push([obj.mes, obj.quantidade]);
    });

    console.log(teste2);

    var data = new google.visualization.DataTable();

    data.addColumn('string', 'Mês');
    data.addColumn('number', 'Quantidade');
    data.addRows(teste2);

    var options = {
        title: 'Entregas (30 min)'
    };

    // Instantiate and draw the chart.
    var chart = new google.visualization.ColumnChart(document.getElementById('container'));
    chart.draw(data, options);
}
google.charts.setOnLoadCallback(drawChart);

function pedidosJSON() {

    var arrayJSON = [];
    $.ajax({
        url: 'JSON/counts.json',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (json) {
            $.each(json, function (index, pedido) {
                arrayJSON[index] = pedido;
            });
        }
    });
    return arrayJSON;
}

$(function () {
    $("#datainicial").datepicker();
});
$(function () {
    $("#datafinal").datepicker();
});

$(document).ready(function () {
    $('#tiposrelatorios').change(function () {
        
        if ($('#tiposrelatorios').val() == 'Pedidos Nao Entregues' || $('#tiposrelatorios').val() == 'Pedidos Entregues') {
            $('#data').show();
        } else {
            $('#data').hide();
        }
        
        if ($('#tiposrelatorios').val() == 'Pedidos Por Entregador') {
            $('#entregadores').show();
        } else {
            $('#entregadores').hide();
        }
    });
});