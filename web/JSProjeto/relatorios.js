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
            $('#radiotela').hide(); 
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
        ['1', 5],
        ['2', 10],
        ['3', 9],
        ['4', 8],
        ['5', 7],
        ['6', 6],
        ['7', 5],
        ['8', 4],
        ['9', 3],
        ['10', 2],
        ['11', 1],

    ]);

    options = {
        title: 'Entregas (30 min)',
        legend: 'top',
        isStacked: true,

    };

    var chart = new google.visualization.ColumnChart(document.getElementById('divcolumn'));
    chart.draw(data, options);
    
    $('#radiotela').show();
    $('#pentregues').hide();
    $('#pentregador').hide();
    $('#pnentregues').hide();
    $('#pgerado').hide();
    $('#pnejustificativa').hide();
};

function pedidosPorEntreguador(){
    
};

function pedidosNEntregues(){
    
};

function prejuizoGerado(){
    
};

function pNEtreguePorJustificativa(){
    
};

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
