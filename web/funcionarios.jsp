<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Funcionario" scope="request" class="model.Funcionario"/>
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
                    <li><a href="Controle?classe=ControleLogicoProduto&acao=listar_produto">PRODUTOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoRelatorio&acao=consulta_entregador">RELATÓRIOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS<span class="sr-only">(current)</span></a></li>
                </ul>
            </c:if>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="table-responsive">
                <div class="sectionrightf">
                    <form name="fomularioPesquisa" action="Controle?classe=ControleLogicoFuncionario&acao=buscar_funcionario" method="POST">
                        <b>Pesquisar: &nbsp;</b>
                        <input type="search" name="pesquisa" placeholder="Nome, CPF, Telefone / Usuario" size="28" required autofocus title="Informe o Nome, CPF, Telefone ou Usuario do Funcionário">
                        <input type="submit" class="btn btn-primary btn-sm" value="Buscar" name="buscar"/>
                    </form>  
                </div>
                <div class="sectionleft">
                    <b id="nametable">Funcionários</b>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Celular</th>
                            <th>Ativo</th>
                            <th>Função</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ListaFuncionarios}" var="Funcionario">
                            <tr>
                                <td>${Funcionario.nome}</td>
                                <td>${Funcionario.celular}</td>
                                <td>${Funcionario.ativo}</td>
                                <td>${Funcionario.funcao}</td>
                                <td><a href="Controle?classe=ControleLogicoFuncionario&acao=carregar_alterar_funcionario&id=${Funcionario.id}"><input type="image" src="img/pencil.png" name="alterar"></a></td>
                            </tr>  
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="Controle?classe=ControleLogicoRedirecionamento&page=funcionarios_cadastro"> <input type="button" class="btn btn-primary" value="Cadastrar"></a> 
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>