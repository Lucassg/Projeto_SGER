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
                    <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=produtos">PRODUTOS</a></li>
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
                    <form name="fomularioCadastro" action="Controle?classe=ControleLogicoCliente&acao=cadastrar_cliente" method="POST">
                        <!-- DADOS PESSOAIS-->
                        <fieldset>
                            <legend> Dados Pessoais: </legend>
                            <table cellspacing="10">
                                <tr>
                                    <td>
                                        <dl><label for="nome">Nome: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input  type="text" name="nome" size="50" autofocus required x-moz-errormessage="Informe o nome do Cliente"></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="cpf">CPF: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="cpf" size="11"></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="celular">Celular:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="tel" name="celularddd" size="2" maxlength="2" required x-moz-errormessage="Informe o DDD"> - <input type="tel" name="celular" size="9" maxlength="9" required x-moz-errormessage="Informe o Celular"></dl> 
                                    </td>
                                    <td>
                                        <dl><label for="telefone">Telefone:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="tel" name="telefoneddd" size="2" maxlength="2" required x-moz-errormessage="Informe o DDD"> - <input type="tel" name="telefone" size="9" maxlength="9" required x-moz-errormessage="Informe o Telefone"></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="email">E-mail: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="email" name="email" size="40" x-moz-errormessage="Informe o e-mail válido"></dl>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <br/>
                        <!-- DADOS DO ENDEREÇO -->
                        <fieldset>
                            <legend> Endereço: </legend>
                            <table cellspacing="10">
                                <ul/>
                                <tr>
                                    <td>
                                        <dl><label for="rua">End: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="rua" size="70" required></dl>
                                    </td>
                                    <td>
                                        <dl><label for="numero">&nbsp;&nbsp; Nº:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="numero" size="4" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="bairro">Bairro: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="bairro" size="30" required></dl>
                                    </td>
                                    <td>
                                        <dl><label for="pontoref">&nbsp;&nbsp Ponto Ref: &nbsp;</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="pontoref" size="30"></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="cidade">Cidade:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="cidade" size="30" value="Campinas" required></dl>
                                    </td>
                                    <td>
                                        <dl><label for="uf">&nbsp;&nbsp; UF:</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><!--<input type="text" name="uf" size="2">-->
                                            <select name="uf" size="1" required>
                                                <option>AC</option>
                                                <option>AL</option>
                                                <option>AP</option>
                                                <option>AM</option>
                                                <option>BA</option>
                                                <option>CE</option>
                                                <option>DF</option>
                                                <option>ES</option>
                                                <option>GO</option>
                                                <option>MA</option>
                                                <option>MT</option>
                                                <option>MS</option>
                                                <option>MG</option>
                                                <option>PA</option>
                                                <option>PB</option>
                                                <option>PR</option>
                                                <option>PE</option>
                                                <option>PI</option>
                                                <option>RJ</option>
                                                <option>RN</option>
                                                <option>RS</option>
                                                <option>RO</option>
                                                <option>RR</option>
                                                <option>SC</option>
                                                <option selected>SP</option>
                                                <option>SE</option>
                                                <option>TO</option>
                                            </select>
                                        </dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="cep">CEP: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="cep" size="5" maxlength="5" required> - <input type="text" name="cep2" size="3" maxlength="3" required></dl>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <br/>
                        <a href="#"><input type="submit" class="btn btn-success" value="Finalizar Cadastro" name="cadastrar" /></a>
                        <a href="#"><input type="reset" class="btn btn-default" value="Limpar" name="limpar" /></a>
                        <a href="Controle?classe=ControleLogicoCliente&acao=listar_cliente"><input type="button" class="btn btn-danger" value="Cancelar" name="cancelar" /></a>
                    </form>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>