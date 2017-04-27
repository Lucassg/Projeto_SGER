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
                <li><a href="Controle?classe=ControleLogicoRelatorio&acao=consulta_entregador">RELATÓRIOS</a></li>
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
                            <th><h2><b><a href="Controle?classe=ControleLogicoRedirecionamento&page=pedidos_status">STATUS DOS PEDIDOS</a></b></h2></th>
                            <th><h2><a href="Controle?classe=ControleLogicoRedirecionamento&page=pedidos_informacoes">INFORMAÇÕES DOS PEDIDOS</a></h2></th>
                        </tr>
                        <tr>
                            <th>Pedidos em Espera</th>
                            <th>Pedidos em Andamento</th>
                            <th>Pedidos Entregues</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>5</td>
                            <td>3</td>
                            <td>1</td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>4</td>
                            <td>2</td>
                        </tr>
                        <tr>
                            <td>11</td>
                            <td>9</td>
                            <td>7</td>
                        </tr>
                        <tr>
                            <td>12</td>
                            <td>10</td>
                            <td>8</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>