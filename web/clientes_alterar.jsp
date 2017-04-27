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
                    <li><a href="Controle?classe=ControleLogicoRelatorio&acao=consulta_entregador">RELATÓRIOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
                    <jsp:useBean id="cliente" scope="request" class="model.Cliente"/>
            <div class="table-responsive">
                <table class="table table-striped">
                    <form name="fomularioAltera" action="Controle?classe=ControleLogicoCliente&acao=alterar_cliente&id=${cliente.id}" method="POST">
                        <!-- DADOS PESSOAIS-->
                        <fieldset>
                            <legend> Dados Pessoais: </legend>
                            <table cellspacing="10">
                                <tr>
                                    <td>
                                        <dl><label for="nome">Nome: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="nome" size="50" value="${cliente.nome}" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="cpf">CPF: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="cpf" size="11" value="${cliente.cpf}"></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="celular">Celular:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="tel" name="celular" size="11" value="${cliente.celular}" required></dl> 
                                    </td>
                                    <td>
                                        <dl><label for="telefone">Telefone:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="tel" name="telefone" size="11" value="${cliente.telefone}" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="email">E-mail: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="email" name="email" size="40" value="${cliente.email}"></dl>
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
                                        <dl><label for="cep">CEP: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="cep" size="8" value="${cliente.cep}" required></dl>
                                    </td>
                                </tr>    
                                <tr>
                                    <td>
                                        <dl><label for="rua">End: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="rua" size="70" value="${cliente.rua}" required></dl>
                                    </td>
                                    <td>
                                        <dl><label for="numero">&nbsp;&nbsp; Nº:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="numero" size="4" value="${cliente.numero}" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="bairro">Bairro: </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="bairro" size="30" value="${cliente.bairro}" required></dl>
                                    </td>
                                    <td>
                                        <dl><label for="pontoref">&nbsp;&nbsp Ponto Ref: &nbsp;</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="pontoref" size="30" value="${cliente.ponto_ref}"></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="cidade">Cidade:&nbsp;&nbsp; </label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="cidade" size="30" value="${cliente.cidade}" required></dl>
                                    </td>
                                    <td>
                                        <dl><label for="uf">&nbsp;&nbsp; UF:</label></dl>
                                    </td>
                                    <td align="left">
                                        <dl><input type="text" name="uf" size="2" value="${cliente.uf}" required></dl>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <dl><label for="ativo">Ativo:</label></dl>
                                    </td>
                                    <c:if test="${cliente.ativo == 'sim'}">
                                        <td align="left">
                                            <dl><select name="ativo">
                                                    <option selected value="sim">Sim</option> 
                                                    <option value="não">Não</option> 
                                                </select></dl>
                                        </td>
                                    </c:if>
                                    <c:if test="${cliente.ativo == 'não'}">
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
                        <a href="#"><input type="submit" class="btn btn-success" value="Salvar" name="alterarCliente"/></a>
                        <a href="Controle?classe=ControleLogicoCliente&acao=listar_cliente"><input type="button" class="btn btn-danger" value="Cancelar" name="cancelar"/></a>
                    </form>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>