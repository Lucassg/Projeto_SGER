package Controle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DaoRelatorio;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

        List<Pedido> ListaPedidosAbertos;
        ListaPedidosAbertos = (List<Pedido>) acessohibernaterelatorio.PedidosAbertos(Pedido.class);

        List<String> ListaStatus = new ArrayList<>();

        ListaPedidosAbertos.forEach(l -> ListaStatus.add(l.getStatus()));
        Map<String, Long> counts = ListaStatus.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        List<jsonServleti.pedidoStatus> ListaQuantidade = new ArrayList<jsonServleti.pedidoStatus>();
        jsonServleti.pedidoStatus pedidos_status;

        for (Map.Entry<String, Long> count : counts.entrySet()) {
            
            pedidos_status = new pedidoStatus();
            pedidos_status.setStatus(count.getKey());
            pedidos_status.setQuantidade(count.getValue());
            ListaQuantidade.add(pedidos_status);
        }

        try (BufferedWriter buff_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Lucas\\Google Drive\\NetBeansProjects\\Projeto_SGER2203\\web\\JSON\\ListaQuantidade.json", false), "UTF-8"))) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(ListaQuantidade, buff_writer);
        }

        String json;
        Gson gson = new Gson();
        gson = new GsonBuilder().create();
        json = gson.toJson(ListaQuantidade);

        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    static class pedidoStatus {

        private String status;
        private long quantidade;

        /**
         * @return the status
         */
        public String getStatus() {
            return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * @return the quantidade
         */
        public long getQuantidade() {
            return quantidade;
        }

        /**
         * @param quantidade the quantidade to set
         */
        public void setQuantidade(long quantidade) {
            this.quantidade = quantidade;
        }
    }
}
