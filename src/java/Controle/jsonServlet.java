package Controle;

import Controle.logico.ControleLogicoRelatorio;
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
import model.Rota;

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
            case "Pedidos Nao Entregues":
                pedidos_nao_entregues(request, response);
                break;
            case "Pedidos Por Entregador":
                pedidos_entregador(request, response);
                break;
            case "Prejuizo Gerado":
                prejuizo_gerado(request, response);
                break;
            case "Pedidos Nao Entregues Por Justificativa":
                relarotio_justificativas(request, response);
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

                PedidosEntregues.setMes_dia(Integer.parseInt(split_data2[0]));
                PedidosEntregues.setData(split_data2[1]);
                PedidosEntregues.setQuantidade(count.getValue());
                PedidosEntregues.setAno(Integer.parseInt(split_data[1]));

                ListaPedidosEntregues.add(PedidosEntregues);
                PedidosEntregues = new jsonServlet.pedidosEntregues();
            }

            Collections.sort(ListaPedidosEntregues, new jsonServlet.datasRelEntreguesComparador());

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

                PedidosEntregues.setMes_dia(Integer.parseInt(split_data2[0]));
                PedidosEntregues.setData(split_data2[1]);
                PedidosEntregues.setQuantidade(count.getValue());
                PedidosEntregues.setAno(Integer.parseInt(split_data[2]));

                ListaPedidosEntregues.add(PedidosEntregues);
                PedidosEntregues = new jsonServlet.pedidosEntregues();
            }

            Collections.sort(ListaPedidosEntregues, new jsonServlet.datasRelEntreguesComparador());

            gson = new GsonBuilder().create();
            json = gson.toJson(ListaPedidosEntregues);
        }

        response.setContentType("application/json");
        response.getWriter().write(json);

    }

    public void pedidos_nao_entregues(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosNaoEntregues(Pedido.class, datainicio, datafinal);

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

                PedidosEntregues.setMes_dia(Integer.parseInt(split_data2[0]));
                PedidosEntregues.setData(split_data2[1]);
                PedidosEntregues.setQuantidade(count.getValue());
                PedidosEntregues.setAno(Integer.parseInt(split_data[1]));

                ListaPedidosEntregues.add(PedidosEntregues);
                PedidosEntregues = new jsonServlet.pedidosEntregues();
            }

            Collections.sort(ListaPedidosEntregues, new jsonServlet.datasRelEntreguesComparador());

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

                PedidosEntregues.setMes_dia(Integer.parseInt(split_data2[0]));
                PedidosEntregues.setData(split_data2[1]);
                PedidosEntregues.setQuantidade(count.getValue());
                PedidosEntregues.setAno(Integer.parseInt(split_data[2]));

                ListaPedidosEntregues.add(PedidosEntregues);
                PedidosEntregues = new jsonServlet.pedidosEntregues();
            }

            Collections.sort(ListaPedidosEntregues, new jsonServlet.datasRelEntreguesComparador());

            gson = new GsonBuilder().create();
            json = gson.toJson(ListaPedidosEntregues);
        }

        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    public void pedidos_entregador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Date datainicio = new Date();
        Date datafinal = new Date();
        String data_inicial = request.getParameter("datainicial");
        String data_final = request.getParameter("datafinal");
        int id_entregador = Integer.parseInt(request.getParameter("entregador"));

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            fmt.setLenient(false);
            datainicio = fmt.parse(data_inicial);
            datafinal = fmt.parse(data_final);
        } catch (ParseException ex) {
            Logger.getLogger(ControleLogicoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Pedido> ListaRelatorio = new ArrayList<>();
        List<Rota> ListaRotaEntregador = new ArrayList<Rota>();
        List<Funcionario> ListaFuncionario = new ArrayList<Funcionario>();

        ListaFuncionario = (List<Funcionario>) acessohibernaterelatorio.consultaEntregador(Funcionario.class, id_entregador);
        Funcionario funcionario = (Funcionario) ListaFuncionario.get(0);
        ListaRotaEntregador = (List<Rota>) acessohibernaterelatorio.consultaRotaEntregador(Rota.class, funcionario, datainicio, datafinal);
        ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosEntregador(Pedido.class, datainicio, datafinal, ListaRotaEntregador);

        List<String> ListaDatas = new ArrayList<>();

        String json;
        Gson gson = new Gson();

        String dia_mes = request.getParameter("dia_mes");

        if (dia_mes.equals("mes")) {

            SimpleDateFormat mes = new SimpleDateFormat("MM MMMMM yyyy");

            ListaRelatorio.forEach(l -> ListaDatas.add(mes.format(l.getData_hora_pedido()) + " " + l.getStatus()));
            Map<String, Long> counts = ListaDatas.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            final List<jsonServlet.pedidosEntregador> ListaPedidosEntregador = new ArrayList<>();

            lab1:
            for (Map.Entry<String, Long> count : counts.entrySet()) {

                jsonServlet.pedidosEntregador PedidosEntregador = new jsonServlet.pedidosEntregador();

                String[] split_data = count.getKey().split(" ");

                PedidosEntregador.setAno(Integer.parseInt(split_data[2]));
                PedidosEntregador.setMes_dia(Integer.parseInt(split_data[0]));
                PedidosEntregador.setData(split_data[1] + " " + split_data[2]);
                if (split_data[3].equals("Entregue")) {
                    PedidosEntregador.setQtde_entregue(count.getValue());
                } else {
                    PedidosEntregador.setQtde_nentregue(count.getValue());
                }

                for (jsonServlet.pedidosEntregador listaentregador : ListaPedidosEntregador) {
                    if ((listaentregador.getData().equals(PedidosEntregador.getData()) && (listaentregador.getQtde_entregue() != null))) {
                        listaentregador.setQtde_nentregue(PedidosEntregador.getQtde_nentregue());
                        continue lab1;
                    }
                    if ((listaentregador.getData().equals(PedidosEntregador.getData()) && (listaentregador.getQtde_nentregue() != null))) {
                        listaentregador.setQtde_entregue(PedidosEntregador.getQtde_entregue());
                        continue lab1;
                    }
                }
                ListaPedidosEntregador.add(PedidosEntregador);
            }

            Long zero = new Long(0);

            ListaPedidosEntregador.forEach(l -> {

                if (l.getQtde_entregue() == null) {
                    l.setQtde_entregue(zero);
                }
                if (l.getQtde_nentregue() == null) {
                    l.setQtde_nentregue(zero);
                }
            });

            Collections.sort(ListaPedidosEntregador, new jsonServlet.datasEntregadorComparador());

            gson = new GsonBuilder().create();
            json = gson.toJson(ListaPedidosEntregador);
        } else {

            SimpleDateFormat dia = new SimpleDateFormat("D d MMM yyyy");

            ListaRelatorio.forEach(l -> ListaDatas.add(dia.format(l.getData_hora_pedido()) + " " + l.getStatus()));
            Map<String, Long> counts = ListaDatas.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            final List<jsonServlet.pedidosEntregador> ListaPedidosEntregador = new ArrayList<>();

            lab1:
            for (Map.Entry<String, Long> count : counts.entrySet()) {

                jsonServlet.pedidosEntregador PedidosEntregador = new jsonServlet.pedidosEntregador();

                String[] split_data = count.getKey().split(" ");

                PedidosEntregador.setAno(Integer.parseInt(split_data[3]));
                PedidosEntregador.setMes_dia(Integer.parseInt(split_data[0]));
                PedidosEntregador.setData(split_data[1] + " " + split_data[2] + " " + split_data[3]);

                if (split_data[4].equals("Entregue")) {
                    PedidosEntregador.setQtde_entregue(count.getValue());
                } else {
                    PedidosEntregador.setQtde_nentregue(count.getValue());
                }

                for (jsonServlet.pedidosEntregador listaentregador : ListaPedidosEntregador) {
                    if ((listaentregador.getData().equals(PedidosEntregador.getData()) && (listaentregador.getQtde_entregue() != null))) {
                        listaentregador.setQtde_nentregue(PedidosEntregador.getQtde_nentregue());
                        continue lab1;
                    }
                    if ((listaentregador.getData().equals(PedidosEntregador.getData()) && (listaentregador.getQtde_nentregue() != null))) {
                        listaentregador.setQtde_entregue(PedidosEntregador.getQtde_entregue());
                        continue lab1;
                    }
                }
                ListaPedidosEntregador.add(PedidosEntregador);
            }

            Long zero = new Long(0);

            ListaPedidosEntregador.forEach(l -> {

                if (l.getQtde_entregue() == null) {
                    l.setQtde_entregue(zero);
                }
                if (l.getQtde_nentregue() == null) {
                    l.setQtde_nentregue(zero);
                }
            });

            Collections.sort(ListaPedidosEntregador, new jsonServlet.datasEntregadorComparador());

            gson = new GsonBuilder().create();
            json = gson.toJson(ListaPedidosEntregador);
        }

        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    public void prejuizo_gerado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosNaoEntregues(Pedido.class, datainicio, datafinal);

        List<String> ListaDatas = new ArrayList<>();

        String json;
        Gson gson = new Gson();

        String dia_mes = request.getParameter("dia_mes");

        if (dia_mes.equals("mes")) {

            SimpleDateFormat mes = new SimpleDateFormat("MM MMMMM yyyy");

            ListaRelatorio.forEach(l -> ListaDatas.add(mes.format(l.getData_hora_pedido())));
            List<String> ListaValores = new ArrayList<>();
            ListaRelatorio.forEach(l -> ListaValores.add(mes.format(l.getData_hora_pedido()) + " " + l.getValor()));
            Map<String, Long> counts = ListaDatas.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            jsonServlet.prejuizoGerado PrejuizoGerado = new jsonServlet.prejuizoGerado();
            List<jsonServlet.prejuizoGerado> ListaPrejuizoGerado = new ArrayList<>();

            for (Map.Entry<String, Long> count : counts.entrySet()) {

                String[] split_data = count.getKey().split(" ");

                PrejuizoGerado.setMes_dia(Integer.parseInt(split_data[0]));
                PrejuizoGerado.setData(split_data[1] + " " + split_data[2]);
                PrejuizoGerado.setAno(Integer.parseInt(split_data[2]));

                ListaPrejuizoGerado.add(PrejuizoGerado);
                PrejuizoGerado = new jsonServlet.prejuizoGerado();
            }

            for (String valor : ListaValores) {

                int contador = 0;
                String[] split_valor = valor.split(" ");
                String data = split_valor[1] + " " + split_valor[2];

                for (jsonServlet.prejuizoGerado pe : ListaPrejuizoGerado) {

                    if (pe.getData().equals(data)) {
                        pe.setPrejuizo(pe.getPrejuizo() + Float.parseFloat(split_valor[3]));
                    }
                }
            }

            Collections.sort(ListaPrejuizoGerado, new jsonServlet.datasPrejuizoGerado());

            gson = new GsonBuilder().create();
            json = gson.toJson(ListaPrejuizoGerado);
        } else {

            SimpleDateFormat dia = new SimpleDateFormat("D d MMM yyyy");

            ListaRelatorio.forEach(l -> ListaDatas.add(dia.format(l.getData_hora_pedido())));
            List<String> ListaValores = new ArrayList<>();
            ListaRelatorio.forEach(l -> ListaValores.add(dia.format(l.getData_hora_pedido()) + " " + l.getValor()));
            Map<String, Long> counts = ListaDatas.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            jsonServlet.prejuizoGerado PrejuizoGerado = new jsonServlet.prejuizoGerado();
            List<jsonServlet.prejuizoGerado> ListaPrejuizoGerado = new ArrayList<>();

            for (Map.Entry<String, Long> count : counts.entrySet()) {

                String[] split_data = count.getKey().split(" ");

                PrejuizoGerado.setMes_dia(Integer.parseInt(split_data[0]));
                PrejuizoGerado.setData(split_data[1] + " " + split_data[2] + " " + split_data[3]);
                PrejuizoGerado.setAno(Integer.parseInt(split_data[3]));

                ListaPrejuizoGerado.add(PrejuizoGerado);
                PrejuizoGerado = new jsonServlet.prejuizoGerado();
            }

            for (String valor : ListaValores) {

                String[] split_valor = valor.split(" ");
                String data = split_valor[1] + " " + split_valor[2] + " " + split_valor[3];

                for (jsonServlet.prejuizoGerado pe : ListaPrejuizoGerado) {

                    if (pe.getData().equals(data)) {
                        pe.setPrejuizo(pe.getPrejuizo() + Float.parseFloat(split_valor[4]));
                    }
                }
            }

            Collections.sort(ListaPrejuizoGerado, new jsonServlet.datasPrejuizoGerado());

            gson = new GsonBuilder().create();
            json = gson.toJson(ListaPrejuizoGerado);
        }

        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    public void relarotio_justificativas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosNaoEntregues(Pedido.class, datainicio, datafinal);

        List<String> ListaJustificativas = new ArrayList<>();

        ListaRelatorio.forEach(l -> ListaJustificativas.add(l.getJustificativa()));
        Map<String, Long> counts = ListaJustificativas.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        String json;
        Gson gson = new Gson();
        gson = new GsonBuilder().create();
        json = gson.toJson(counts);

        response.setContentType("application/json");
        response.getWriter().write(json);

    }

    static class pedidosEntregues {

        private String data;
        private Long quantidade;
        private int mes_dia;
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

        /**
         * @return the mes_dia
         */
        public int getMes_dia() {
            return mes_dia;
        }

        /**
         * @param mes_dia the mes_dia to set
         */
        public void setMes_dia(int mes_dia) {
            this.mes_dia = mes_dia;
        }
    }

    static class pedidosEntregador {

        private String data;
        private Long qtde_entregue;
        private Long qtde_nentregue;
        private int mes_dia;
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
         * @return the qtde_entregue
         */
        public Long getQtde_entregue() {
            return qtde_entregue;
        }

        /**
         * @param qtde_entregue the qtde_entregue to set
         */
        public void setQtde_entregue(Long qtde_entregue) {
            this.qtde_entregue = qtde_entregue;
        }

        /**
         * @return the qtde_nentregue
         */
        public Long getQtde_nentregue() {
            return qtde_nentregue;
        }

        /**
         * @param qtde_nentregue the qtde_nentregue to set
         */
        public void setQtde_nentregue(Long qtde_nentregue) {
            this.qtde_nentregue = qtde_nentregue;
        }

        /**
         * @return the mes_dia
         */
        public int getMes_dia() {
            return mes_dia;
        }

        /**
         * @param mes_dia the mes_dia to set
         */
        public void setMes_dia(int mes_dia) {
            this.mes_dia = mes_dia;
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

    static class prejuizoGerado {

        private String data;
        private int mes_dia;
        private int ano;
        private float prejuizo;

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
         * @return the mes_dia
         */
        public int getMes_dia() {
            return mes_dia;
        }

        /**
         * @param mes_dia the mes_dia to set
         */
        public void setMes_dia(int mes_dia) {
            this.mes_dia = mes_dia;
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

        /**
         * @return the prejuizo
         */
        public float getPrejuizo() {
            return prejuizo;
        }

        /**
         * @param prejuizo the prejuizo to set
         */
        public void setPrejuizo(float prejuizo) {
            this.prejuizo = prejuizo;
        }
    }

    public static class datasRelEntreguesComparador implements Comparator<pedidosEntregues> {

        @Override
        public int compare(pedidosEntregues s1, pedidosEntregues s2) {
            Integer comparar_data = s1.getAno();
            comparar_data = comparar_data.compareTo(s2.getAno());
            if (comparar_data != 0) {
                return comparar_data;
            }
            return Integer.compare(s1.getMes_dia(), s2.getMes_dia());
        }
    }

    public static class datasEntregadorComparador implements Comparator<pedidosEntregador> {

        @Override
        public int compare(pedidosEntregador s1, pedidosEntregador s2) {
            Integer comparar_data = s1.getAno();
            comparar_data = comparar_data.compareTo(s2.getAno());
            if (comparar_data != 0) {
                return comparar_data;
            }
            return Integer.compare(s1.getMes_dia(), s2.getMes_dia());
        }
    }

    public static class datasPrejuizoGerado implements Comparator<prejuizoGerado> {

        @Override
        public int compare(prejuizoGerado s1, prejuizoGerado s2) {
            Integer comparar_data = s1.getAno();
            comparar_data = comparar_data.compareTo(s2.getAno());
            if (comparar_data != 0) {
                return comparar_data;
            }
            return Integer.compare(s1.getMes_dia(), s2.getMes_dia());
        }
    }
}
