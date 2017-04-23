
package Controle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import model.Pedido;

@WebServlet(name = "jsonServleti", urlPatterns = {"/jsonServleti"})
public class jsonServleti extends HttpServlet {

    private static final long serialVersionUID = 1L;
    DaoRelatorio acessohibernaterelatorio;
    
    public jsonServleti() {

        acessohibernaterelatorio = new DaoRelatorio();

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        List <Pedido> ListaPedidosAbertos;
        ListaPedidosAbertos = (List<Pedido>) acessohibernaterelatorio.PedidosAbertos(Pedido.class);
        
        List<String> ListaStatus = new ArrayList<>();
        
        ListaPedidosAbertos.forEach(l -> ListaStatus.add(l.getStatus()));
        Map<String, Long> counts = ListaStatus.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        
        String json;
        Gson gson = new Gson();
        gson = new GsonBuilder().create();
        json = gson.toJson(counts);

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
   
    }

    
}
