google.charts.load('current', {packages: ['corechart']});
var point1, point2, dataArray = [];

document.getElementById("Buscar1").onclick = drawChart;

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

    var chart = new google.visualization.ColumnChart(document.getElementById('container'));
    chart.draw(data, options);
}
google.charts.setOnLoadCallback(drawChart);

function pedidosJSON() {

    var arrayJSON = [];
    $.ajax({
        //url: './JSON/counts.json',
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

    startDate: false, // new Date(), '1986/12/08', '-1970/01/05','-1970/01/05', 

    step: 60,
    monthChangeSpinner: true,
    closeOnDateSelect: false,
    closeOnWithoutClick: true,
    closeOnInputClick: true,

    timepicker: true,
    datepicker: true,

    defaultTime: false, // use formatTime format (ex. '10:00' for formatTime:	'H:i')
    defaultDate: false, // use formatDate format (ex new Date() or '1986/12/08' or '-1970/01/05' or '-1970/01/05')

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

    startDate: false, // new Date(), '1986/12/08', '-1970/01/05','-1970/01/05', 

    step: 60,
    monthChangeSpinner: true,
    closeOnDateSelect: false,
    closeOnWithoutClick: true,
    closeOnInputClick: true,

    timepicker: true,
    datepicker: true,

    defaultTime: false, // use formatTime format (ex. '10:00' for formatTime:	'H:i')
    defaultDate: false, // use formatDate format (ex new Date() or '1986/12/08' or '-1970/01/05' or '-1970/01/05')

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