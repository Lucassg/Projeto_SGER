package Controle;

import Controle.logico.ControleLogicoRelatorio;
import Controle.logico.ControleLogicoRota;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DaoFuncionario;
import dao.DaoRelatorio;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import model.RotaTemp;

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

        Date datainicio = new Date();
        Date datafinal = new Date();
        String data_inicial = request.getParameter("datainicial");
        String data_final = request.getParameter("datafinal");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat mes = new SimpleDateFormat("MMMMM yyyy");

        try {
            fmt.setLenient(false);
            datainicio = fmt.parse(data_inicial);
            datafinal = fmt.parse(data_final);
        } catch (ParseException ex) {
            Logger.getLogger(ControleLogicoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("data inicial serverlet: " + data_inicial);
        System.out.println("data final servlet: " + data_final);

        List<Pedido> ListaRelatorio = new ArrayList<>();
        List<Funcionario> ListaEntregador = new ArrayList<>();
        ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosEntregues(Pedido.class, datainicio, datafinal);
        ListaEntregador = (List<Funcionario>) acessohibernatefuncionario.consultaEntregadores(Funcionario.class);
        
        ListaRelatorio.forEach(l -> System.out.println("id: " + l.getId() + " data: " + l.getData_hora_pedido()));

        List<String> ListaMeses = new ArrayList<>();

        ListaRelatorio.forEach(l -> ListaMeses.add(mes.format(l.getData_hora_pedido())));
        Map<String, Long> counts = ListaMeses.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        jsonServlet.pedidosEntregues PedidosEntregues = new jsonServlet.pedidosEntregues();
        List<jsonServlet.pedidosEntregues> ListaPedidosEntregues;
        ListaPedidosEntregues = new ArrayList<>();

        for (Map.Entry<String, Long> count : counts.entrySet()) {
            PedidosEntregues.setMes(count.getKey());
            PedidosEntregues.setQuantidade(count.getValue());
            String[] split_data = PedidosEntregues.getMes().split(" ");
            if ("Janeiro".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(1);
            }
            if ("Fevereiro".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(2);
            }
            if ("Março".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(3);
            }
            if ("Abril".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(4);
            }
            if ("Maio".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(5);
            }
            if ("Junho".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(6);
            }
            if ("Julho".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(7);
            }
            if ("Agosto".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(8);
            }
            if ("Setembro".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(9);
            }
            if ("Outubro".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(10);
            }
            if ("Novembro".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(11);
            }
            if ("Dezembro".equals(split_data[0])) {
                PedidosEntregues.setMes_numero(12);
            }
            PedidosEntregues.setAno(Integer.parseInt(split_data[1]));
            ListaPedidosEntregues.add(PedidosEntregues);
            PedidosEntregues = new jsonServlet.pedidosEntregues();
        }

        //Collections.sort(ListaPedidosEntregues, new jsonServlet.comparadorDatas());
        Collections.sort(ListaPedidosEntregues, new jsonServlet.datasComparador());

        String json;
        Gson gson = new Gson();
        gson = new GsonBuilder().create();
        json = gson.toJson(ListaPedidosEntregues);

        ListaPedidosEntregues.forEach(l -> System.out.println("data: " + l.getMes()));

        response.setContentType("application/json");
        response.getWriter().write(json);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Date datainicio = new Date();
        Date datafinal = new Date();
        String data_inicial = request.getParameter("datainicial");
        String data_final = request.getParameter("datafinal");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat mes = new SimpleDateFormat("MMMMM");

        try {
            fmt.setLenient(false);
            datainicio = fmt.parse(data_inicial);
            datafinal = fmt.parse(data_final);
        } catch (ParseException ex) {
            Logger.getLogger(ControleLogicoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("data inicial serverlet: " + data_inicial);
        System.out.println("data final servlet: " + data_final);

        List<Pedido> ListaRelatorio = new ArrayList<>();
        List<Funcionario> ListaEntregador = new ArrayList<>();
        ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosEntregues(Pedido.class, datainicio, datafinal);
        ListaEntregador = (List<Funcionario>) acessohibernatefuncionario.consultaEntregadores(Funcionario.class);

        List<String> ListaMeses = new ArrayList<>();

        ListaRelatorio.forEach(l -> ListaMeses.add(mes.format(l.getData_hora_pedido())));
        Map<String, Long> counts = ListaMeses.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        jsonServlet.pedidosEntregues PedidosEntregues = new jsonServlet.pedidosEntregues();
        List<jsonServlet.pedidosEntregues> ListaPedidosEntregues;
        ListaPedidosEntregues = new ArrayList<>();

        for (Map.Entry<String, Long> count : counts.entrySet()) {
            PedidosEntregues.setMes(count.getKey());
            PedidosEntregues.setQuantidade(count.getValue());
            if (PedidosEntregues.getMes() == "Janeiro") {
                PedidosEntregues.setMes_numero(1);
            }
            if (PedidosEntregues.getMes() == "Fevereiro") {
                PedidosEntregues.setMes_numero(2);
            }
            if (PedidosEntregues.getMes() == "Março") {
                PedidosEntregues.setMes_numero(3);
            }
            if (PedidosEntregues.getMes() == "Abril") {
                PedidosEntregues.setMes_numero(4);
            }
            if (PedidosEntregues.getMes() == "Maio") {
                PedidosEntregues.setMes_numero(5);
            }
            if (PedidosEntregues.getMes() == "Junho") {
                PedidosEntregues.setMes_numero(6);
            }
            if (PedidosEntregues.getMes() == "Julho") {
                PedidosEntregues.setMes_numero(7);
            }
            if (PedidosEntregues.getMes() == "Agosto") {
                PedidosEntregues.setMes_numero(8);
            }
            if (PedidosEntregues.getMes() == "Setembro") {
                PedidosEntregues.setMes_numero(9);
            }
            if (PedidosEntregues.getMes() == "Outubro") {
                PedidosEntregues.setMes_numero(10);
            }
            if (PedidosEntregues.getMes() == "Novembro") {
                PedidosEntregues.setMes_numero(11);
            }
            if (PedidosEntregues.getMes() == "Dezembro") {
                PedidosEntregues.setMes_numero(12);
            }
            ListaPedidosEntregues.add(PedidosEntregues);
            PedidosEntregues = new jsonServlet.pedidosEntregues();
        }

        Collections.sort(ListaPedidosEntregues, new jsonServlet.comparadorDatas());

        String json;
        Gson gson = new Gson();
        gson = new GsonBuilder().create();
        json = gson.toJson(ListaPedidosEntregues);

        response.setContentType("application/json");
        response.getWriter().write(json);

        //request.getRequestDispatcher("relatorios").forward(request, response);
    }

    static class pedidosEntregues {

        private String mes;
        private Long quantidade;
        private int mes_numero;
        private int ano;

        /**
         * @return the mes
         */
        public String getMes() {
            return mes;
        }

        /**
         * @param mes the mes to set
         */
        public void setMes(String mes) {
            this.mes = mes;
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
         * @return the mes_numero
         */
        public int getMes_numero() {
            return mes_numero;
        }

        /**
         * @param mes_numero the mes_numero to set
         */
        public void setMes_numero(int mes_numero) {
            this.mes_numero = mes_numero;
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

    public static class comparadorDatas implements Comparator<pedidosEntregues> {

        @Override
        public int compare(pedidosEntregues p1, pedidosEntregues p2) {
            if (p1.getAno() < p2.getAno() || p1.getMes_numero() < p2.getMes_numero()) {
                return -1;
            } else if (p1.getAno() > p2.getAno() || p1.getMes_numero() > p2.getMes_numero()) {
                return +1;
            } else {
                return 0;
            }
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
            return Integer.compare(s1.getMes_numero(), s2.getMes_numero());
        }

    }
}
