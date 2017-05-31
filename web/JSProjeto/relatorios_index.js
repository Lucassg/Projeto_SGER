google.charts.load('current', {packages: ['corechart']});

google.charts.setOnLoadCallback(drawChart1);
google.charts.setOnLoadCallback(drawChart2);

function drawChart1() {

    var grafico_json = new Object();
    grafico_json = pedidosFechadosJSON();
    var grafico_formatado = [];

    console.log(grafico_json);

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
        pieSliceText: 'value',
        pieSliceText: 'percentage',
        colors:['#F44336','#00796B','#1565C0']
    };

    var chart = new google.visualization.PieChart(document.getElementById('chart1'));
    chart.draw(data, options);
}

function drawChart2() {

    var grafico_json = new Object();
    grafico_json = pedidosAbertosJSON();
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
        pieSliceText: 'value',
        pieSliceText: 'percentage',
        colors:['#00796B','#1565C0']
    };
    var chart = new google.visualization.PieChart(document.getElementById('chart2'));
    chart.draw(data, options);
}
google.charts.setOnLoadCallback(drawChart2);

function pedidosAbertosJSON() {

    var arrayJSON = [];
    $.ajax({
        url: 'ServletIndex',
        data: {tipo_relatorio: "pedidos_abertos"},
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
function pedidosFechadosJSON() {

    var arrayJSON = [];
    $.ajax({
        url: 'ServletIndex',
        data: {tipo_relatorio: "pedidos_fechados"},
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
