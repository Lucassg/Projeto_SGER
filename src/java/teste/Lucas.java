package teste;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;
import dao.DaoItens_Pedido;
import dao.DaoPedido;
import dao.DaoSger;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import model.Pedido;
import model.RotaInicial;
import model.RotaTemp;
import model.Sger;

public class Lucas {

    /*
    Método utilizado para ondenar a lista ListaRotasInicial
     */
    public static class comparadorInicio implements Comparator<RotaInicial> {

        @Override
        public int compare(RotaInicial r1, RotaInicial r2) {
            if (r1.getDistancia() < r2.getDistancia()) {
                return -1;
            } else if (r1.getDistancia() > r2.getDistancia()) {
                return +1;
            } else {
                return 0;
            }
        }
    }

    public static class comparadorParesEndereco implements Comparator<RotaTemp> {

        @Override
        public int compare(RotaTemp r1, RotaTemp r2) {
            if (r1.getDistancia() < r2.getDistancia()) {
                return -1;
            } else if (r1.getDistancia() > r2.getDistancia()) {
                return +1;
            } else {
                return 0;
            }
        }

    }

    public static void main(String[] args) throws Exception {

        DaoPedido acessohibernatepedido;
        DaoItens_Pedido acessohibernateitenspedido;
        acessohibernatepedido = new DaoPedido();
        acessohibernateitenspedido = new DaoItens_Pedido();
        DaoSger acessohibernatesger = new DaoSger();
        Sger sger = new Sger();
        List<RotaTemp> ListaParesPedidos = new ArrayList<>();

        List<Pedido> ListaPedidos = (List<Pedido>) acessohibernatepedido.carregarPedidosAguardandoEntrega(Pedido.class);
        sger = (Sger) acessohibernatesger.carregaSger();

        /*
        GeoApiContext: Objeto utilizado na API java do Maps. Utilizar o comando
        setApiKey para passar a chave de acesso ao Maps.
         */
        //GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyBNPdACkvr_g58Dy19fyguF14u5ZExDIyM");
        GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyAnkl6gpRCijYhMRGatUCnk3_E0kuS9xms");

        /*
        ListaRotasInicial do tipo RotaTemp contém todas as possíveis rotas iniciais
        partindo do endereço do estabelecimento.
         */
        List<RotaInicial> ListaInicio = new ArrayList<>();
        String[] destinos = new String[ListaPedidos.size()];

        /*
        Loop para carregar os pares de endereços partindo do ponto incial da rota
         */
        for (int pedido = 0; pedido < ListaPedidos.size(); pedido++) {
            RotaInicial ParEnderecos = new RotaInicial();

            ParEnderecos.setId(pedido);
            ParEnderecos.setPedido(ListaPedidos.get(pedido));
            destinos[pedido] = ListaPedidos.get(pedido).getCliente().enderecoToString();

            ListaInicio.add(ParEnderecos);
        }

        /*
        Parte aonde a consulta ao Maps DistanceMatrix acontece para gerar os pontos
        iniciais da rota. A origem sempre será o endereço do estabelecimento e os
        destinos serão os endereços de todos os pedidos em aberto.
         */
        DistanceMatrix distanceMatrix;
        try {
            distanceMatrix = DistanceMatrixApi.newRequest(geoApiContext)
                    .origins(sger.sgerEnderecoToString())
                    .destinations(destinos)
                    .units(Unit.METRIC)
                    .await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /*
        Loop para adicionar a resposta gerada pelo DistanceMatrix a ListaRotasInicial
         */
        for (int lista = 0; lista < ListaInicio.size(); lista++) {
            ListaInicio.get(lista).setDistancia(distanceMatrix.rows[0].elements[lista].duration.inSeconds);
        }

        /*
        Ordena ListaRotasInicial de acordo com o tempo gasto entre os pontos
         */
        Collections.sort(ListaInicio, new comparadorInicio());


        /*
        Variáveis usadas para o controle de entrada em ListaParesPedidos:
        
        id_controle: variável para atribuir um ID em tempo de execução para cada 
        par de pedido gerado
        
        controle_destinos: O array 'destinos' é usado para acumular todos os 
        possíveis endereços de destino a partir de uma única origem, que então 
        será passada ao DistanceMatrix para retornar o tempo gasto entre cada 
        possível ponto. Essa variável controla a entrada de cada destino no array.
        
        controle_listarotas: Usada para certificar que cada resultado extraído 
        do DistanceMatrixseja atribuído corretamente ao seu respectivo par de pedidos.
         */
        int id_controle = 0, controle_destinos = 0, controle_listarotas = 0;

        /*
        Loop para gerar todos os possíveis pares de endereços entre os pedidos
        aguardando entrega
         */
        for (int pedido1 = 0; pedido1 < ListaPedidos.size() - 1; pedido1++) {
            /*
            Para otimizar o código a consulta ao DistaceMatrix é feita por lotes.
            a String origem contém o endereço inicial e String[] destinos contém
            todos os possíveis endereços de destino
             */
            String origem = ListaPedidos.get(pedido1).getCliente().enderecoToString();
            /*
            String[] destinos precisa ser instanciada com tamanho menor que pedido1 + 1.
            Isso ocorre porque a cada interação a quantidade de combinações de pedidos
            diminui. Qualquer coisa diferente disso pode causar problemas no tamanho 
            do index do array. Isso pode causar erros de IndexOutOfBounds ou posições 
            desnecessárias dentro do array.
             */
            destinos = new String[ListaPedidos.size() - (pedido1 + 1)];
            for (int pedido2 = pedido1 + 1; pedido2 < ListaPedidos.size(); pedido2++) {
                RotaTemp ParEnderecos = new RotaTemp();

                /*
                Gravação dos pares de pedidos dentro do objeto RotaTemp, contendo:
                Int Id: variável para controle dos pares de endereço em tempo de execução
                Int pedido1: Id do pedido de origem
                Int pedido2: Id do pedido de destino
                String end1: Endereço do pedido de origem
                String end1: Endereço do pedido de destino
                Long distancia: O tempo gasto em segundos entre os pontos
                 */
                ParEnderecos.setId(id_controle);
                ParEnderecos.setPedido1(ListaPedidos.get(pedido1));
                ParEnderecos.setPedido2(ListaPedidos.get(pedido2));

                /*
                Gravação dos endereços de destino a partir de uma única origem 
                dentro do array 'destinos'. Isso é necessário para que as consultas 
                ao DistanceMatrix sejam feitas em lotes, reduzindo o tempo de execução.
                 */
                destinos[controle_destinos] = ListaPedidos.get(pedido2).getCliente().enderecoToString();

                /*
                Gravação do objeto carregado RotaTemp em ListaParesPedidos
                 */
                ListaParesPedidos.add(ParEnderecos);

                /*
                Adiciona-se +1 em id_controle e controle_destinos para fins de 
                controle de entrada
                 */
                id_controle++;
                controle_destinos++;
            }
            /*
            Zera controle_destinos para que um novo array possa ser gerado com 
            uma nova origem e destinos.
             */
            controle_destinos = 0;

            /*
            Consulta ao DistanceMatrix
             */
            try {
                distanceMatrix = DistanceMatrixApi.newRequest(geoApiContext)
                        .origins(origem)
                        .destinations(destinos)
                        .units(Unit.METRIC)
                        .await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            /*
            controle_elements: variável utilizada para controlar o acesso o resultado
            do DistanceMatrix dentro do objeto 'distanceMatrix'.
            
            o resultado a duração das rotas fica armazenado em uma matriz, sendo 
            'rows' as linha e 'elements' os valores contidos nas colunas de cada linha.
            
            No nosso caso 'rows' sempre terá valor 0, pois as consulta sempre lida
            com apenas uma origem. A quantidade de 'elements' deverá ser igual a
            quantidade de destinos gerados a partir da origem. Ou seja, o tamanho de 
            'elements' será igual ao tamnho do array 'destinos'.
             */
            int controle_elements = 0;

            /*
            Loop para adicionar o tempo entre os ponto em ListaParesPedidos
             */
            for (int lista = controle_listarotas; lista < ListaParesPedidos.size(); lista++) {
                ListaParesPedidos.get(lista).setDistancia(distanceMatrix.rows[0].elements[controle_elements].duration.inSeconds);
                controle_elements++;
            }
            /*
            controle_listarotas recebe o valor de id_controle para que gravação dos
            resultados do DistanceMatrix sejam atribuídos corretamente para o seu
            respectivo par de pedidos.
             */
            controle_listarotas = id_controle;
        }

        /*
        Nessa parte devemos iniciar a distribuição dos endereços para a formação
        das rotas.
        
        Deve-se levar em consideração os seguintes fatores:
            - O ponto inicial e final sempre será o endereço do estabelecimento
            - Deve-se respeitar o peso máximo que um carregador pode levar em sua
            mochila (por enquanto usaremos o padrão de peso máximo igual a 10).
            - O tempo limite da rota deve ser respeitado. O padrão no momento será
            de 30 minutos.
            - De acordo com testes feitos no Directions e JavaScript API a 
            quantidade máxima de pontos de parada são 25 contando com o destino 
            e a origem. Portanto, devemos limitar cada rota a 23 pedidos.
         */
        
        Collections.sort(ListaParesPedidos, new comparadorParesEndereco());
        RotaInicial lucas = new RotaInicial();
        Pedido pedido = new Pedido();
        pedido = (Pedido) acessohibernatepedido.carregarUm(3, Pedido.class);
        
        
       
        while (!ListaParesPedidos.isEmpty()) {


            List<Pedido> ListaRota = new ArrayList<>();
            int qtde_enderecos = 0;
            long tempo = 0;
            float peso = 0;

            ListaRota.add(ListaInicio.get(0).getPedido());
            tempo = ListaInicio.get(0).getDistancia();
            qtde_enderecos++;
            peso = peso + ListaRota.get(0).getPeso();

            ListaInicio.remove(0);

            do {
                for (int pares_pedidos = 0; pares_pedidos <= ListaParesPedidos.size(); pares_pedidos++) {
                    if (ListaRota.get(ListaRota.size() - 1).getId() == ListaParesPedidos.get(pares_pedidos).getPedido1().getId()) {
                        ListaRota.add(ListaParesPedidos.get(pares_pedidos).getPedido2());
                        tempo = tempo + ListaParesPedidos.get(pares_pedidos).getDistancia();
                        qtde_enderecos++;
                        peso = peso + ListaParesPedidos.get(pares_pedidos).getPedido2().getPeso();

//                        RotaInicial rota_inicial = new RotaInicial();
//                        rota_inicial.setPedido(ListaParesPedidos.get(pares_pedidos).getPedido2());
//                        ListaInicio.removeIf(i -> {
//                            return i == rota_inicial;
//                        });

                        for (int i = 0; i < ListaParesPedidos.size(); i++) {
                            if (ListaRota.get(ListaRota.size() - 2).getId() == ListaParesPedidos.get(i).getPedido1().getId() || ListaRota.get(ListaRota.size() - 2).getId() == ListaParesPedidos.get(i).getPedido2().getId()) {
                                ListaParesPedidos.remove(i);
                                i = 0;
                            }
                        }
                        pares_pedidos = 0;
                        continue;
                    }
                    if (ListaRota.get(ListaRota.size() - 1).getId() == ListaParesPedidos.get(pares_pedidos).getPedido2().getId()) {
                        ListaRota.add(ListaParesPedidos.get(pares_pedidos).getPedido1());
                        tempo = tempo + ListaParesPedidos.get(pares_pedidos).getDistancia();
                        qtde_enderecos++;
                        peso = peso + ListaParesPedidos.get(pares_pedidos).getPedido1().getPeso();

//                        RotaInicial rota_inicial = new RotaInicial();
//                        rota_inicial.setPedido(ListaParesPedidos.get(pares_pedidos).getPedido1());
//                        ListaInicio.removeIf(i -> {
//                            return i == rota_inicial;
//                        });

                        for (int i = 0; i < ListaParesPedidos.size(); i++) {
                            if (ListaRota.get(ListaRota.size() - 2).getId() == ListaParesPedidos.get(i).getPedido1().getId() || ListaRota.get(ListaRota.size() - 2).getId() == ListaParesPedidos.get(i).getPedido2().getId()) {
                                ListaParesPedidos.remove(i);
                                i = 0;
                            }
                        }
                        pares_pedidos = 0;
                        continue;
                    }
                }
            } while ((peso <= 10 && tempo <= 1800 && qtde_enderecos <= 23) && !ListaParesPedidos.isEmpty());
        }
        System.exit(0);
    }
}
