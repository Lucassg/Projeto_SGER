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
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=rotas">ROTAS</a></li>
            </ul>
            <c:if test="${funcionario.funcao == 'gerente'}">
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="Controle?classe=ControleLogicoProduto&acao=listar_produto">PRODUTOS<span class="sr-only">(current)</span></a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=relatorios">RELATÓRIOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
            <div class="table-responsive">
                <table class="table table-striped">
                    <form name="formulariocadastroProd" action="Controle?classe=ControleLogicoProduto&acao=cadastrar_produto" method="POST">
                        <!-- DADOS DOS Produto -->
                        <fieldset>
                            <legend> Dados do Produto: </legend>
                            <table cellspacing="10">
                                <tr>
                                    <td>
                                        <dl><label for="nomeprod">Nome:</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="nomeprod" size="50" required autofocus></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="descricao">Descrição:&nbsp;&nbsp;</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="descricao" size="50" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="valor">Valor (R$):</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="valor" size="5" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="peso">Peso:</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="peso" size="5" required></dl>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <br/>
                        <a href="#"><input type="submit" class="btn btn-success" value="Finalizar Cadastro" name="cadastrarproduto" /></a>
                        <a href="#"><input type="reset" class="btn btn-default" value="Limpar" name="limpar" /></a>
                        <a href="Controle?classe=ControleLogicoProduto&acao=listar_produto"><input type="button" class="btn btn-danger" value="Cancelar" name="cancelar" /></a>
                    </form>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>