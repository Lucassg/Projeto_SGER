<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=index">INÍCIO</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controle?classe=ControleLogicoRedirecionamento&page=pedidos">PEDIDOS</a></li>
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
                <li class="active"><a href="Controle?classe=ControleLogicoRedirecionamento&page=relatorios">RELATÓRIOS<span class="sr-only">(current)</span></a></li>
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
                            <th><h2><a href="Controle?classe=ControleLogicoRedirecionamento&page=rel_ped_ent">PEDIDOS ENTREGUES</a></h2></th>
                            <th><h2><a href="Controle?classe=ControleLogicoRedirecionamento&page=rel_ped_nao_ent">PEDIDOS NÃO ENTREGUES</a></h2></th>
                            <th><h2><a href="Controle?classe=ControleLogicoRedirecionamento&page=rel_ent_entregador">ENTREGAS POR ENTREGADOR</a></h2></th>
                            <th><h2><a href="Controle?classe=ControleLogicoRedirecionamento&page=rel_clientes">CLIENTES</a></h2></th>
                        </tr>
                        <tr>
                            <th>Nome</th>
                            <th>Telefone</th>
                            <th>CPF</th>
                            <th>Endereço</th>
                        </tr>				
                    </thead>
                    <tbody>
                        <tr>
                            <td>Elio</td>
                            <td>3838-6427</td>
                            <td>987.654.321-10</td>
                            <td>Av.Francisco Glicerio, 427, Centro, Campinas-SP</td>
                        </tr>
                        <tr>
                            <td>Gilberto</td>
                            <td>3854-2764</td>
                            <td>876.543.219-20</td>
                            <td>Av.Campos Sales, 563, Centro, Campinas-SP</td>
                        </tr>
                        <tr>
                            <td>Thiago</td>
                            <td>3864-7283</td>
                            <td>765.432.198-30</td>
                            <td>Av.Andrade Neves, 289, Centro, Campinas-SP</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>