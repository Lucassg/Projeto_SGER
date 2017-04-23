package teste;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;
import dao.DaoItens_Pedido;
import dao.DaoPedido;
import dao.DaoSger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Pedido;
import model.RotaInicial;
import model.RotaTemp;
import model.Sger;

public class teste_dist_trx {

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

    /*
    Método utilizado para ondenar a lista ListaParesEnderecos
     */
    public static class comparadorParesPedidos implements Comparator<RotaTemp> {

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
        
        /*
        Declaração de acesso as classes DAO
        */
        DaoPedido acessohibernatepedido;
        DaoItens_Pedido acessohibernateitenspedido;
        acessohibernatepedido = new DaoPedido();
        acessohibernateitenspedido = new DaoItens_Pedido();
        DaoSger acessohibernatesger = new DaoSger();
        
        /*
        A classe modelo Sger contém as informações do estabelecimento (endereço e etc).
        
        ListaParesPedidos será carregada com uma dupla de pedidos que posteriomente 
        para fins de controle, consulta e geração de informação junto ao DistanceMatrix
        */
        Sger sger = new Sger();
        List<RotaTemp> ListaParesPedidos = new ArrayList<>();

        /*
        Acessando o banco de dados por meio de Hibernate.
        - 'ListaPedidos' receberá todos os pedidos em status "Aguardando Entrega"
        - 'sger' receberá os dados do estabelecimento
        */
        List<Pedido> ListaPedidos = (List<Pedido>) acessohibernatepedido.carregarPedidosAguardandoEntrega(Pedido.class);
        sger = (Sger) acessohibernatesger.carregaSger();

        /*
        GeoApiContext: Objeto utilizado na API java do Maps. Utilizar o comando
        setApiKey para passar a chave de acesso ao Maps.
         */
        GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyBNPdACkvr_g58Dy19fyguF14u5ZExDIyM");
        //GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyAnkl6gpRCijYhMRGatUCnk3_E0kuS9xms");

        /*
        ListaRotasInicial do tipo RotaTemp contém todas as possíveis rotas iniciais
        partindo do endereço do estabelecimento.
        
        Array 'destinos' conterá todos os endereços de destino a partir do ponto
        de origm especificado no DistanceMatrix
         */
        List<RotaInicial> ListaInicio = new ArrayList<>();
        String[] destinos = new String[ListaPedidos.size()];

        /*
        Loop para carregar os pares de endereços partindo do ponto incial da rota 
        (endereço do estabelecimento).
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
        iniciais da rota. Nesse caso a origem sempre será o endereço do estabelecimento 
        e os destinos serão os endereços de todos os pedidos em aberto.
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
            
            O resultado da duração das rotas fica armazenado em um vetor bidimensional,
            sendo 'rows' as linha e 'elements' os valores contidos nas colunas de cada linha.
            
            No nosso caso 'rows' sempre terá valor 0, pois as consultas sempre terão
            apenas uma origem. A quantidade de 'elements' deverá ser igual a
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
        
        /*
        Ordena ListaParesPedidos de acordo com o tempo entre cada ponto
        */
        Collections.sort(ListaParesPedidos, new comparadorParesPedidos());

        /*
        ListaRotaFinal: Lista bidimensional utilizada para armazena todas as rotas geradas.
        
        remove_pedido e pedido_final são variáveis utilizadas para remover da
        ListaParesPedidos o último pedido associado a uma rota.
        */
        List<List<Pedido>> ListaRotaFinal = new ArrayList<List<Pedido>>();
        Pedido remove_pedido = new Pedido();
        boolean pedido_final = false;

        /*
        Loop para percorrer toda a ListaParesPedidos enquanto for diferente de vazio
        */
        while (!ListaParesPedidos.isEmpty()) {

            RotaTemp rota_temp = new RotaTemp();

            /*
            Condição para remover da ListaParesPedidos o último pedido associado
            a uma rota.
            */
            if (pedido_final == true) {
                rota_temp.setPedido1(remove_pedido);

                ListaParesPedidos.removeIf(i -> {
                    return i.getPedido1() == rota_temp.getPedido1();
                });
                ListaParesPedidos.removeIf(i -> {
                    return i.getPedido2() == rota_temp.getPedido1();
                });
                pedido_final = false;
            }

            /*
            Declaração da ListaRota no qual conterá todos os pedidos associados a
            uma rota.
            
            As variáveis qtde_enderecos, tempo e peso são utilizadas para garantir que
            as rotas não estrapolem as condições estabelecidas.
            */
            List<Pedido> ListaRota = new ArrayList<>();
            int qtde_enderecos = 0;
            long tempo;
            float peso;

            /*
            Adiciona o primeiro ponto de parada a ListaRota, bem como atribiu
            o valor do tempo gasto, peso do pedido e incrementa o contador 
            qdte_enderecos. 
            */
            ListaRota.add(ListaInicio.get(0).getPedido());
            tempo = ListaInicio.get(0).getDistancia();
            qtde_enderecos++;
            peso = ListaRota.get(0).getPeso();

            /*
            Remove o pedido adicionado a ListaRota da ListaInicio para que não
            seja utilizado novamente.
            */
            ListaInicio.remove(0);

            /*
            'pares_pedidos' é utilizada para controlar a entrada em ListaParesPedidos.
            
            */
            int pares_pedidos = 0;
            /*
            Loop para adicionar adicionar os demais pedidos em ListaRota.
            */
            while (pares_pedidos < ListaParesPedidos.size()) {
                
                /*
                As condições abaixo são usadas para e garantir que as regras estabelecidas
                sejam atendidas. Antes que um pedido que esteja em ListaParesPedidos
                seja realmente adiciona em ListaRota as condições devem ser atendidas.
                
                A verificação prévia do peso, tempo e quantidade de pedidos são feitas
                a fim de não estrapolar os limites impostos.
                
                A classe RotaTemp que determina o formato da ListaParesPedido é
                composta de um 'id' de valor inteiro, 'pedido1' e 'pedido2' do tipo
                Pedido e 'distancia' do tipo long.
                
                Ao formar uma dupla de pedidos ('pedido1' e 'pedido2'), considera-se
                a distancia que tanto a distância do 'pedido1' para 'pedido2' e 
                vice-versa são iguais. Por esse motivo existem dois 'If' para checar 
                os dois pedidos de cada posição da ListaParesPedidos.
                */
                if (ListaRota.get(ListaRota.size() - 1).getId() == ListaParesPedidos.get(pares_pedidos).getPedido1().getId()
                        && ((peso + ListaParesPedidos.get(pares_pedidos).getPedido2().getPeso()) <= 10)
                        && ((tempo + ListaParesPedidos.get(pares_pedidos).getDistancia()) <= 1800)
                        && ((qtde_enderecos + 1) <= 23)) {

                    /*
                    Adiciona o pedido a ListaRota e soma-se adiciona-se um novo valor
                    a 'tempo', 'peso' e 'qtde_enderecos'.
                    
                    Nessa condição a última posição de ListaRota é comparada com 
                    o 'pedido1' de ListaParesPedidos de acordo com o valor do contador
                    pares_pedidos.
                    
                    Isso significa que o valor comparado já existe em ListaRota e
                    próximo valor deve ser o 'pedido2' contido naquela posição de
                    ListaParesPedidos.
                    */
                    ListaRota.add(ListaParesPedidos.get(pares_pedidos).getPedido2());
                    tempo = tempo + ListaParesPedidos.get(pares_pedidos).getDistancia();
                    qtde_enderecos++;
                    peso = peso + ListaParesPedidos.get(pares_pedidos).getPedido2().getPeso();

                    /*
                    Quando um pedido é atribuído a uma rota o mesmo deve ser removido
                    da ListaInicio, evitando que seja usado novamente ao determinar
                    o primeiro ponto de entrega de uma nova rota.
                    */
                    RotaInicial rota_inicial = new RotaInicial();
                    rota_inicial.setPedido(ListaParesPedidos.get(pares_pedidos).getPedido2());
                    ListaInicio.removeIf(i -> {
                        return i.getPedido() == rota_inicial.getPedido();
                    });

                    /*
                    O código abaixo remove de ListaParesPedidos todas as possibilidades 
                    contendo a penúltimo pedido adicionado a ListaRota, não permitindo
                    que o mesmo seja utilizado novamente.
                    */
                    rota_temp.setPedido1(ListaRota.get(ListaRota.size() - 2));

                    ListaParesPedidos.removeIf(i -> {
                        return i.getPedido1() == rota_temp.getPedido1();
                    });
                    ListaParesPedidos.removeIf(i -> {
                        return i.getPedido2() == rota_temp.getPedido1();
                    });

                    /*
                    Seta pares_pedidos novamente para zero, permitindo que ListaParesPedidos
                    seja percorrida a partir da posição inicial na próxima iteração.
                    */
                    pares_pedidos = 0;
                    continue;
                }
                /*
                Segunda condição 'If' utilizada para checar o 'pedido2' da mesma
                posição de ListaParesPedidos. As instruções contidas nessa parte
                do código é semelhante a condição 'If' a cima, mudando apenas o 
                'pedido2' é comparado e o 'pedido1' é removido caso todas as condições
                são atendidas.
                */
                if (ListaRota.get(ListaRota.size() - 1).getId() == ListaParesPedidos.get(pares_pedidos).getPedido2().getId()
                        && ((peso + ListaParesPedidos.get(pares_pedidos).getPedido1().getPeso()) <= 10)
                        && ((tempo + ListaParesPedidos.get(pares_pedidos).getDistancia()) <= 1800)
                        && ((qtde_enderecos + 1) <= 23)) {

                    ListaRota.add(ListaParesPedidos.get(pares_pedidos).getPedido1());
                    tempo = tempo + ListaParesPedidos.get(pares_pedidos).getDistancia();
                    qtde_enderecos++;
                    peso = peso + ListaParesPedidos.get(pares_pedidos).getPedido1().getPeso();

                    RotaInicial rota_inicial = new RotaInicial();
                    rota_inicial.setPedido(ListaParesPedidos.get(pares_pedidos).getPedido1());
                    ListaInicio.removeIf(i -> {
                        return i.getPedido() == rota_inicial.getPedido();
                    });

                    rota_temp.setPedido1(ListaRota.get(ListaRota.size() - 2));

                    ListaParesPedidos.removeIf(i -> {
                        return i.getPedido1() == rota_temp.getPedido1();
                    });
                    ListaParesPedidos.removeIf(i -> {
                        return i.getPedido2() == rota_temp.getPedido1();
                    });

                    pares_pedidos = 0;
                    continue;
                }
                /*
                Caso nenhuma das condições sejam verdadeiras adiciona-se +1 a 
                pares_pedidos.
                */
                pares_pedidos++;
            }
            /*
            Nesse ponto uma rota foi gerada por completo. No entanto, o último 
            pedido atribuído a ListaRota não foi removido de ListaParesPedidos, 
            podendo ser utilizado caso uma nova rota seja criada.
            
            Para evitar esse problema o último pedido contido em ListaRota é
            atribuído a remove_pedido e pedido_final recebe o valor de true.
            Ambas variáveis serão utilizadas novamente na condição 'If' no início
            primeiro loop (linha 305).
            
            O pedido só pode ser removido na próxima iteração, pois o controle 
            de entrada do primeiro loop depende de ListaParesPedidos ser diferente
            de vazio.
            */
            remove_pedido = ListaRota.get(ListaRota.size() - 1);
            pedido_final = true;
            
            /*
            Adiciona-se ListaRota a ListaRotaFinal.
            ListaRota é instanciada novamente para uma nova rota.
            */
            ListaRotaFinal.add(ListaRota);
            ListaRota = new ArrayList<>();
        }

//        for (int i = 0; i < ListaRotaFinal.size(); i++) {
//            try (Writer writer = new FileWriter("./web/JSON/listarotafinal" + i + ".json")) {
//                Gson gson = new GsonBuilder().create();
//                gson.toJson(ListaRotaFinal.get(i), writer);
//                System.out.println("Arquivo JSON criado com sucesso.");
//            }
//        }
        System.exit(0);
    }
}
