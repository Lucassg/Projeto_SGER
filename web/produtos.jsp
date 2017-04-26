<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="Produto" scope="request" class="model.Produto"/>
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
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=rotas">ROTAS</a></li>
            </ul>
            <c:if test="${funcionario.funcao == 'gerente'}">
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="Controle?classe=ControleLogicoProduto&acao=listar_produto">PRODUTOS<span class="sr-only">(current)</span></a></li>
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
                    <form name="fomularioPesquisa" action="Controle?classe=ControleLogicoProduto&acao=buscar_produto" method="POST">
                        <b>Pesquisar: &nbsp;</b>
                        <input type="search" name="pesquisa"  placeholder="Nome ou descrição Produto" size="25" autofocus required title="Informe Nome ou Descrição do Produto">
                        <input type="submit" class="btn btn-primary btn-sm" value="Buscar" name="buscar"/>
                    </form>
                </div>
                <div class="sectionleft">
                    <b id="nametable">Produtos</b>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Descrição</th>
                            <th>Valor(R$)</th>
                            <th>Ativo</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ListaProdutos}" var="Produto">
                            <tr>
                                <td>${Produto.nome}</td>
                                <td>${Produto.descricao}</td>
                                <td>${Produto.valor}</td>
                                <td>${Produto.ativo}</td>
                                <td><a href="Controle?classe=ControleLogicoProduto&acao=carregar_alterar_produto&id=${Produto.codigo}"><input type="image" src="img/pencil.png" name="alterar"></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="Controle?classe=ControleLogicoRedirecionamento&page=produtos_cadastro"> <input type="button" class="btn btn-primary" value="Cadastrar"></a> 
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>