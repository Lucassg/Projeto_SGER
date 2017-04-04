package teste;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import dao.DaoFuncionario;
import dao.DaoRelatorio;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Funcionario;
import model.Pedido;

public class Main {

    static class pedidosEntregues{
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

    public static void main(String[] args) throws Exception {

        DaoRelatorio acessohibernaterelatorio = new DaoRelatorio();
        DaoFuncionario acessohibernatefuncionario = new DaoFuncionario();
        Date datainicio = new Date();
        Date datafinal = new Date();
        String data = "2017-01-01 00:00:00";
        String data1 = "2017-03-01 00:00:00";

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat mes = new SimpleDateFormat("MMMMM");
        datainicio = fmt.parse(data);
        datafinal = fmt.parse(data1);

        //datainicio = (Date) data;
        List<Pedido> ListaRelatorio = new ArrayList<>();
        List<Funcionario> ListaEntregador = new ArrayList<>();
        ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosEntregues(Pedido.class, datainicio, datafinal);
        ListaEntregador = (List<Funcionario>) acessohibernatefuncionario.consultaEntregador();

//        ListaRelatorio.forEach(a -> System.out.println("Id: " + a.getId() + ", Data pedido: " + fmt.format(a.getData_hora_pedido())));
//        ListaRelatorio.forEach(a -> System.out.println("Id: " + a.getId() + ", Mes pedido: " + mes.format(a.getData_hora_pedido())));
        ListaEntregador.forEach(a -> System.out.println("Id: " + a.getId() + ", Nome: " + a.getNome()));

        List<String> ListaMeses = new ArrayList<>();

        ListaRelatorio.forEach(l -> ListaMeses.add(mes.format(l.getData_hora_pedido())));

        Map<String, Long> counts = ListaMeses.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

//        System.out.println("counts" + counts);

        Main.pedidosEntregues PedidosEntregues = new Main.pedidosEntregues();
        List<Main.pedidosEntregues> ListaPedidosEntregues;
        ListaPedidosEntregues = new ArrayList<>();
        
        for(Map.Entry<String, Long> count : counts.entrySet()){
            PedidosEntregues.setMes(count.getKey());
            PedidosEntregues.setQuantidade(count.getValue());
            ListaPedidosEntregues.add(PedidosEntregues);
            PedidosEntregues = new pedidosEntregues();
        }

        Gson gson = new Gson();
        JsonElement json = gson.fromJson(new FileReader("./web/Relatorio/relatorio.json"), JsonElement.class);
        String result = gson.toJson(json);

//        System.out.println(result);

        try (Writer writer = new FileWriter("./web/Relatorio/counts.json")) {
            gson = new GsonBuilder().create();
            gson.toJson(ListaPedidosEntregues, writer);
            System.out.println("Arquivo JSON criado com sucesso.");
        }
        System.exit(0);
    }
}
