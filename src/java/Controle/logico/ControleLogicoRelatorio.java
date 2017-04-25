package Controle.logico;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DaoFuncionario;
import dao.DaoRelatorio;
import dao.DaoRota;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Pedido;
import model.Rota;

public class ControleLogicoRelatorio implements ControleLogico {

    DaoRelatorio acessohibernaterelatorio;
    DaoFuncionario acessohibernatefuncionario;
    DaoRota acessohibernaterota;
    Funcionario funcionario;

    public ControleLogicoRelatorio() {

        acessohibernaterelatorio = new DaoRelatorio();
        acessohibernatefuncionario = new DaoFuncionario();
        acessohibernaterota = new DaoRota();
        funcionario = new Funcionario();
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");

        switch (acao) {
            case "gera_relatorio":
                gera_relatorio(request, response);
                break;
            case "consulta_entregador":
                consulta_entregador(request, response);
                break;
            case "consulta_entregador_teste":
                consulta_entregador_teste(request, response);
                break;
            case "relatorio_entregador":
                relatorio_entregador(request, response);
                break;
            default:
                break;
        }
    }

    public void gera_relatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Date datainicio = new Date();
        Date datafinal = new Date();
        String data_inicial = request.getParameter("datainicial");
        String data_final = request.getParameter("datafinal");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        SimpleDateFormat mes = new SimpleDateFormat("MMMMM");

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

        ControleLogicoRelatorio.pedidosEntregues PedidosEntregues = new ControleLogicoRelatorio.pedidosEntregues();
        List<ControleLogicoRelatorio.pedidosEntregues> ListaPedidosEntregues;
        ListaPedidosEntregues = new ArrayList<>();

        for (Map.Entry<String, Long> count : counts.entrySet()) {
            PedidosEntregues.setMes(count.getKey());
            PedidosEntregues.setQuantidade(count.getValue());
            ListaPedidosEntregues.add(PedidosEntregues);
            PedidosEntregues = new ControleLogicoRelatorio.pedidosEntregues();
        }

        Gson gson = new Gson();

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Lucas\\Google Drive\\NetBeansProjects\\Projeto_SGER2203\\web\\JSON\\counts.json", false), "UTF-8"))) {
            gson = new GsonBuilder().create();
            gson.toJson(ListaPedidosEntregues, out);
        }
    }

    public void consulta_entregador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Funcionario> ListaEntregadores;
        ListaEntregadores = (List<Funcionario>) acessohibernatefuncionario.consultaEntregadores(Funcionario.class);
        request.setAttribute("ListaEntregadores", ListaEntregadores);
        request.getRequestDispatcher("relatorios").forward(request, response);
    }
    
    public void consulta_entregador_teste(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Funcionario> ListaEntregadores;
        ListaEntregadores = (List<Funcionario>) acessohibernatefuncionario.consultaEntregadores(Funcionario.class);
        request.setAttribute("ListaEntregadores", ListaEntregadores);
        request.getRequestDispatcher("teste").forward(request, response);
    }

    public void relatorio_entregador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("entregador"));
        this.funcionario = (Funcionario) acessohibernatefuncionario.carregarUm(id, Funcionario.class);
        List<Rota> ListaRotas = acessohibernaterota.consultaRotaEntregador(Rota.class, funcionario);
    }

    static class pedidosEntregues {

        private String mes;
        private Long quantidade;

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
    }
}
