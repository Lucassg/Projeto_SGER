google.charts.load('current', {packages: ['corechart']});

//google.charts.setOnLoadCallback(drawChart1);
google.charts.setOnLoadCallback(drawChart2);
//
//function drawChart1() {
//    
//    var data = new google.visualization.arrayToDataTable([
//        ['Status', 'Quantidade'],
//        ['Entregue', 27],
//        ['Nao Entregue', 8],
//        ['Cancelado', 3], 
//    ]);
//
//    var options = {
//        title: 'Pedidos Fechados',
//        titleTextStyle:{fontSize: 32, bold:true},
//        legend:'top',
//        pieSliceText:'value',
//        pieSliceText:'percentage'
//    };
//
//    var chart = new google.visualization.PieChart(document.getElementById('chart1'));
//    chart.draw(data, options);
//}

document.getElementById("Buscar1").onclick = drawChart2;

function drawChart2() {

    var grafico_json = pedidosabertosJSON();
    var grafico_formatado = [];

    $.each(grafico_json, function (i, obj) {
        grafico_formatado.push([obj.status, obj.quantidade]);
    });

    console.log(grafico_formatado);

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
