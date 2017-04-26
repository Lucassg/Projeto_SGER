google.charts.load('current', {packages: ['corechart']});

google.charts.setOnLoadCallback(drawChart1);
google.charts.setOnLoadCallback(drawChart2);

document.getElementById("Buscar").onclick = drawChart1;

function drawChart1() {

    var grafico_json = new Object();
    grafico_json = pedidosabertosJSON();
    var grafico_formatado = [];

    for (var i in grafico_json) {
        grafico_formatado.push([i, grafico_json[i]]);
    }

    var data = new google.visualization.DataTable();

    data.addColumn('string', 'Status');
    data.addColumn('number', 'Quantidade');
    data.addRows(grafico_formatado);


    var options = {
        title: 'Pedidos Fechados',
        titleTextStyle: {fontSize: 32, bold: true},
        legend: 'top',
    };
    var chart = new google.visualization.PieChart(document.getElementById('chart1'));
    chart.draw(data, options);
}
google.charts.setOnLoadCallback(drawChart1);

document.getElementById("Buscar1").onclick = drawChart2;

function drawChart2() {

    var grafico_json = new Object();
    grafico_json = pedidosabertosJSON();
    var grafico_formatado = [];

    for (var i in grafico_json) {
        grafico_formatado.push([i, grafico_json[i]]);
    }

    var data = new google.visualization.DataTable();

    data.addColumn('string', 'Status');
    data.addColumn('number', 'Quantidade');
    data.addRows(grafico_formatado);


    var options = {
        title: 'Pedidos Abertos',
        titleTextStyle: {fontSize: 32, bold: true},
        legend: 'top',
    };
    var chart = new google.visualization.PieChart(document.getElementById('chart2'));
    chart.draw(data, options);
}
google.charts.setOnLoadCallback(drawChart2);

function pedidosabertosJSON() {

    var arrayJSON = [];
    $.ajax({
        url: 'jsonServleti',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (json) {
            $.each(json, function (index, pedido) {
                arrayJSON[index] = pedido;
//                console.log(arrayJSON[index]);
            });
        }
    });
    return arrayJSON;
}

function pedidosfechadosJSON() {

    var arrayJSON = [];
    $.ajax({
        url: 'jsonServleti2',
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
