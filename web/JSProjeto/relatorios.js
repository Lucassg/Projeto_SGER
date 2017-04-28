google.charts.load('current', {packages: ['corechart']});
var arrayJSON = [];
var data;
var options;

$(document).ready(function () {
    $('#tiposrelatorios').change(function () {

        if ($('#tiposrelatorios').val() == 'Pedidos Entregues') {
            $('#pentregues').show();
            $('#pentregador').hide();
            $('#pnentregues').hide();
            $('#pgerado').hide();
            $('#pnejustificativa').hide();
            $("div.graf").hide();
            $('#radiotela').hide();
        } else if ($('#tiposrelatorios').val() == 'Pedidos Por Entregador') {
            $('#pentregador').show();
            $('#pentregues').hide();
            $('#pnentregues').hide();
            $('#pgerado').hide();
            $('#pnejustificativa').hide();
            $("div.graf").hide();
            $('#radiotela').hide();
        } else if ($('#tiposrelatorios').val() == 'Pedidos Nao Entregues') {
            $('#pnentregues').show();
            $('#pentregues').hide();
            $('#pentregador').hide();
            $('#pgerado').hide();
            $('#pnejustificativa').hide();
            $("div.graf").hide();
            $('#radiotela').hide();
        } else if ($('#tiposrelatorios').val() == 'Prejuizo Gerado') {
            $('#pgerado').show();
            $('#pentregues').hide();
            $('#pentregador').hide();
            $('#pnentregues').hide();
            $('#pnejustificativa').hide();
            $("div.graf").hide();
            $('#radiotela').hide();
        } else if ($('#tiposrelatorios').val() == 'Pedidos Nao Entregues Por Justificativa') {
            $('#pnejustificativa').show();
            $('#pentregues').hide();
            $('#pentregador').hide();
            $('#pnentregues').hide();
            $('#pgerado').hide();
            $("div.graf").hide();
            $('#radiotela').hide();
        } else {
            $('#pentregues').hide();
            $('#pentregador').hide();
            $('#pnentregues').hide();
            $('#pgerado').hide();
            $('#pnejustificativa').hide();
            $('#radiotela').hide();
            $("div.graf").hide();
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

            json_temp = arrayJSON;
            var graf_temp = [];
            
            console.log(json_temp);

            console.log($('select[name="tiposrelatorios"] option:selected').val());

            if ($('select[name="tiposrelatorios"] option:selected').val() == "Pedidos Por Entregador") {
                $.each(json_temp, function (i, obj) {
                    graf_temp.push([obj.data, obj.qtde_entregue + obj.qtde_nentregue]);
                });
                
                console.log(graf_temp);

                var data_temp = new google.visualization.DataTable();

                if ($('input[name="mesdia"]:checked').val() == "mes") {
                    data_temp.addColumn('string', 'Mês');
                    data_temp.addColumn('number', 'Pedidos');
                } else {
                    data_temp.addColumn('string', 'Dia');
                    data_temp.addColumn('number', 'Pedidos');
                }


                data_temp.addRows(graf_temp);

                var chart = new google.visualization.PieChart(document.getElementById('divpie'));
                chart.draw(data_temp, options);
            } else {

                var chart = new google.visualization.PieChart(document.getElementById('divpie'));
                chart.draw(data, options);
            }
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

function pedidosEntregues() {

    $.ajax({
        url: 'jsonServlet',
        data: {datainicial: $('#datainicial_entregue').val(),
            datafinal: $('#datafinal_entregue').val(),
            tipo_relatorio: $('#tiposrelatorios').val(),
            dia_mes: $('#mesdia:checked').val()},
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (json) {
            $.each(json, function (index, pedido) {
                arrayJSON[index] = pedido;
            });
        }
    });

    var grafico_formatado = [];

    $.each(arrayJSON, function (i, obj) {
        grafico_formatado.push([obj.data, obj.quantidade]);
    });

    data = new google.visualization.DataTable();

    if ($('input[name="mesdia"]:checked').val() == "mes") {
        data.addColumn('string', 'Mês');
        data.addColumn('number', 'Quantidade');
    } else {
        data.addColumn('string', 'Dia');
        data.addColumn('number', 'Quantidade');
    }

    data.addRows(grafico_formatado);

    options = {
        title: 'Pedidos Entregues',
        legend: 'top',
        isStacked: true,
        height: 500,
        width: 950,
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('divcolumn'));
    chart.draw(data, options);

    $('#radiotela').show();
    $('#pentregues').hide();
    $('#pentregador').hide();
    $('#pnentregues').hide();
    $('#pgerado').hide();
    $('#pnejustificativa').hide();
    $("#divcolumn").show();
}
;

function pedidosPorEntreguador() {

    $.ajax({
        url: 'jsonServlet',
        data: {datainicial: $('#datainicial_entregador').val(),
            datafinal: $('#datafinal_entregador').val(),
            tipo_relatorio: $('#tiposrelatorios').val(),
            dia_mes: $('#mesdia:checked').val(),
            entregador: $('#entregador').val()},
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (json) {
            $.each(json, function (index, pedido) {
                arrayJSON[index] = pedido;
            });
        }
    });

    console.log(arrayJSON);

    var grafico_formatado = [];

    $.each(arrayJSON, function (i, obj) {
        grafico_formatado.push([obj.data, obj.qtde_entregue, obj.qtde_nentregue]);
    });

    console.log(grafico_formatado);

    data = new google.visualization.DataTable();

    if ($('input[name="mesdia"]:checked').val() == "mes") {
        data.addColumn('string', 'Mês');
        data.addColumn('number', 'Entregues');
        data.addColumn('number', 'Não Entregues');
    } else {
        data.addColumn('string', 'Dia');
        data.addColumn('number', 'Entregues');
        data.addColumn('number', 'Não Entregues');
    }

    data.addRows(grafico_formatado);

    options = {
        title: 'Pedidos Não Entregues',
        legend: 'top',
        isStacked: true,
        height: 500,
        width: 950,
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('divcolumn'));
    chart.draw(data, options);

    $('#radiotela').show();
    $('#pentregues').hide();
    $('#pentregador').hide();
    $('#pnentregues').hide();
    $('#pgerado').hide();
    $('#pnejustificativa').hide();
    $("#divcolumn").show();


}
;

function pedidosNEntregues() {

    $.ajax({
        url: 'jsonServlet',
        data: {datainicial: $('#datainicial_nao_entregue').val(),
            datafinal: $('#datafinal_nao_entregue').val(),
            tipo_relatorio: $('#tiposrelatorios').val(),
            dia_mes: $('#mesdia:checked').val()},
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (json) {
            $.each(json, function (index, pedido) {
                arrayJSON[index] = pedido;
            });
        }
    });

    var grafico_formatado = [];

    $.each(arrayJSON, function (i, obj) {
        grafico_formatado.push([obj.data, obj.quantidade]);
    });

    data = new google.visualization.DataTable();

    if ($('input[name="mesdia"]:checked').val() == "mes") {
        data.addColumn('string', 'Mês');
        data.addColumn('number', 'Quantidade');
    } else {
        data.addColumn('string', 'Dia');
        data.addColumn('number', 'Quantidade');
    }

    data.addRows(grafico_formatado);

    options = {
        title: 'Pedidos Por Entregador',
        legend: 'top',
        isStacked: true,
        height: 600,
        width: 1000,
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('divcolumn'));
    chart.draw(data, options);

    $('#radiotela').show();
    $('#pentregues').hide();
    $('#pentregador').hide();
    $('#pnentregues').hide();
    $('#pgerado').hide();
    $('#pnejustificativa').hide();
    $("#divcolumn").show();
}
;

function prejuizoGerado() {

}
;

function pNEtreguePorJustificativa() {

}
;

$('.datetimepicker').datetimepicker({
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
