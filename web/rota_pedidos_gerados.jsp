<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>

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
                    <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=relatorios">RELATÓRIOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <jsp:useBean id="Pedido" scope="request" class="model.Pedido"/>
        <jsp:useBean id="Rota" scope="request" class="model.Rota"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
            <div class="table-responsive">
                <br>
                <b>Rota:</b> ${Rota.id} </br> 
                <b>Status:</b> ${Rota.status} </br> 
                <b>Data:</b> ${Rota.data_hora_rota} </br> 
                <b>Entregador:</b> ${Rota.funcionario.nome} </br></br>

                <table class="table table-bordered"> 
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>ID</th>
                            <th>Status</th>
                            <th>Cliente</th>
                            <th>Endereço</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0"/>
                        <c:forEach items="${PedidosRota}" var="Pedido">
                            <tr>
                                <td>${cont = cont + 1}</td>
                                <td>${Pedido.id}</td>
                                <td>${Pedido.status}</td>
                                <td>${Pedido.cliente.nome}</td>
                                <td>${Pedido.cliente.rua}, ${Pedido.cliente.numero}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <input class="btn btn-primary" type="button" value="Visualizar Rota no Google Maps" onClick="centralizarJanela('\mapa_multi_pontos', 'targetWindow', 1000, 800);">
                <a href="Controle?classe=ControleLogicoRota&acao=rota_andamento&rota=${Rota.id}"><input class="btn btn-primary" type="button" value="Rota em Andamento"></a>
            </div>
        </div> 
    </div>
</div>

<script src="JSProjeto/janela_popup.js" type="text/javascript"></script>

<jsp:include page="footer.jsp"/>