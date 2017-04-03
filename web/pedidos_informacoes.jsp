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
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoProduto&acao=listar_produto">PRODUTOS</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=relatorios">RELATÓRIOS</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoFuncionario&acao=listar_funcionario">FUNCIONÁRIOS</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"> <center> <b> SGER - Sistema de Gerenciamento de Entregas </b> </br>  para Restaurantes </center> </h1>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th colspan="2"><h2><a href="Controle?classe=ControleLogicoRedirecionamento&page=pedidos_status">STATUS DOS PEDIDOS</a></h2></th>
                            <th colspan="2"><h2><b><a href="Controle?classe=ControleLogicoRedirecionamento&page=pedidos_informacoes">INFORMAÇÕES DOS PEDIDOS</a></b></h2></th>
                        </tr>
                        <tr>
                            <th>Tipo</th>
                            <th>ID</th>
                            <th>Descrição</th>
                            <th>Quantidade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Marmitex(P)</td>
                            <td>1</td>
                            <td>Lasanha</td>
                            <td>3</td>
                        </tr>
                        <tr>
                            <td>Marmitex(M)</td>
                            <td>2</td>
                            <td>Feijoada</td>
                            <td>2</td>
                        </tr>
                        <tr>
                            <td>Marmitex(G)</br>Marmitex(M)</td>
                            <td>3</td>
                            <td>Lasanha</br>Feijoada</td>
                            <td>1</br>2</td>
                        </tr>
                        <tr>
                            <td>Marmitex(P)</br>Marmitex(G)</td>
                            <td>4</td>
                            <td>Dobradinha</br>Macarronada</td>
                            <td>2</br>3</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>