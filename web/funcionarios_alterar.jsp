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
                    <jsp:useBean id="funcionario" scope="request" class="model.Funcionario"/>
            <div class="table-responsive">
                <table class="table table-striped">
                    <form name="formulariocadastroFunc" action="Controle?classe=ControleLogicoFuncionario&acao=alterar_funcionario&id=${Funcionario.id}" method="POST">
                        <!-- DADOS DOS FUNCIONÁRIOS -->
                        <fieldset>
                            <legend> Dados do Funcionário: </legend>
                            <table cellspacing="10">
                                <tr>
                                    <td>
                                        <dl><label for="nome">Nome: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="nome" size="50" value="${Funcionario.nome}" required autofocus></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="cpf">CPF: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="cpf" size="11" value="${Funcionario.cpf}" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="celular">Celular:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="celular" size="11"  value="${Funcionario.celular}" required></dl> 
                                    </td>
                                    <td>
                                        <dl><label for="telefone">Telefone:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="telefone" size="11"  value="${Funcionario.telefone}" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="funcao">Função: </label></dl>
                                    </td>
                                    <c:if test="${Funcionario.funcao == 'atendente'}">
                                        <td align="left">
                                            <dl><select name="funcao">
                                                    <option selected value="atendente">Atendente</option>
                                                    <option value="entregador">Entregador</option>
                                                    <option value="gerente">Gerente</option> 
                                                </select></dl>
                                        </td>
                                    </c:if>
                                    <c:if test="${Funcionario.funcao == 'gerente'}">
                                        <td align="left">
                                            <dl><select name="funcao">
                                                    <option value="atendente">Atendente</option> 
                                                    <option value="entregador">Entregador</option>
                                                    <option selected value="gerente">Gerente</option> 
                                                </select></dl>
                                        </td>
                                    </c:if>
                                    <c:if test="${Funcionario.funcao == 'entregador'}">
                                        <td align="left">
                                            <dl><select name="funcao">
                                                    <option value="atendente">Atendente</option> 
                                                    <option selected value="entregador">Entregador</option>
                                                    <option value="gerente">Gerente</option> 
                                                </select></dl>
                                        </td>
                                    </c:if>    
                                    <td>
                                        <dl><label for="ativo">Ativo:</label></dl>
                                    </td>
                                    <c:if test="${Funcionario.ativo == 'sim'}">
                                        <td align="left">
                                            <dl><select name="ativo">
                                                    <option selected value="sim">Sim</option> 
                                                    <option value="não">Não</option> 
                                                </select></dl>
                                        </td>
                                    </c:if>
                                    <c:if test="${Funcionario.ativo == 'não'}">
                                        <td align="left">
                                            <dl><select name="ativo">
                                                    <option value="sim">Sim</option> 
                                                    <option selected value="não">Não</option> 
                                                </select></dl>
                                        </td> 
                                    </c:if>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="usuario">Usuário: &nbsp;</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="usuario" size="10" value="${Funcionario.usuario}" required></dl>
                                    </td>
                                    <td>
                                        <dl><label for="senha">Senha: &nbsp;</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="password" name="senha" size="10" value="${Funcionario.senha}" required></dl>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <br/>
                        <a href="#"><input type="submit" class="btn btn-success" value="Salvar" name="cadastrarfunc" /></a>
                        <a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario"><input type="button" class="btn btn-danger" value="Cancelar" name="cancelar" /></a>
                    </form>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>