package Controle.logico;

import dao.DaoFuncionario;
import dao.DaoRelatorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Pedido;

public class ControleLogicoRelatorio implements ControleLogico {

    DaoRelatorio acessohibernaterelatorio;
    DaoFuncionario acessohibernatefuncionario;
    
    public ControleLogicoRelatorio() {

        acessohibernaterelatorio = new DaoRelatorio();
        acessohibernatefuncionario = new DaoFuncionario();
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");

        switch (acao) {
            case "listar_pedido_entregar":
                gera_relatorio(request, response);
                break;
            case "consulta_entregador":
                consulta_entregador(request,response);
                break;
            default:
                break;
        }
    }

    public void gera_relatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Date datainicio = new Date();
        Date datafinal = new Date();
        String data = "01/01/2017";
        String data1 = "01/03/2017";

        //datainicio = (Date) data;
        List<Pedido> ListaRelatorio = new ArrayList<>();
        //ListaRelatorio = (List<Pedido>) acessohibernaterelatorio.pedidosEntregues(Pedido.class, data, data1);
    }

    public void consulta_entregador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Funcionario> ListaEntregadores;
        ListaEntregadores = (List<Funcionario>) acessohibernatefuncionario.consultaEntregador();
        request.setAttribute("ListaEntregadores", ListaEntregadores);
        request.getRequestDispatcher("relatorios").forward(request, response);
    }

}
