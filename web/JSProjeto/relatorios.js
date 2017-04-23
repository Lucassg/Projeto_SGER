google.charts.load('current', {packages: ['corechart']});
var grafico_json;

document.getElementById("Buscar1").onclick = drawChart;

document.getElementById("Buscar2").onclick = imprime;

function imprime(){
    console.log(grafico_json);
}

function drawChart() {

    grafico_json = pedidosJSON();
    var grafico_formatado = [];

    $.each(grafico_json, function (i, obj) {
        grafico_formatado.push([obj.mes, obj.quantidade]);
    });
    
    var data = new google.visualization.DataTable();

    data.addColumn('string', 'Mês');
    data.addColumn('number', 'Quantidade');
    data.addRows(grafico_formatado);

    var options = {
        title: 'Entregas (30 min)',
        legend: 'top',
        isStacked: true
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('container'));
    chart.draw(data, options);
}
google.charts.setOnLoadCallback(drawChart);

function pedidosJSON() {

    var arrayJSON = [];
    $.ajax({
        url: 'jsonServlet',
        data: {datainicial : $('#datainicial').val(), datafinal : $('#datafinal').val()},
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (json) {
            $.each(json, function (index, pedido) {
                arrayJSON[index] = pedido;
                console.log(arrayJSON[index]);
            });
        }
    });
    return arrayJSON;
}

$('#datainicial').datetimepicker({
    inline: true,
    lang: 'pt-BR',

    format: 'Y-m-d H:i:s',

    startDate: false, 

    step: 60,
    monthChangeSpinner: true,
    closeOnDateSelect: false,
    closeOnWithoutClick: true,
    closeOnInputClick: true,

    timepicker: true,
    datepicker: true,

    defaultTime: false,
    defaultDate: false,

    minDate: false,
    maxDate: false,
    minTime: false,
    maxTime: false,

    opened: false,
    initTime: true,
    inline: false,

    withoutCopyright: true,

    inverseButton: false,
    hours12: false,
    next: 'xdsoft_next',
    prev: 'xdsoft_prev',
    dayOfWeekStart: 0,

    timeHeightInTimePicker: 25,

    todayButton: true, // 2.1.0
    defaultSelect: true, // 2.1.0

    scrollMonth: true,
    scrollTime: true,
    scrollInput: true,

    lazyInit: false,

    mask: false,
    validateOnBlur: true,
    allowBlank: true,

    yearStart: 1950,
    yearEnd: 2050,

    style: '',
    id: '',

    fixed: false,

    roundTime: 'ceil', // ceil, floor
    className: '',

    yearOffset: 0,
    beforeShowDay: null
});

$('#datafinal').datetimepicker({
    inline: true,
    lang: 'pt-BR',

    format: 'Y-m-d H:i:s',

    startDate: false, 

    step: 60,
    monthChangeSpinner: true,
    closeOnDateSelect: false,
    closeOnWithoutClick: true,
    closeOnInputClick: true,

    timepicker: true,
    datepicker: true,

    defaultTime: false, 
    defaultDate: false, 

    minDate: false,
    maxDate: false,
    minTime: false,
    maxTime: false,

    opened: false,
    initTime: true,
    inline: false,

    withoutCopyright: true,

    inverseButton: false,
    hours12: false,
    next: 'xdsoft_next',
    prev: 'xdsoft_prev',
    dayOfWeekStart: 0,

    timeHeightInTimePicker: 25,

    todayButton: true, 
    defaultSelect: true, 

    scrollMonth: true,
    scrollTime: true,
    scrollInput: true,

    lazyInit: false,

    mask: false,
    validateOnBlur: true,
    allowBlank: true,

    yearStart: 1950,
    yearEnd: 2050,

    style: '',
    id: '',

    fixed: false,

    roundTime: 'ceil',
    className: '',

    yearOffset: 0,
    beforeShowDay: null
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