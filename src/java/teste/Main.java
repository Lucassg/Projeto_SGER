package teste;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;
import dao.DaoPedido;
import dao.DaoSger;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import model.RotaTemp;
import model.Sger;

public class Main {

    public static long consulta_maps(GeoApiContext geoApiContext, String origem, String destinos) {

        try {
            DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(geoApiContext)
                    .origins(origem)
                    .destinations(destinos)
                    .units(Unit.METRIC)
                    .await();

            return distanceMatrix.rows[0].elements[0].duration.inSeconds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {

        DaoPedido acessohibernatepedido;
        acessohibernatepedido = new DaoPedido();
        DaoSger acessohibernatesger = new DaoSger();
        Sger sger = new Sger();
        List<RotaTemp> ListaRotas = new ArrayList<>();

        List<Pedido> ListaPedidos;
        ListaPedidos = (List<Pedido>) acessohibernatepedido.carregarPedidosAguardandoEntrega(Pedido.class);
        sger = (Sger) acessohibernatesger.carregaSger();

        try (Writer writer = new FileWriter("./web/JSON/teste.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(ListaPedidos, writer);
            System.out.println("Arquivo JSON criado com sucesso.");
        }

        GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyBNPdACkvr_g58Dy19fyguF14u5ZExDIyM");

        int a = 1;
        for (int i = 0; i < ListaPedidos.size(); i++) {
            for (int k = i + 1; k < ListaPedidos.size(); k++) {

                RotaTemp ParEnderecos = new RotaTemp();

//                ParEnderecos.setId(a);
//                ParEnderecos.setPedido1(ListaPedidos.get(i).getId());
//                ParEnderecos.setPedido2(ListaPedidos.get(k).getId());
//                ParEnderecos.setEnd1(ListaPedidos.get(i).getCliente().enderecoToString());
//                ParEnderecos.setEnd2(ListaPedidos.get(k).getCliente().enderecoToString());
//                ParEnderecos.setDistancia(consulta_maps(geoApiContext, ListaPedidos.get(i).getCliente().enderecoToString(), ListaPedidos.get(k).getCliente().enderecoToString()));

                ListaRotas.add(ParEnderecos);

                a++;
            }
        }

        try (Writer writer = new FileWriter("./web/JSON/teste.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(ListaRotas, writer);
            System.out.println("Arquivo JSON criado com sucesso.");
        }

        long[][] matriz = new long[ListaPedidos.size()][ListaPedidos.size()];
        int linha, coluna, in = 0;
        
        for (linha = 0; linha < ListaPedidos.size(); linha++) {
            for (coluna = 0; coluna < ListaPedidos.size(); coluna++) {
                matriz[linha][coluna] = -1;
            }
        }
        
        for (linha = 0; linha < ListaPedidos.size(); linha++) {
            for (coluna = 0; coluna < ListaPedidos.size(); coluna++) {
                if (linha == coluna) {
                    matriz[linha][coluna] = 0;
                } else {
                    if (matriz[linha][coluna] == -1) {
                        matriz[linha][coluna] = ListaRotas.get(in).getDistancia();
                        matriz[coluna][linha] = ListaRotas.get(in).getDistancia();
                        in++;
                    }
                }
            }
        }

        for (linha = 0; linha < ListaPedidos.size(); linha++) {
            for (coluna = 0; coluna < ListaPedidos.size(); coluna++) {
                System.out.printf("\t %d \t", matriz[linha][coluna]);
            }
            System.out.println("\n");
        }
        System.out.println("teste");
        System.out.println("size: " + ListaRotas.size());
        System.exit(0);
    }
}
