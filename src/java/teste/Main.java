package teste;

import dao.DaoItens_Pedido;
import dao.DaoPedido;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Pedido;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        DaoPedido acessohibernatepedido;
        acessohibernatepedido = new DaoPedido();

        List<Pedido> ListaPedidosFechados = (List<Pedido>) acessohibernatepedido.carregarPedidosFechados();

        List<String> ListaStatus = new ArrayList<>();

        ListaPedidosFechados.forEach(l -> ListaStatus.add(l.getStatus()));
        Map<String, Long> counts = ListaStatus.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        System.exit(0);

    }
}
