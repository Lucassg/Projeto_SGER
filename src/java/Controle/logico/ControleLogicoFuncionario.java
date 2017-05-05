package Controle.logico;

import dao.DaoFuncionario;
import model.Funcionario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControleLogicoFuncionario implements ControleLogico {

    DaoFuncionario acessohibernatefuncionario;
    private Funcionario funcionario;

    public ControleLogicoFuncionario() {

        acessohibernatefuncionario = new DaoFuncionario();
        funcionario = new Funcionario();
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");

        switch (acao) {
            case "listar_funcionario":
                listar_funcionario(request, response);
                break;
            case "cadastrar_funcionario":
                cadastrar_funcionario(request, response);
                break;
            case "autenticar_usuario":
                autenticar_usuario(request, response);
                break;
            case "carregar_alterar_funcionario":
                carregar_alterar_funcionario(request, response);
                break;
            case "alterar_funcionario":
                alterar_funcionario(request, response);
                break;
            case "buscar_funcionario":
                buscar_funcionario(request, response);
                break;
            case "sair":
                sair(request, response);
                break;
            default:
                break;
        }
    }

    public void cadastrar_funcionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.funcionario.setNome(request.getParameter("nome"));
        this.funcionario.setCpf(request.getParameter("cpf"));
        this.funcionario.setCelular(request.getParameter("celularddd") + request.getParameter("celular"));
        this.funcionario.setTelefone(request.getParameter("telefoneddd") + request.getParameter("telefone"));
        this.funcionario.setAtivo("sim");
        this.funcionario.setFuncao(request.getParameter("funcao"));
        this.funcionario.setUsuario(request.getParameter("usuario"));
        this.funcionario.setSenha(request.getParameter("usuario"));

        acessohibernatefuncionario.gravar(this.funcionario);

        listar_funcionario(request, response);
    }

    public void listar_funcionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Funcionario> ListaFuncionarios;
        ListaFuncionarios = (List<Funcionario>) acessohibernatefuncionario.carregarUltimos(Funcionario.class, "id");

        request.setAttribute("ListaFuncionarios", ListaFuncionarios);
        request.getRequestDispatcher("funcionarios").forward(request, response);

    }

    public void autenticar_usuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Funcionario funcionario;
        String user;
        String senha;

        funcionario = new Funcionario();
        user = request.getParameter("login");
        senha = request.getParameter("senha");

        funcionario = (Funcionario) acessohibernatefuncionario.verificaUsuario(user, Funcionario.class);

        if (funcionario != null) {
            if (user.equals(funcionario.getUsuario()) && senha.equals(funcionario.getSenha())) {

                request.getSession().setAttribute("funcionario", funcionario);
                request.getRequestDispatcher("index").forward(request, response);
            } else {

                request.setAttribute("Falha", "Erro de Autenticação");
                request.getRequestDispatcher("login").forward(request, response);

            }
        } else {
            request.setAttribute("Falha", "Erro de Autenticação");
            request.getRequestDispatcher("login").forward(request, response);
        }

    }

    public void buscar_funcionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String busca;
        List<Funcionario> ListaFuncionarios;
        busca = request.getParameter("pesquisa");

        ListaFuncionarios = (List<Funcionario>) acessohibernatefuncionario.verificaFuncionario(busca, Funcionario.class);

        request.setAttribute("ListaFuncionarios", ListaFuncionarios);
        request.getRequestDispatcher("funcionarios").forward(request, response);

    }

    public void carregar_alterar_funcionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.funcionario = (Funcionario) acessohibernatefuncionario.carregarUm(Integer.parseInt(request.getParameter("id")), Funcionario.class);

        request.setAttribute("Funcionario", this.funcionario);
        request.getRequestDispatcher("funcionarios_alterar").forward(request, response);

    }

    public void alterar_funcionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.funcionario.setId(Integer.parseInt(request.getParameter("id")));
        this.funcionario.setNome(request.getParameter("nome"));
        this.funcionario.setCpf(request.getParameter("cpf"));
        this.funcionario.setCelular(request.getParameter("celular"));
        this.funcionario.setTelefone(request.getParameter("telefone"));
        this.funcionario.setAtivo(request.getParameter("ativo"));
        this.funcionario.setFuncao(request.getParameter("funcao"));
        this.funcionario.setUsuario(request.getParameter("usuario"));
        this.funcionario.setSenha(request.getParameter("senha"));

        acessohibernatefuncionario.alterar(this.funcionario);

        listar_funcionario(request, response);

    }

    public void sair(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("funcionario", null);
        request.getRequestDispatcher("login").forward(request, response);

    }
}
