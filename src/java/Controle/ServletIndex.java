package Controle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DaoPedido;
import dao.DaoRelatorio;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import model.Pedido;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

        Date datainicio = new Date();
        Date datafinal = new Date();

        LocalDate today = new LocalDate();
        LocalDate di = today.withDayOfMonth(1);
        LocalDate df = today.plusMonths(1).withDayOfMonth(1);

        DateTimeFormatter formato = DateTimeFormat.forPattern("yyyy-MM-dd 00:00:00");

        DateTime primerDiaDelMes = new DateTime().dayOfMonth().withMinimumValue();
        String desde = new LocalDate(di).toString(formato);
        
        DateTime ultimoDiaDelMes = new DateTime().dayOfMonth().withMaximumValue();
        String hasta = new LocalDate(df).toString(formato);
        
        SimpleDateFormat fmt = new SimpleDateFormat("yyy-MM-dd 00:00:00");

        try {
            fmt.setLenient(false);
            datainicio = fmt.parse(desde);
            datafinal = fmt.parse(hasta);
        } catch (ParseException ex) {
            Logger.getLogger(ServletIndex.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (relatorio.equals("pedidos_abertos")) {
            List<Pedido> ListaPedidosAbertos;
            ListaPedidosAbertos = (List<Pedido>) acessohibernaterelatorio.PedidosAbertos(Pedido.class, datainicio, datafinal);

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

            List<Pedido> ListaPedidosFechados = (List<Pedido>) acessohibernatepedido.carregarPedidosFechados(datainicio, datafinal);

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
