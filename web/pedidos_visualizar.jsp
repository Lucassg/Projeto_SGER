<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <li><a href="Controle?classe=ControleLogicoRelatorio&acao=consulta_entregador">RELATÓRIOS</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
                </ul>
            </c:if>
        </div>
        <jsp:useBean id="Pedido" scope="request" class="model.Pedido"/>
        <jsp:useBean id="Itens_Pedido" scope="request" class="model.Itens_Pedido"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>
            <div class="table-responsive">
                <table class="table table-striped">
                    <fieldset>
                        <h4><b>Pedido: ${Pedido.id}</b></h4> 
                        <h4><b>Status:</b> ${Pedido.status}</h4> 
                        <table cellspacing="10">
                            <tr>
                                <td>
                                    <dl><label for="nome">Nome: </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="nome" size="50" value="${Pedido.cliente.nome}" readonly></dl>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <dl><label for="cpf">CPF: </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="cpf" size="11" value="${Pedido.cliente.cpf}" readonly></dl>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <dl><label for="celular">Celular:&nbsp;&nbsp; </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="celular" size="11" value="${Pedido.cliente.celular}" readonly></dl> 
                                </td>
                                <td>
                                    <dl><label for="telefone">Telefone:&nbsp;&nbsp; </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="telefone" size="11" value="${Pedido.cliente.telefone}" readonly></dl>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <dl><label for="email">E-mail: </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="email" size="40" value="${Pedido.cliente.email}" readonly></dl>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                    <fieldset>
                        <table cellspacing="10">
                            <tr>
                                <td>
                                    <dl><label for="rua">Rua: </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="rua" size="70" value="${Pedido.cliente.rua}" readonly></dl>
                                </td>
                                <td>
                                    <dl><label for="numero">&nbsp;&nbsp; Nº:&nbsp;&nbsp; </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="numero" size="4" value="${Pedido.cliente.numero}" readonly></dl>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <dl><label for="bairro">Bairro: </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="bairro" size="30" value="${Pedido.cliente.bairro}" readonly></dl>
                                </td>
                                <td>
                                    <dl><label for="pontoref">&nbsp;&nbsp Ponto Ref: &nbsp;</label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="pontoref" size="30" value="${Pedido.cliente.ponto_ref}" readonly></dl>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <dl><label for="cidade">Cidade:&nbsp;&nbsp; </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="cidade" size="30" value="${Pedido.cliente.cidade}" readonly></dl>
                                </td>
                                <td>
                                    <dl><label for="uf">&nbsp;&nbsp; UF:</label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="uf" size="2" value="${Pedido.cliente.uf}" readonly></dl>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <dl><label for="cep">CEP: </label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="cep" size="8" value="${Pedido.cliente.cep}" readonly></dl>
                                </td>
                                <td>
                                    <dl><label for="ativo">Ativo:</label></dl>
                                </td>
                                <td align="left">
                                    <dl><input type="text" name="ativo" size="3" value="${Pedido.cliente.ativo}" readonly></dl>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </table>
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
                                <th>Valor Unit.</th>
                                <th>Valor Total</th>
                            </tr>
                        </thead>
                        <c:set var="cont" value="0"/>
                        <c:forEach items="${ListaItensPedido}" var="Itens_Pedido">
                            <tr>
                                <td>${cont = cont + 1}</td>
                                <td>${Itens_Pedido.produto_id.nome}</td>
                                <td>${Itens_Pedido.produto_id.descricao}</td>
                                <td>${Itens_Pedido.quantidade}</td>
                                <td><fmt:setLocale value="pt_BR"/><fmt:formatNumber value="${Itens_Pedido.produto_id.valor}" type = "currency"/></td>
                                <td><fmt:setLocale value="pt_BR"/><fmt:formatNumber value="${Itens_Pedido.produto_id.valor * Itens_Pedido.quantidade}" type = "currency"/></td>
                            </tr>
                        </c:forEach> 
                    </table>
                </div>
                <table cellspacing="10">
                    <tr>
                        <td>
                            <dl><label for="atendente">Atendente:&nbsp;&nbsp;</label></dl>
                        </td>
                        <td align="left">
                            <dl><input type="text" name="atendente" size="30" value="${Pedido.funcionario.nome}" readonly></dl>
                        </td>
                        <td>
                            <dl><label for="entregador">&nbsp;Entregador:&nbsp;&nbsp;</label></dl>
                        </td>
                        <td align="left">
                            <dl><input type="text" name="entregador" size="30" value="${Pedido.rota.funcionario.nome}" readonly></dl>
                        </td>
                        <td>
                            <dl><label for="rota">&nbsp;Rota:&nbsp;&nbsp;</label></dl>
                        </td>
                        <td align="left">
                            <dl><input type="text" name="rota" size="4" value="${Pedido.rota.id}" readonly></dl>
                        </td>
                    </tr>
                    <c:if test="${Pedido.status == 'Não Entregue'}">
                        <tr>
                            <td>
                                <dl><label for="justificativa">Justificativa: &nbsp;&nbsp;</label></dl>
                            </td>
                            <td align="left">
                                <dl><input type="text" name="justificativa" size="30" value="${Pedido.justificativa}" readonly></dl>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${Pedido.status == 'Cancelado'}">
                        <tr>
                            <td>
                                <dl><label for="justificativa">Motivo Cancelamento: &nbsp;&nbsp;</label></dl>
                            </td>
                            <td align="left">
                                <dl><input type="text" name="motivocancelamento" size="30" value="${Pedido.justificativa}" readonly></dl>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${Pedido.status != 'Não Entregue' and Pedido.status != 'Entregue' and Pedido.status != 'Cancelado'}">
                        <tr>
                        <form name="formcancela" action="Controle?classe=ControleLogicoPedido&acao=cancelar_pedido&pedido=${Pedido.id}" method="POST">
                            <td>
                                <dl><label for="motivocancel">Motivo Cancelamento:&nbsp;</label></dl>
                            </td>
                            <td align="left">
                                <dl><select name="motivo" >
                                        <option value="pedido aberto errado">Pedido Aberto Errado</option> 
                                        <option value="cancelado pelo cliente">Cancelado Pelo Cliente</option> 
                                        <option value="alteração do pedido">Alteração Do Pedido</option> 
                                    </select>
                                </dl>
                            </td>
                            <td>
                                <input class="btn btn-danger" type="submit" value="Cancelar Pedido">    
                            </td>
                        </form>
                        </tr>
                    </c:if>
                </table>
                <a href="Controle?classe=ControleLogicoPedido&acao=listar_pedido"> <input type="button" class="btn btn-primary" value="Voltar"></a>
                <!--<a href="#"><input type="submit" class="btn btn-danger" onclick="confirm('Deseja realmente Carcelar o Pedido?')" value="Cancelar Pedido"></a>-->
            </div>	  
        </div>
    </div>
</div>                        

<jsp:include page="footer.jsp"/>