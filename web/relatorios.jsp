<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

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
                            <option value="Pedidos Entregues">Pedidos Entregues</option>
                            <option value="Pedidos Nao Entregues">Pedidos Nao Entregues</option>
                            <option value="Pedidos Por Entregador">Pedidos por Entregador</option>
                        </select>
                        <br>
                        <br>
                        <div id="data" style="display: none;">
                            <p >Data Inicial: <input type="text" id="datainicial"/></p>
                            <p >Data Final: <input type="text" id="datafinal"/></p>
                        </div>

                        <div id="entregadores" style="display: none;">
                            <select>
                                <c:forEach items="${ListaEntregadores}" var="Entregador">
                                    <option>${Entregador.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    </tr>
                    <tr>
                    <div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="JSProjeto/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="JSProjeto/jquery-ui.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="JSProjeto/relatorios.js"></script>
</body>

<jsp:include page="footer.jsp"/>