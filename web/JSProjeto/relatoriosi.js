google.charts.load('current', {packages: ['corechart']});

google.charts.setOnLoadCallback(drawChart1);
google.charts.setOnLoadCallback(drawChart2);

function drawChart1() {
    
    var data = new google.visualization.arrayToDataTable([
        ['Status', 'Quantidade'],
        ['Entregue', 27],
        ['Nao Entregue', 8],
        ['Cancelado', 3], 
    ]);

    var options = {
        title: 'Pedidos Fechados',
        legend:'top',
    };

    var chart = new google.visualization.PieChart(document.getElementById('chart1'));
    chart.draw(data, options);
}


function drawChart2() {
    
    var data = new google.visualization.arrayToDataTable([
        ['Status', 'Quantidade'],
        ['Aguardando Entrega', 7],
        ['Entrega em Andamento', 9],
    ]);

    var options = {
        title: 'Pedidos Abertos',
        legend:'top',
    };

    var chart = new google.visualization.PieChart(document.getElementById('chart2'));
    chart.draw(data, options);
}