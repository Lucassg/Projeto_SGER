package Controle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DaoPedido;
import dao.DaoRelatorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Area_Entrega;
import model.Pedido;

@WebServlet(name = "ServletIndex", urlPatterns = {"/ServletIndex"})
public class ServletIndex extends HttpServlet {

    private static final long serialVersionUID = 1L;
    DaoRelatorio acessohibernaterelatorio;
    
    public ServletIndex() {

        acessohibernaterelatorio = new DaoRelatorio();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String relatorio = request.getParameter("tipo_relatorio");
        
        if (relatorio.equals("pedidos_abertos")) {
            List<Pedido> ListaPedidosAbertos;
            ListaPedidosAbertos = (List<Pedido>) acessohibernaterelatorio.PedidosAbertos(Pedido.class);

            List<String> ListaStatus = new ArrayList<>();

            ListaPedidosAbertos.forEach(l -> ListaStatus.add(l.getStatus()));
            Map<String, Long> map_pedidos_andamento = ListaStatus.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            String json;
            Gson gson = new Gson();
            gson = new GsonBuilder().create();
            json = gson.toJson(map_pedidos_andamento);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        if (relatorio.equals("pedidos_fechados")) {
            DaoPedido acessohibernatepedido;
            acessohibernatepedido = new DaoPedido();

            List<Pedido> ListaPedidosFechados = (List<Pedido>) acessohibernatepedido.carregarPedidosFechados();

            List<String> ListaStatus = new ArrayList<>();

            ListaPedidosFechados.forEach(l -> ListaStatus.add(l.getStatus()));
            Map<String, Long> map_pedidos_fechados = ListaStatus.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            String json;
            Gson gson = new Gson();
            gson = new GsonBuilder().create();
            json = gson.toJson(map_pedidos_fechados);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
