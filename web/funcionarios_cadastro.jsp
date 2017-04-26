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

            <div class="table-responsive">
                <table class="table table-striped">
                    <form name="formulariocadastroFunc" action="Controle?classe=ControleLogicoFuncionario&acao=cadastrar_funcionario" method="POST">
                        <!-- DADOS DOS FUNCIONÁRIOS -->
                        <fieldset>
                            <legend> Dados do Funcionário: </legend>
                            <table cellspacing="10">
                                <tr>
                                    <td>
                                        <dl><label for="nome">Nome: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="nome" size="50" required autofocus></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="cpf">CPF: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="cpf" size="11" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="celular">Celular:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="celularddd" size="2" maxlength="2" required> - <input type="text" name="celular" size="9" maxlength="9" required></dl> 
                                    </td>
                                    <td>
                                        <dl><label for="telefone">Telefone:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="telefoneddd" size="2" maxlength="2" required> - <input type="text" name="telefone" size="9" maxlength="9" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="funcao">Função: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><select name="funcao" required> 
                                                <option selected value="atendente">Atendente</option> 
                                                <option value="entregador">Entregador</option> 
                                                <option value="gerente">Gerente</option>
                                            </select></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="usuario">Usuario: &nbsp;</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="usuario" size="10" required></dl>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <br/>
                        <a href="#"><input type="submit" class="btn btn-success" value="Finalizar Cadastro" name="cadastrarfunc" /></a>
                        <a href="#"><input type="reset" class="btn btn-default" value="Limpar" name="limpar" /></a>
                        <a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario"><input type="button" class="btn btn-danger" value="Cancelar" name="cancelar" /></a>
                    </form>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>