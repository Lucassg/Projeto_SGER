package Controle;

import Controle.logico.ControleLogicoRelatorio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DaoFuncionario;
import dao.DaoRelatorio;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Pedido;

@WebServlet(name = "jsonServlet", urlPatterns = {"/jsonServlet"})
public class jsonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    DaoRelatorio acessohibernaterelatorio;
    DaoFuncionario acessohibernatefuncionario;

    public jsonServlet() {

        acessohibernaterelatorio = new DaoRelatorio();
        acessohibernatefuncionario = new DaoFuncionario();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("tipo_relatorio");

        switch (acao) {
            case "Pedidos Entregues":
                pedidos_entregues(request, response);
                break;
            default:
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void pedidos_entregues(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Date datainicio = new Date();
        Date datafinal = new Date();
        String data_inicial = request.getParameter("datainicial");
        String data_final = request.getParameter("datafinal");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            fmt.setLenient(false);
            datainicio = fmt.parse(data_inicial);
            datafinal = fmt.parse(data_final);
        } catch (ParseException ex) {
            Logger.getLogger(ControleLogicoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Pedido> ListaRelatorio = new ArrayList<>();
        ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosEntregues(Pedido.class, datainicio, datafinal);

        List<String> ListaDatas = new ArrayList<>();

        String json;
        Gson gson = new Gson();

        String dia_mes = request.getParameter("dia_mes");

        if (dia_mes.equals("mes")) {

            SimpleDateFormat mes = new SimpleDateFormat("MM-MMMMM yyyy");

            ListaRelatorio.forEach(l -> ListaDatas.add(mes.format(l.getData_hora_pedido())));
            Map<String, Long> counts = ListaDatas.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            jsonServlet.pedidosEntregues PedidosEntregues = new jsonServlet.pedidosEntregues();
            List<jsonServlet.pedidosEntregues> ListaPedidosEntregues;
            ListaPedidosEntregues = new ArrayList<>();

            for (Map.Entry<String, Long> count : counts.entrySet()) {

                String[] split_data = count.getKey().split(" ");
                String[] split_data2 = count.getKey().split("-");

                PedidosEntregues.setDia_ano(Integer.parseInt(split_data2[0]));
                PedidosEntregues.setData(split_data2[1]);
                PedidosEntregues.setQuantidade(count.getValue());
                PedidosEntregues.setAno(Integer.parseInt(split_data[1]));

                ListaPedidosEntregues.add(PedidosEntregues);
                PedidosEntregues = new jsonServlet.pedidosEntregues();
            }

            Collections.sort(ListaPedidosEntregues, new jsonServlet.datasComparador());

            gson = new GsonBuilder().create();
            json = gson.toJson(ListaPedidosEntregues);
        } else {

            SimpleDateFormat dia = new SimpleDateFormat("D-d MMM yyyy");

            ListaRelatorio.forEach(l -> ListaDatas.add(dia.format(l.getData_hora_pedido())));
            Map<String, Long> counts = ListaDatas.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            jsonServlet.pedidosEntregues PedidosEntregues = new jsonServlet.pedidosEntregues();
            List<jsonServlet.pedidosEntregues> ListaPedidosEntregues;
            ListaPedidosEntregues = new ArrayList<>();

            for (Map.Entry<String, Long> count : counts.entrySet()) {

                String[] split_data = count.getKey().split(" ");
                String[] split_data2 = count.getKey().split("-");

                PedidosEntregues.setDia_ano(Integer.parseInt(split_data2[0]));
                PedidosEntregues.setData(split_data2[1]);
                PedidosEntregues.setQuantidade(count.getValue());
                PedidosEntregues.setAno(Integer.parseInt(split_data[2]));

                ListaPedidosEntregues.add(PedidosEntregues);
                PedidosEntregues = new jsonServlet.pedidosEntregues();
            }

            Collections.sort(ListaPedidosEntregues, new jsonServlet.datasComparador());

            gson = new GsonBuilder().create();
            json = gson.toJson(ListaPedidosEntregues);
        }

        response.setContentType("application/json");
        response.getWriter().write(json);

    }
    
    static class pedidosEntregues {

        private String data;
        private Long quantidade;
        private int dia_ano;
        private int ano;

        /**
         * @return the data
         */
        public String getData() {
            return data;
        }

        /**
         * @param data the data to set
         */
        public void setData(String data) {
            this.data = data;
        }

        /**
         * @return the quantidade
         */
        public Long getQuantidade() {
            return quantidade;
        }

        /**
         * @param quantidade the quantidade to set
         */
        public void setQuantidade(Long quantidade) {
            this.quantidade = quantidade;
        }

        /**
         * @return the dia_ano
         */
        public int getDia_ano() {
            return dia_ano;
        }

        /**
         * @param dia_ano the dia_ano to set
         */
        public void setDia_ano(int dia_ano) {
            this.dia_ano = dia_ano;
        }

        /**
         * @return the ano
         */
        public int getAno() {
            return ano;
        }

        /**
         * @param ano the ano to set
         */
        public void setAno(int ano) {
            this.ano = ano;
        }
    }

    public static class datasComparador implements Comparator<pedidosEntregues> {

        @Override
        public int compare(pedidosEntregues s1, pedidosEntregues s2) {
            Integer comparar_data = s1.getAno();
            comparar_data = comparar_data.compareTo(s2.getAno());
            if (comparar_data != 0) {
                return comparar_data;
            }
            return Integer.compare(s1.getDia_ano(), s2.getDia_ano());
        }
    }

}
