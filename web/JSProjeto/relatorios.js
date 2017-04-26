google.charts.load('current', {packages: ['corechart']});
var grafico_json;
var data;
var options;

$(document).ready(function () {
    $('#tiposrelatorios').change(function () {
        
        if ($('#tiposrelatorios').val() == 'Pedidos Entregues'){
            $('#pentregues').show();
            $('#pentregador').hide();
            $('#pnentregues').hide();
            $('#pgerado').hide();
            $('#pnejustificativa').hide();
        } else if ($('#tiposrelatorios').val() == 'Pedidos Por Entregador'){
            $('#pentregador').show();
            $('#pentregues').hide();
            $('#pnentregues').hide();
            $('#pgerado').hide();
            $('#pnejustificativa').hide();
        } else if ($('#tiposrelatorios').val() == 'Pedidos Nao Entregues'){
            $('#pnentregues').show();
            $('#pentregues').hide();
            $('#pentregador').hide();
            $('#pgerado').hide();
            $('#pnejustificativa').hide();
        } else if ($('#tiposrelatorios').val() == 'Prejuizo Gerado'){
            $('#pgerado').show();
            $('#pentregues').hide();
            $('#pentregador').hide();
            $('#pnentregues').hide();
            $('#pnejustificativa').hide();
        } else if ($('#tiposrelatorios').val() == 'Pedidos Nao Entregues Por Justificativa'){
            $('#pnejustificativa').show();
            $('#pentregues').hide();
            $('#pentregador').hide();
            $('#pnentregues').hide();
            $('#pgerado').hide();
        } else {
            $('#pentregues').hide();
            $('#pentregador').hide();
            $('#pnentregues').hide();
            $('#pgerado').hide();
            $('#pnejustificativa').hide();
        }        
    });
});

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

function pedidosEntregues(){
    
      data = new google.visualization.arrayToDataTable([
        ['Dias', 'Quantidade'],
        ['1', 10],
        ['2', 10],
        ['3', 10],
        ['4', 10],
        ['5', 10],
        ['6', 10],
        ['7', 10],
        ['8', 10],
        ['8', 10],
        ['9', 10],
        ['10', 10],
        ['11', 10],
        ['12', 10],
        ['13', 10],
        ['14', 10],
        ['15', 10],
        ['16', 10],
        ['17', 10],
        ['18', 10],
        ['19', 10],
        ['20', 10],
        ['21', 10],
        ['22', 10],
        ['23', 10],
        ['24', 10],
        ['25', 10],
        ['26', 10],
        ['27', 10],
        ['28', 10],
        ['29', 10],
        ['30', 10],
    ]);

    options = {
        title: 'Entregas (30 min)',
        legend: 'top',
        isStacked: true
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('divcolumn'));
    chart.draw(data, options); 
    $('#divcolumn').show()
};

function pedidosPorEntreguador(){
    
};

function pedidosNEntregues(){
    
};

function prejuizoGerado(){
    
};

function pNEtreguePorJustificativa(){
    
};


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
