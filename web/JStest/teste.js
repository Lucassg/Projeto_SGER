google.charts.load('current', {packages: ['corechart']});
var data;
var options;
var grafico_json = new Object();
var tipo_graf;

$(document).ready(function () {
    $("input[name$='chart']").click(function () {
        var select = $(this).val();
        if (select == "column") {
            var chart = new google.visualization.ColumnChart(document.getElementById('divcolumn'));
            chart.draw(data, options);
        }
        if (select == "pie") {
            var chart = new google.visualization.PieChart(document.getElementById('divpie'));
            chart.draw(data, options);
        }
        if (select == "combo") {
            var chart = new google.visualization.ComboChart(document.getElementById('divcombo'));
            chart.draw(data, options);
        }
        if (select == "bar") {
            var chart = new google.visualization.BarChart(document.getElementById('divbar'));
            chart.draw(data, options);
        }
        $("div.graf").hide();
        $("#div" + select).show();
    });
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

function drawChart() {

    tipo_graf = "rel_coluna";

    var arrayJSON = [];
    $.ajax({
        url: 'jsonServlet',
        data: {datainicial: $('#datainicial').val(), datafinal: $('#datafinal').val()},
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (json) {
            $.each(json, function (index, pedido) {
                arrayJSON[index] = pedido;
            });
        }
    });
    grafico_json = arrayJSON;
    var grafico_formatado = [];

    console.log(grafico_json);

    $.each(grafico_json, function (i, obj) {
        grafico_formatado.push([obj.mes, obj.quantidade]);
    });
    
    data = new google.visualization.DataTable();

    data.addColumn('string', 'Mês');
    data.addColumn('number', 'Quantidade');
    data.addRows(grafico_formatado);

    options = {
        title: 'Entregas (30 min)',
        legend: 'top',
        isStacked: true
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('divcolumn'));
    chart.draw(data, options);
}


function relatorioNaoEntregues() {

}
//if (tipo_graf === "rel_coluna") {
//}

function pedidosJSON() {

    var arrayJSON = [];
    $.ajax({
        url: 'jsonServlet',
        data: {datainicial: $('#datainicial').val(), datafinal: $('#datafinal').val()},
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
}
);
