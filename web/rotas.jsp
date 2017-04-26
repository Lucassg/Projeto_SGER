<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
   <!-- Styles for Maps  -->
   <link href="CSSProjeto/mapa.css" rel="stylesheet" async="true">

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
                    <li class="active"><a href="Controle?classe=ControleLogicoRedirecionamento&page=rotas">ROTAS<span class="sr-only">(current)</span></a></li>
                </ul>
                <c:if test="${funcionario.funcao == 'gerente'}">
                    <ul class="nav nav-sidebar">
                        <li><a href="Controle?classe=ControleLogicoProduto&acao=listar_produto">PRODUTOS</a></li>
                    </ul>
                    <ul class="nav nav-sidebar">
                        <li><a href="Controle?classe=ControleLogicoRelatorio&acao=consulta_entregador">RELATÓRIOS</a></li>
                    </ul>
                    <ul class="nav nav-sidebar">
                        <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                    </ul>
                </c:if>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
                <div>
                    <a href="Controle?classe=ControleLogicoRedirecionamento&page=rotas_googlemaps"><input class="btn btn-primary" type="button" value="Google Maps"></a>
                    <a href="Controle?classe=ControleLogicoRota&acao=listar_rotas_geradas"><input class="btn btn-primary" type="button" value="Rotas Geradas"></a>     
                    <a href="Controle?classe=ControleLogicoRota&acao=listar_rotas_andamento"><input class="btn btn-primary" type="button" value="Rotas em Andamento"></a>     
                </div>
            </div>
        </div>
    </div>

<jsp:include page="footer.jsp"/>