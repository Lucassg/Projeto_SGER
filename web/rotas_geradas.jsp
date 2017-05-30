<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <li><a href="Controle?classe=ControleLogicoRelatorio&acao=consulta_entregador">RELATÓRIOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <jsp:useBean id="Rota" scope="request" class="model.Rota"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
            <div class="table-responsive">
                <center><b><h3>Rotas Geradas</h2></b></center>
                <br>
                <table class="table table-bordered"> 
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>ID</th>
                            <th>Status</th>
                            <th>Data</th>
                            <th>Entregador</th>
                            <th><input type="image" src="img/search.png" name="visualizar"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0"/>
                        <c:forEach items="${ListaRotas}" var="Rota">
                            <tr>
                                <td>${cont = cont + 1}</td>
                                <td>${Rota.id}</td>
                                <td>${Rota.status}</td>
                                <td><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${Rota.data_hora_rota}"/></td>
                                <td>${Rota.funcionario.nome}</td>
                                <td><a href="Controle?classe=ControleLogicoRota&acao=exibe_pedidos_rota&rota=${Rota.id}"><input type="image" src="img/search.png" name="visualizar"></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>        
            </div>
        </div> 
    </div>
</div>
<jsp:include page="footer.jsp"/>