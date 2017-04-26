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
                    <li class="active"><a href="Controle?classe=ControleLogicoRedirecionamento&page=produtos">PRODUTOS<span class="sr-only">(current)</span></a></li>
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
                    <jsp:useBean id="produto" scope="request" class="model.Produto"/>
            <div class="table-responsive">
                <table class="table table-striped">
                    <form name="formulariocadastroProd" action="Controle?classe=ControleLogicoProduto&acao=alterar_produto&id=${produto.codigo}" method="POST">
                        <!-- DADOS DOS Produto -->
                        <fieldset>
                            <legend> Dados do Produto: </legend>
                            <table cellspacing="10">
                                <tr>
                                    <td>
                                        <dl><label for="nomeprod">Nome:</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="nomeprod" size="50" value="${produto.nome}" required autofocus></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="descricao">Descrição:&nbsp;&nbsp;</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="descricao" size="50" value="${produto.descricao}" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="valor">Valor (R$):</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="valor" size="5" value="${produto.valor}" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="peso">Peso:</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="peso" size="5" value="${produto.peso}" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="ativo">Ativo:</label></dl>
                                    </td>
                                    <c:if test="${produto.ativo == 'sim'}">
                                        <td align="left">
                                            <dl><select name="ativo">
                                                    <option selected value="sim">Sim</option> 
                                                    <option value="não">Não</option> 
                                                </select></dl>
                                        </td>
                                    </c:if>
                                    <c:if test="${produto.ativo == 'não'}">
                                        <td align="left">
                                            <dl><select name="ativo">
                                                    <option value="sim">Sim</option> 
                                                    <option selected value="não">Não</option> 
                                                </select></dl>
                                        </td> 
                                    </c:if>
                                </tr>
                            </table>
                        </fieldset>
                        <br/>
                        <a href="#"><input type="submit" class="btn btn-success" value="Salvar" name="alterarproduto" /></a>
                        <a href="Controle?classe=ControleLogicoProduto&acao=listar_produto"><input type="button" class="btn btn-danger" value="Cancelar" name="cancelar" /></a>
                    </form>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>