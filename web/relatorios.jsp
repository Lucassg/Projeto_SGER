<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="CSSProjeto/jquery.datetimepicker.css">

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=index">IN�CIO</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoPedido&acao=listar_pedido">PEDIDOS</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoCliente&acao=listar_cliente">CLIENTES</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoRota&acao=listar_pedido_entregar">ENTREGAS</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=rotas">ROTAS</a></li>
            </ul>
            <c:if test="${funcionario.funcao == 'gerente'}">
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoProduto&acao=listar_produto">PRODUTOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="Controle?classe=ControleLogicoRedirecionamento&page=relatorios">RELAT�RIOS<span class="sr-only">(current)</span></a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCION�RIOS</a></li>
                </ul>
            </c:if>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"><center><b> SGER - Sistema de Gerenciamento de Entregas </b></br>para Restaurantes</center></h1>
            <div class="tiporelt">
                <b>Tipo de Relat�rio:</b>
                <select id="tiposrelatorios">
                    <option value="null"></option>
                    <option value="Pedidos Entregues">Pedidos Entregues</option>
                    <option value="Pedidos Por Entregador">Pedidos Por Entregador</option>
                    <option value="Pedidos Nao Entregues">Pedidos N�o Entregues</option> 
                    <option value="Prejuizo Gerado">Preju�zo Gerado</option>
                    <option value="Pedidos Nao Entregues Por Justificativa">Pedidos N�o Entregues Por Justificativa</option>
                </select>
            </div>
            <div class="tiporelt">
                <div id="pentregues" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relat�rio" onclick="pedidosEntregues()"/>
                </div>
                <div id="pentregador" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relat�rio" onclick="pedidosPorEntreguador()"/>
                </div>
                <div id="pnentregues" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relat�rio" onclick="pedidosNEntregues()"/>
                </div>
                <div id="pgerado" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relat�rio" onclick="prejuizoGerado()"/>
                </div>
                <div id="pnejustificativa" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relat�rio" onclick="pNEtreguePorJustificativa()"/>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div id="divcolumn" class="graf"></div>
            <div id="divcombo" class="graf"></div>
            <div id="divbar" class="graf"></div>
            <div id="divpie" class="graf"></div>
            <div id="radiotela" class="hidediv">
                <input id="pie" type="radio" name="chart" value="column" CHECKED> Coluna
                <input id="combo" type="radio" name="chart" value="combo"> Combo
                <input id="bar" type="radio" name="chart" value="bar"> Bar
                <input id="column" type="radio" name="chart" value="pie"> Pizza
            </div>
        </div>
    </div>	
</div>
<script type="text/javascript" src="JSProjeto/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="JSProjeto/jquery-ui.min.js"></script>
<script type="text/javascript" src="JSProjeto/jquery.datetimepicker.min.js"></script>
<script type="text/javascript" src="JSProjeto/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="JSProjeto/relatorios.js"></script>

<jsp:include page="footer.jsp"/>
