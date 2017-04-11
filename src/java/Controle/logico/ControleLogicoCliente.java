package Controle.logico;

import model.Cliente;
import dao.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControleLogicoCliente implements ControleLogico {

    DaoCliente acessohibernatecliente;
    private Cliente cliente;
    
    public ControleLogicoCliente(){
        
       acessohibernatecliente = new DaoCliente();
       cliente                = new Cliente();
       
       
        //teste do git 2
        //conmentario 222
    }
    
    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");
        
        switch (acao) {
            case "listar_cliente":
                listar_cliente(request, response);
                break;
            case "cadastrar_cliente":
                cadastrar_cliente(request, response);
                break;
            case "buscar_cliente":
                buscar_cliente(request, response);
                break;
            case "carregar_alterar_cliente":
                carregar_alterar_cliente(request, response);
                break;
            case "alterar_cliente":
                alterar_cliente(request, response);
                break;
            default:     
                break;
        }
    }

    public void cadastrar_cliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.cliente.setNome(request.getParameter("nome"));
        this.cliente.setCpf(request.getParameter("cpf"));
        this.cliente.setCelular(request.getParameter("celularddd") + request.getParameter("celular"));
        this.cliente.setTelefone(request.getParameter("telefoneddd") + request.getParameter("telefone"));
        this.cliente.setEmail(request.getParameter("email"));
        this.cliente.setRua(request.getParameter("rua"));
        this.cliente.setNumero(request.getParameter("numero"));
        this.cliente.setBairro(request.getParameter("bairro"));
        this.cliente.setPonto_ref(request.getParameter("pontoref"));
        this.cliente.setCidade(request.getParameter("cidade"));
        this.cliente.setUf(request.getParameter("uf"));
        this.cliente.setCep(request.getParameter("cep") + request.getParameter("cep2"));
        this.cliente.setData_atualizacao((java.util.Date) new Date());
        this.cliente.setAtivo("sim");
        
        acessohibernatecliente.gravar(this.cliente);
        
        request.getSession().setAttribute("cliente", this.cliente);
        listar_cliente(request, response);

    }
    
    public void listar_cliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        List<Cliente> ListaClientes;
        ListaClientes = (List<Cliente>) acessohibernatecliente.carregarUltimos(Cliente.class, "id");
        
        request.setAttribute("ListaClientes", ListaClientes);
        request.getRequestDispatcher("clientes").forward(request, response);

    }
    
    public void buscar_cliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        List<Cliente> ListaClientes;
        ListaClientes = (List<Cliente>) acessohibernatecliente.verificaCliente(request.getParameter("pesquisa"), Cliente.class);
        
        request.setAttribute("ListaClientes", ListaClientes);
        request.getRequestDispatcher("clientes").forward(request, response);
    
    }
    
    public void carregar_alterar_cliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.cliente = (Cliente) acessohibernatecliente.carregarUm(Integer.parseInt(request.getParameter("id")), Cliente.class);
        
        request.setAttribute("cliente", this.cliente);
        request.getRequestDispatcher("clientes_alterar").forward(request, response);
            
    }
    
    public void alterar_cliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.cliente.setId(Integer.parseInt(request.getParameter("id")));
        this.cliente.setNome(request.getParameter("nome"));
        this.cliente.setCpf(request.getParameter("cpf"));
        this.cliente.setCelular(request.getParameter("celular"));
        this.cliente.setTelefone(request.getParameter("telefone"));
        this.cliente.setEmail(request.getParameter("email"));
        this.cliente.setRua(request.getParameter("rua"));
        this.cliente.setNumero(request.getParameter("numero"));
        this.cliente.setBairro(request.getParameter("bairro"));
        this.cliente.setPonto_ref(request.getParameter("pontoref"));
        this.cliente.setCidade(request.getParameter("cidade"));
        this.cliente.setUf(request.getParameter("uf"));
        this.cliente.setCep(request.getParameter("cep"));
        this.cliente.setData_atualizacao((java.util.Date) new Date());
        this.cliente.setAtivo(request.getParameter("ativo"));
    
        acessohibernatecliente.alterar(this.cliente);
        
        request.getSession().setAttribute("cliente", this.cliente);
        listar_cliente(request, response);
            
    }
}
