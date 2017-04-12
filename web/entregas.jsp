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
                <li class="active"><a href="Controle?classe=ControleLogicoRota&acao=listar_pedido_entregar">ENTREGAS<span class="sr-only">(current)</span></a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=rotas">ROTAS</a></li>
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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
            <div class="table-responsive">
                <center><b><h3>Pedidos Aguardando Entrega</h2></b></center>
                <br>
                <table class="table table-bordered"> 
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Pedido</th>
                            <th>Status</th>
                            <th>Data</th>
                            <th>Cliente</th>
                            <th>Endereço</th>
                            <th><input type="image" src="img/search.png"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0"/>
                        <c:forEach items="${ListaPedidos}" var="Pedido">
                            <tr>
                                <td>${cont = cont + 1}</td>
                                <td>${Pedido.id}</td>
                                <td>${Pedido.status}</td>
                                <td>${Pedido.data_hora_pedido}</td>
                                <td>${Pedido.cliente.nome}</td>
                                <td>${Pedido.cliente.rua}, ${Pedido.cliente.numero}</td>
                                <td><a href="Controle?classe=ControleLogicoPedido&acao=visualizar_pedido&pedido=${Pedido.id}"><input type="image" src="img/search.png" name="visualizar"></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="Controle?classe=ControleLogicoRota&acao=gerar_rotas"><input class="btn btn-primary" type="button" value="Gerar Rotas"></a>       
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>