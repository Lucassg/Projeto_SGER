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
        <jsp:useBean id="Funcionario" scope="request" class="model.Funcionario"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
            <form action="Controle?classe=ControleLogicoRota&acao=gravar_rota" method="POST">
            <div class="table-responsive">
                <center><b><h3>Informe o Entregador para as Rotas Geradas</h2></b></center>
                <br>
                <table class="table table-bordered"> 
                    <thead>
                        <tr>
                            <th>Rota</th>
                            <th>Entregador</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cont" value="0"/>
                        <c:forEach var="i" begin="1" end="${qtderotas}">
                            <tr>
                                <td>${cont = cont + 1}</td>
                                <td align="left">
                                    <dl><select name="${cont}">
                                            <option value="null"></option>
                                        <c:forEach items="${ListaEntregares}" var="Funcionario">
                                            <option value="${Funcionario.cpf}">${Funcionario.nome}</option> 
                                        </c:forEach>
                                    </select></dl>
                                </td>
                            </tr>
                        </c:forEach> 
                    </tbody>
                </table>    
            </div>
            <input class="btn btn-primary" type="submit" value="Salvar"></a>
            </form> 
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>