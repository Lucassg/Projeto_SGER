<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
   <!-- Styles for Maps  -->
   <link href="CSSProjeto/mapa.css" rel="stylesheet" async="true">

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
                    <li class="active"><a href="Controle?classe=ControleLogicoRedirecionamento&page=rotas">ROTAS<span class="sr-only">(current)</span></a></li>
                </ul>
                <c:if test="${funcionario.funcao == 'gerente'}">
                    <ul class="nav nav-sidebar">
                        <li><a href="Controle?classe=ControleLogicoProduto&acao=listar_produto">PRODUTOS</a></li>
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
            </div>
        </div>
    </div>
    </br>
    </br>
    </br>
    <table cellspacing="10">
        <tr>
            <td>
            </td>
        </tr>
        <tr>
            <td>
                <div id="map_container">
                    </br>
                    </br>
                    <input id="pac-input" class="controls" type="text"
                           placeholder="Informe a localização">
                    <div id="type-selector" class="controls">
                        <input type="radio" name="type" id="changetype-all" checked="checked">
                        <label for="changetype-all">Todos</label>

                        <input type="radio" name="type" id="changetype-establishment">
                        <label for="changetype-establishment">Estabelecimentos</label>

                        <input type="radio" name="type" id="changetype-address">
                        <label for="changetype-address">Endereços</label>

                        <input type="radio" name="type" id="changetype-geocode">
                        <label for="changetype-geocode">Coordenadas Geograficas</label>
                    </div>
                    <div id="map">
                    </div>
                </div>
            </td>
        </tr>
    </table>
    <script type="text/javascript" src="JSProjeto/janela_popup.js" async="true"></script>
    <script type="text/javascript" src="JSProjeto/mapa.js" async="true"></script>
    <!-- Chave para executar via servidor  -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCj0AreC_ic-Nrs3BBrPuNmBaaTeaGr-eQ&signed_in=true&libraries=places&callback=initMap" async defer></script>
<jsp:include page="footer.jsp"/>