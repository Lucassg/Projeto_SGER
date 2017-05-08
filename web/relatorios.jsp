<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="CSSProjeto/jquery.datetimepicker.css">

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=index">INÍCIO</a></li>
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
                    <li class="active"><a href="Controle?classe=ControleLogicoRelatorio&acao=consulta_entregador">RELATÓRIOS<span class="sr-only">(current)</span></a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"><center><b> SGER - Sistema de Gerenciamento de Entregas </b></br>para Restaurantes</center></h1>
            <div class="tiporelt">
                <b>Tipo de Relatório:</b>
                <select id="tiposrelatorios" name="tiposrelatorios">
                    <option name="tipo_rel" value="null"></option>
                    <option name="tipo_rel" value="Pedidos Entregues">Pedidos Entregues</option>
                    <option name="tipo_rel" value="Pedidos Por Entregador">Pedidos Por Entregador</option>
                    <option name="tipo_rel" value="Pedidos Nao Entregues">Pedidos Não Entregues</option> 
                    <option name="tipo_rel" value="Prejuizo Gerado">Prejuízo Gerado</option>
                    <option name="tipo_rel" value="Pedidos Nao Entregues Por Justificativa">Pedidos Não Entregues Por Justificativa</option>
                </select>
            </div>
            <div class="tiporelt">
                <div id="pentregues" class="hidediv">
                    <b>Data Inicial: <input type="text" id="datainicial_entregue" name="datainicial_entregue" class="datetimepicker"/></b> 
                    <b>Data Final: <input type="text" id="datafinal_entregue" name="datafinal_entregue" class="datetimepicker"/></b> 
                    <input id="mesdia" type="radio" name="mesdia" value="mes" checked> Mês
                    <input id="mesdia" type="radio" name="mesdia" value="dia"> Dia
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="pedidosEntregues()"/>
                </div>
                <div id="pentregador" class="hidediv">
                    <form name="datas" action="Controle?classe=ControleLogicoRelatorio&acao=relatorio_entregador" method="POST">
                        <b>Entregador: </b>
                        <select id="entregador" name="entregador">
                            <option value="null"></option>
                            <c:forEach items="${ListaEntregadores}" var="Entregador">
                                <option value="${Entregador.id}">${Entregador.nome}</option>
                            </c:forEach>
                        </select>
                    </form>
                    <b>Data Inicial: <input type="text" id="datainicial_entregador" name="datainicial_entregador" class="datetimepicker"/></b> 
                    <b>Data Final: <input type="text" id="datafinal_entregador" name="datafinal_entregador" class="datetimepicker"/></b> 
                    <input id="mesdia" type="radio" name="mesdia" value="mes" checked> Mês
                    <input id="mesdia" type="radio" name="mesdia" value="dia"> Dia
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="pedidosPorEntregador()"/>
                </div>
                <div id="pnentregues" class="hidediv">
                    <b>Data Inicial: <input type="text" id="datainicial_nao_entregue" name="datainicial_nao_entregue" class="datetimepicker"/></b> 
                    <b>Data Final: <input type="text" id="datafinal_nao_entregue" name="datafinal_nao_entregue" class="datetimepicker"/></b>
                    <input id="mesdia" type="radio" name="mesdia" value="mes" checked> Mês
                    <input id="mesdia" type="radio" name="mesdia" value="dia"> Dia
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="pedidosNEntregues()"/>
                </div>
                <div id="pgerado" class="hidediv">
                    <b>Data Inicial: <input type="text" id="datainicial_pgerado" name="datainicial_pgerado" class="datetimepicker"/></b> 
                    <b>Data Final: <input type="text" id="datafinal_pgerado" name="datafinal_pgerado" class="datetimepicker"/></b>
                    <input id="mesdia" type="radio" name="mesdia" value="mes" checked> Mês
                    <input id="mesdia" type="radio" name="mesdia" value="dia"> Dia
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="prejuizoGerado()"/>
                </div>
                <div id="pnejustificativa" class="hidediv">
                    <b>Data Inicial: <input type="text" id="datainicial_just" name="datainicial_just" class="datetimepicker"/></b> 
                    <b>Data Final: <input type="text" id="datafinal_just" name="datafinal_just" class="datetimepicker"></b>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="pNEtreguePorJustificativa()"/>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div id="radiotela" class="hidediv">
                <input id="pie" type="radio" name="chart" value="column" CHECKED> Coluna
                <input id="combo" type="radio" name="chart" value="combo"> Combo
                <input id="bar" type="radio" name="chart" value="bar"> Bar
                <input id="column" type="radio" name="chart" value="pie"> Pizza
            </div>
            <div id="divcolumn" class="graf"></div>
            <div id="divcombo" class="graf"></div>
            <div id="divbar" class="graf"></div>
            <div id="divpie" class="graf"></div>
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
