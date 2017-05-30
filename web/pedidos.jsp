<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Pedido" scope="request" class="model.Pedido"/>
<jsp:include page="header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=index">INÍCIO</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li class="active"><a href="Controle?classe=ControleLogicoPedido&acao=listar_pedido">PEDIDOS<span class="sr-only">(current)</span></a></li>
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
                    <li><a href="Controle?classe=ControleLogicoRelatorio&acao=consulta_entregador">RELATÓRIOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="table-responsive">
                <div class="sectionright">
                    <form name="fomularioCadastro" action="Controle?classe=ControleLogicoPedido&acao=listar_pedido_status" method="POST"> 
                        <select name="statusEscolhido">
                            <option value="todos" selected>Todos</option> 
                            <option value="Aguardando Entrega">Aguardando Entrega</option> 
                            <option value="Entrega em Andamento">Entrega em Andamento</option>
                            <option value="Cancelado">Cancelado</option> 
                            <option value="Entregue">Entregue</option>
                            <option value="Não Entregue">Não Entregue</option>
                        </select>&nbsp;
                        <input class="btn btn-primary btn-xs" type="submit" value="Buscar" name="buscar"/>
                    </form>
                </div>
                <div class="sectionleft">
                    <b id="nametable">Pedidos</b>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Status</th>
                            <th>Cliente</th>
                            <th>Valor</th>
                            <th>Data</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ListaPedidos}" var="Pedido">
                            <tr>
                                <td>${Pedido.id}</td>
                                <td>${Pedido.status}</td>
                                <td>${Pedido.cliente.nome}</td>
                                <td><fmt:setLocale value="pt_BR"/><fmt:formatNumber value="${Pedido.valor}" type="currency"/></td>
                                <td><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${Pedido.data_hora_pedido}" /></td>
                                <td><a href="Controle?classe=ControleLogicoPedido&acao=visualizar_pedido&pedido=${Pedido.id}"><input type="image" src="img/search.png" name="visualizar"></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="Controle?classe=ControleLogicoRedirecionamento&page=pedidos_cadastro"><input class="btn btn-primary" type="button"value="Inserir Pedido"></a>
            <a href="Controle?classe=ControleLogicoRedirecionamento&page=pedidos_pesquisar"><input class="btn btn-primary" type="button" value="Pesquisar Pedido"></a>
        </div>	  
    </div>
</div>
<jsp:include page="footer.jsp"/>