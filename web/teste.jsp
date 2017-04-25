<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="CSSProjeto/jquery.datetimepicker.css">
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        <br>
        <div style="float: left">
            <select id="tiposrelatorios">
                <option value=" "></option>
                <option value="Pedidos Entregues">Pedidos Entregues</option>
                <option value="Pedidos Nao Entregues">Pedidos Nao Entregues</option>
                <option value="Pedidos Por Entregador">Pedidos por Entregador</option>
                <option value="Pedidos Por Entregador">Prejuízo Gerado</option>
                <option value="Pedidos Por Entregador">Pedidos não Entregues por Justificativa</option>
            </select>

            <div id="data" style="display: none;">
                <form name="datas" action="Controle?classe=ControleLogicoRelatorio&acao=gera_relatorio" method="POST">
                    <p >Data Inicial: <input type="text" id="datainicial" name="datainicial"/></p>
                    <p >Data Final: <input type="text" id="datafinal" name="datafinal"/></p>
                    <!--&nbsp; <a href="#"><input class="btn btn-primary btn-xs" type="submit" value="Buscar" name="buscar"/>removendo botão da tela-->
                </form>
            </div>

            <div id="entregadores" style="display: none;">
                <br>
                <form name="datas" action="Controle?classe=ControleLogicoRelatorio&acao=relatorio_entregador" method="POST">
                    <select name="entregador">
                        <option value="null"></option>
                        <c:forEach items="${ListaEntregadores}" var="Entregador">
                            $(Entregador.nome)
                            <option value="${Entregador.id}">${Entregador.nome}</option>
                        </c:forEach>
                    </select>
                </form>
            </div>
            <input id="Buscar1" class="btn btn-primary btn-xs" type="submit" value="Buscar1" name="buscar1" onclick="drawChart()"/>
        </div>
        <div id="divpie" class="graf" style="background: #000; float: top; width: 600px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="divcombo" class="graf" style="background: #007fff; float: top; display: none; width: 600px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="divbar" class="graf" style="background: #00dd1c; float: top; display: none; width: 600px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="divcolumn" class="graf" style="background: #761c19; float: top; display: none; width: 600px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="divgeo" class="graf" style="background: yellow; float: top; display: none; width: 600px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="div6" style="float: bottom; width: 400px; margin: 0 auto">
            <form action="" >
                <input id="pie" type="radio" name="chart" value="pie"> Pie
                <input id="combo" type="radio" name="chart" value="combo"> Combo
                <input id="bar" type="radio" name="chart" value="bar"> Bar
                <input id="column" type="radio" name="chart" value="column"> Column
                <input id="geo" type="radio" name="chart" value="geo"> Geo
            </form>
        </div>
    </body>
    <script type="text/javascript" src="JSProjeto/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="JSProjeto/jquery-ui.min.js"></script>
    <script type="text/javascript" src="JSProjeto/jquery.datetimepicker.min.js"></script>
    <script type="text/javascript" src="JSProjeto/jquery.datetimepicker.full.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="JStest/teste.js"></script>
</html>
