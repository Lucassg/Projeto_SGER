package Controle.logico;

import dao.DaoFuncionario;
import dao.DaoRelatorio;
import dao.DaoRota;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
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
}