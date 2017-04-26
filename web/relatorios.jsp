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
                    <li class="active"><a href="Controle?classe=ControleLogicoRedirecionamento&page=relatorios">RELATÓRIOS<span class="sr-only">(current)</span></a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
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
                    <li class="active"><a href="Controle?classe=ControleLogicoRedirecionamento&page=relatorios">RELATÓRIOS<span class="sr-only">(current)</span></a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
            <div class="table-responsive">
                <table class="table table-striped">
                    <tr>
                    <div>
                        <select id="tiposrelatorios">
                            <option value=" "></option>
                            <option value="Pedidos Entregues" id="pedidos_entregues" name="pedidos_entregues">Pedidos Entregues</option>
                            <option value="Pedidos Nao Entregues" id="pedidos_nao_entregues" name="pedidos_nao_entregues">Pedidos Nao Entregues</option>
                            <option value="Pedidos Por Entregador" id="pedidos_por_entregador" name="pedidos_por_entregador">Pedidos por Entregador</option>
                        </select>
                        <br>
                        <br>
                        <div id="data" style="display: none;">
                            <form name="datas" action="Controle?classe=ControleLogicoRelatorio&acao=gera_relatorio" method="POST">
                                <p >Data Inicial: <input type="text" id="datainicial" name="datainicial"/></p>
                                <p >Data Final: <input type="text" id="datafinal" name="datafinal"/></p>
                                <!--&nbsp; <a href="#"><input class="btn btn-primary btn-xs" type="submit" value="Buscar" name="buscar"/>removendo botão da tela-->
                            </form>
                            <form action="" >
                                <input id="radio_dia_mes" type="radio" name="radio_dia_mes" value="dia"> Dia
                                <input id="radio_dia_mes" type="radio" name="radio_dia_mes" value="mes"> Mes
                            </form>
                        </div>

                        <div id="entregadores" style="display: none;">
                            <form name="datas" action="Controle?classe=ControleLogicoRelatorio&acao=relatorio_entregador" method="POST">
                                <select name="entregador">
                                    <option value="null"></option>
                                    <c:forEach items="${ListaEntregadores}" var="Entregador">
                                        <option value="${Entregador.id}">${Entregador.nome}</option>
                                    </c:forEach>
                                </select>
                                <!--&nbsp; <a href="#"><input id="Buscar" class="btn btn-primary btn-xs" type="submit" value="Buscar" name="buscar"/>removendo botão da tela-->
                            </form>
                        </div>
                    </div>
                    <input id="Buscar1" class="btn btn-primary btn-xs" type="submit" value="Buscar1" name="buscar1" onclick="drawChart()"/>
                    <input id="Buscar2" class="btn btn-primary btn-xs" type="submit" value="Buscar2" name="buscar2" onclick="imprime()"/>
                    </tr>
                    <tr>
                    <div id="container" style="width: 1000px; height: 600px; margin: 0 auto"></div>
                    </tr>
                </table>
            <h1 class="page-header"><center><b> SGER - Sistema de Gerenciamento de Entregas </b></br>para Restaurantes</center></h1>
            <div class="tiporelt">
                <b>Tipo de Relatório:</b>
                <select id="tiposrelatorios">
                    <option value="null"></option>
                    <option value="Pedidos Entregues">Pedidos Entregues</option>
                    <option value="Pedidos Por Entregador">Pedidos Por Entregador</option>
                    <option value="Pedidos Nao Entregues">Pedidos Não Entregues</option> 
                    <option value="Prejuizo Gerado">Prejuízo Gerado</option>
                    <option value="Pedidos Nao Entregues Por Justificativa">Pedidos Não Entregues Por Justificativa</option>
                </select>
            </div>
            <div class="tiporelt">
                <div id="pentregues" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="pedidosEntregues()"/>
                </div>
                <div id="pentregador" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="pedidosPorEntreguador()"/>
                </div>
                <div id="pnentregues" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="pedidosNEntregues()"/>
                </div>
                <div id="pgerado" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="prejuizoGerado()"/>
                </div>
                <div id="pnejustificativa" class="hidediv">
                    <b>Data Inicial:</b> <input type="text" id="datainicial" name="datainicial"/>
                    <b>Data Final:</b> <input type="text" id="datafinal" name="datafinal"/>
                    <input id="Buscar" class="btn btn-primary" type="submit" value="Buscar" name="Gerar Relatório" onclick="pNEtreguePorJustificativa()"/>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div id="divcolumn" class="graf"></div>
            <div id="divcombo" class="graf"></div>
            <div id="divbar" class="graf"></div>
            <div id="divpie" class="graf"></div>
            <div id="radiotela" class="hidediv">
                <input selected id="pie" type="radio" name="chart" value="column"> Column
                <input id="combo" type="radio" name="chart" value="combo"> Combo
                <input id="bar" type="radio" name="chart" value="bar"> Bar
                <input id="column" type="radio" name="chart" value="pie"> Pie
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
