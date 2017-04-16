<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=relatorios">RELATÓRIOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <jsp:useBean id="Produto" scope="request" class="model.Produto"/>
        <jsp:useBean id="Itens_Pedido" scope="request" class="model.Itens_Pedido"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
            <div class="table-responsive">
                <div class="sectionleft">
                    <b id="nametable">Itens do Pedido</b>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Item</th>
                            <th>Produto</th>
                            <th>Descrição</th>
                            <th>Quantidade</th>
                            <th>Valor</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:set var="cont" value="0"/>
                    <c:forEach items="${ListaItensPedido}" var="Itens_Pedido">
                        <tr>
                            <td>${cont = cont + 1}</td>
                            <td>${Itens_Pedido.produto_id.nome}</td>
                            <td>${Itens_Pedido.produto_id.descricao}</td>
                            <td>${Itens_Pedido.quantidade}</td>
                            <td>${Itens_Pedido.produto_id.valor}</td>
                            <td><a href="Controle?classe=ControleLogicoPedido&acao=excluir_item&index=${cont}"><input type="image" src="img/delete.png" name="deletar"></a></td>
                        </tr>
                    </c:forEach> 
                </table>
            </div>
            <div class="table-responsive">
                <table class="table table-striped">
                    <form name="FormularioInseriItem" action="Controle?classe=ControleLogicoPedido&acao=inserir_item" method="POST">
                        <fieldset>
                            <thead>
                                <tr>
                                    <th>Produto</th>
                                    <th>Quantidade</th>
                                    <th>Observação</th>
                                    <th></th>
                                </tr> 
                            </thead>
                            <tbody>
                                <tr>
                                    <td align="left">
                                        <dl><select name="produto">
                                                <option value="null"></option>
                                                <c:forEach items="${ListaProdutos}" var="Produto">
                                                    <option value="${Produto.codigo}">${Produto.nome}</option> 
                                                </c:forEach>
                                            </select></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="number" name="quantidade" size="2" required></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="observacao" size="80"></dl>
                                    </td>
                                    <td align="left">
                                        <a href="#"><input type="image" src="img/plus.png" name="incluir"></a>
                                    </td>
                                </tr>
                            </tbody>
                    </form>
                    </fieldset>
                </table>
            </div>
            <a href="Controle?classe=ControleLogicoPedido&acao=gravar_pedido"><input type="button" class="btn btn-success" value="Finalizar Pedido" name="cadastrar" /></a>
            <a href="Controle?classe=ControleLogicoPedido&acao=cancelar_cad_pedido"><input type="button" class="btn btn-danger" value="Cancelar" name="cancelar" /></a>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>