<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                <li class="active"><a href="Controle?classe=ControleLogicoCliente&acao=listar_cliente">CLIENTES<span class="sr-only">(current)</span></a></li>
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
        <jsp:useBean id="Cliente" scope="request" class="model.Cliente"/>
        <h1 class="page-header"><center><b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">        
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="4"><h2>CLIENTES</h2><td align="right">
                                <form name="fomularioPesquisa" action="Controle?classe=ControleLogicoCliente&acao=buscar_cliente" method="POST" >
                                    <b>Pesquisar: &nbsp;&nbsp;</b>
                                    <input type="search" placeholder="Nome, CPF, Telefone" name="pesquisa" size="20" title="Informe Nome, CPF, Celular ou Telefone" autofocus required>
                                    <a href="#"><input type="submit" class="btn btn-primary btn-sm" value="Buscar" name="buscar"/>
                                    </a></td></form></th>
                        </tr>
                        <tr>
                            <th>Nome</th>
                            <th>Celular</th>
                            <th>Telefone</th>
                            <th>Endereço</th>
                            <th>Email</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ListaClientes}" var="Cliente">
                            <tr>
                                <td>${Cliente.nome}</td>
                                <td>${Cliente.celular}</td>
                                <td>${Cliente.telefone}</td>
                                <td>${Cliente.rua}, ${Cliente.numero}, 
                                    ${Cliente.bairro}, ${Cliente.cidade}-
                                    ${Cliente.uf}</td>
                                <td>${Cliente.email}</td>
                                <td><a href="Controle?classe=ControleLogicoCliente&acao=carregar_alterar_cliente&id=${Cliente.id}"><input type="image" src="img/pencil.png" name="alterar"></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="Controle?classe=ControleLogicoRedirecionamento&page=clientes_cadastro"><button class="btn btn-primary">Cadastrar</button>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>