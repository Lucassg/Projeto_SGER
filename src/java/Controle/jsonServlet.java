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

        List<Pedido> ListaRelatorio = new ArrayList<>();
        List<Funcionario> ListaEntregador = new ArrayList<>();
        ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosEntregues(Pedido.class, datainicio, datafinal);
        ListaEntregador = (List<Funcionario>) acessohibernatefuncionario.consultaEntregadores(Funcionario.class);

        List<String> ListaMeses = new ArrayList<>();

        ListaRelatorio.forEach(l -> ListaMeses.add(mes.format(l.getData_hora_pedido())));
        Map<String, Long> counts = ListaMeses.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        try (BufferedWriter buff_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Lucas\\Google Drive\\NetBeansProjects\\Projeto_SGER2203\\web\\JSON\\counts_hashmap.json", false), "UTF-8"))) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(counts, buff_writer);
        }

        jsonServlet.pedidosEntregues PedidosEntregues = new jsonServlet.pedidosEntregues();
        List<jsonServlet.pedidosEntregues> ListaPedidosEntregues;
        ListaPedidosEntregues = new ArrayList<>();

        for (Map.Entry<String, Long> count : counts.entrySet()) {
            PedidosEntregues.setMes(count.getKey());
            PedidosEntregues.setQuantidade(count.getValue());
            String[] split_data = PedidosEntregues.getMes().split(" ");

            switch (split_data[0]) {
                case "Janeiro":
                    PedidosEntregues.setMes_numero(1);
                    break;
                case "Fevereiro":
                    PedidosEntregues.setMes_numero(2);
                    break;
                case "Março":
                    PedidosEntregues.setMes_numero(3);
                    break;
                case "Abril":
                    PedidosEntregues.setMes_numero(4);
                    break;
                case "Maio":
                    PedidosEntregues.setMes_numero(5);
                    break;
                case "Junho":
                    PedidosEntregues.setMes_numero(6);
                    break;
                case "Julho":
                    PedidosEntregues.setMes_numero(7);
                    break;
                case "Agosto":
                    PedidosEntregues.setMes_numero(8);
                    break;
                case "Setembro":
                    PedidosEntregues.setMes_numero(9);
                    break;
                case "Outubro":
                    PedidosEntregues.setMes_numero(10);
                    break;
                case "Novembro":
                    PedidosEntregues.setMes_numero(11);
                    break;
                case "Dezembro":
                    PedidosEntregues.setMes_numero(12);
                    break;
            }
            PedidosEntregues.setAno(Integer.parseInt(split_data[1]));
            ListaPedidosEntregues.add(PedidosEntregues);
            PedidosEntregues = new jsonServlet.pedidosEntregues();
        }

        Collections.sort(ListaPedidosEntregues, new jsonServlet.datasComparador());

        String json;
        Gson gson = new Gson();
        gson = new GsonBuilder().create();
//        json = gson.toJson(ListaPedidosEntregues);
        json = gson.toJson(counts);

        response.setContentType("application/json");
        response.getWriter().write(json);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
