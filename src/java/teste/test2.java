
package teste;

import dao.DaoRelatorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Pedido;

public class test2 {
    
    public static void main(String[] args) throws Exception{
        
        DaoRelatorio acessohibernaterelatorio;
        acessohibernaterelatorio = new DaoRelatorio();
        
        List <Pedido> ListaPedidosAbertos = new ArrayList<>();
        ListaPedidosAbertos = (List<Pedido>) acessohibernaterelatorio.PedidosAbertos(Pedido.class);
        
        List<String> ListaStatus = new ArrayList<>();
        
        ListaPedidosAbertos.forEach(l -> ListaStatus.add(l.getStatus()));
        Map<String, Long> counts = ListaStatus.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println("teste");
    }
    
}
