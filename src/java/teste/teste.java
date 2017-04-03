package teste;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DaoItens_Pedido;
import dao.DaoPedido;
import dao.DaoSger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import model.Itens_Pedido;
import model.Pedido;
import model.RotaTemp;
import model.Sger;

public class teste {

    public static void main(String[] args) throws IOException {
        int k = 0;
        int qtd = 15; // quantidade de pedidos
        for (int i = qtd - 1; i > 0; i--) {
            k = k + i;
            System.out.println("i: " + i);
            System.out.println("k: " + k);
        }
        System.out.println(k);

        DaoPedido acessohibernatepedido;
        DaoItens_Pedido acessohibernateitenspedido;
        acessohibernatepedido = new DaoPedido();
        acessohibernateitenspedido = new DaoItens_Pedido();
        DaoSger acessohibernatesger = new DaoSger();
        Sger sger = new Sger();
        List<RotaTemp> ListaRotas = new ArrayList<>();

        List<Pedido> ListaPedidos = (List<Pedido>) acessohibernatepedido.carregarPedidosAguardandoEntrega(Pedido.class);
        sger = (Sger) acessohibernatesger.carregaSger();

        int[] pedidos = new int[ListaPedidos.size()];

        for (int i = 0; i < ListaPedidos.size(); i++) {
            pedidos[i] = ListaPedidos.get(i).getId();
            System.out.println(pedidos[i]);
        }

        List<Itens_Pedido> lista = new ArrayList<>();

        lista = (List<Itens_Pedido>) acessohibernateitenspedido.pesquisarTodosItensPedido(Itens_Pedido.class);

        try (Writer writer = new FileWriter("./web/JSON/lista.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(lista, writer);
            System.out.println("Arquivo JSON criado com sucesso.");
        }
        System.exit(0);
    }
}
