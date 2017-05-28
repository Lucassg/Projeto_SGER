package Controle.logico;

import dao.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;

public class ControleLogicoPedido implements ControleLogico {

    DaoCliente acessohibernatecliente;
    DaoPedido acessohibernatepedido;
    DaoProduto acessohibernateproduto;
    DaoItens_Pedido acessohibernateitens_pedido;
    private Cliente cliente;
    private Produto produto;
    private Funcionario funcionario;
    private Itens_Pedido item_pedido;
    private Pedido pedido;

    public ControleLogicoPedido() {

        acessohibernatecliente      = new DaoCliente();
        acessohibernatepedido       = new DaoPedido();
        acessohibernateproduto      = new DaoProduto();
        acessohibernateitens_pedido = new DaoItens_Pedido();
        item_pedido                 = new Itens_Pedido();
        pedido                      = new Pedido();
        cliente                     = new Cliente();
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");

        switch (acao) {
            case "buscar_cliente":
                buscar_cliente(request, response);
                break;
            case "listar_produto":
                listar_produto(request, response);
                break;
            case "verifcar_cadastrar":
                verifcar_cadastrar(request, response);
                break;
            case "inserir_item":
                inserir_item(request, response);
                break;
            case "gravar_pedido":
                gravar_pedido(request, response);
                break;
            case "excluir_item":
                excluir_item(request, response);
                break;
            case "cancelar_cad_pedido":
                cancelar_cad_pedido(request, response);
                break;
            case "listar_pedido":
                listar_pedido(request, response);
                break;
            case "visualizar_pedido":
                visualizar_pedido(request, response);
                break;
            case "listar_pedido_status":
                listar_pedido_status(request, response);
                break;
            case "pesquisar_pedido":
                pesquisar_pedido(request, response);
                break;
            case "cancelar_pedido":
                cancelar_pedido(request, response);
                break;      
            default:
                break;
        }
    }

    public void buscar_cliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String busca;
        busca = request.getParameter("pesquisa");

        this.cliente = (Cliente) acessohibernatecliente.verificaUmCliente(busca, Cliente.class);

        request.setAttribute("cliente", this.cliente);
        request.getRequestDispatcher("pedidos_cadastro").forward(request, response);
    }

    public void listar_produto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Produto> ListaProdutos;
        ListaProdutos = (List<Produto>) acessohibernateproduto.carregarTudoOrdenado(Produto.class, "nome");

        request.setAttribute("ListaProdutos", ListaProdutos);
        request.getRequestDispatcher("pedidos_cadastro2").forward(request, response);
    }

    public void verifcar_cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        this.cliente.setAtivo("sim");

        cliente = (Cliente) acessohibernatecliente.salvaOuAltera(this.cliente);
        request.getSession().setAttribute("cliente", this.cliente);
        listar_produto(request, response);
    }

    public void inserir_item(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Itens_Pedido> ListaItensPedido;

        this.produto = (Produto) acessohibernateproduto.carregarUm(Integer.parseInt(request.getParameter("produto")), Produto.class);
        this.item_pedido.setProduto_id(this.produto);
        this.item_pedido.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        this.item_pedido.setObservacao(request.getParameter("observacao"));

        if (request.getSession().getAttribute("ListaItensPedido") != null) {
            ListaItensPedido = (List<Itens_Pedido>) request.getSession().getAttribute("ListaItensPedido");
            for (Itens_Pedido item_pedido : ListaItensPedido) {

                if (item_pedido.produto_id.getCodigo() == this.item_pedido.produto_id.getCodigo()) {

                    listar_produto(request, response);
                    return;
                }
            }
        } else {
            ListaItensPedido = new ArrayList<Itens_Pedido>();
        }

        ListaItensPedido.add(this.item_pedido);
        request.getSession().setAttribute("ListaItensPedido", ListaItensPedido);
        request.setAttribute("ListaItensPedido", ListaItensPedido);
        listar_produto(request, response);
    }

    public void excluir_item(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int index;
        index = (Integer.parseInt(request.getParameter("index")) - 1);

        List<Itens_Pedido> ListaItensPedido;
        ListaItensPedido = (List<Itens_Pedido>) request.getSession().getAttribute("ListaItensPedido");

        ListaItensPedido.remove(index);

        request.getSession().setAttribute("ListaItensPedido", ListaItensPedido);
        request.setAttribute("ListaItensPedido", ListaItensPedido);
        listar_produto(request, response);

    }

    public void gravar_pedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BigDecimal valorPedido;
        Float soma = 0F;
        Float peso = 0F;
        List<Itens_Pedido> ListaItensPedido;
        this.cliente = (Cliente) request.getSession().getAttribute("cliente");
        this.funcionario = (Funcionario) request.getSession().getAttribute("funcionario");
        ListaItensPedido = (List<Itens_Pedido>) request.getSession().getAttribute("ListaItensPedido");

        for (Itens_Pedido item_pedido : ListaItensPedido) {
            peso = peso + (item_pedido.getQuantidade() * item_pedido.produto_id.getPeso().floatValue());
            soma = soma + (item_pedido.getQuantidade() * item_pedido.produto_id.getValor().floatValue());

        }

        valorPedido = BigDecimal.valueOf(soma);
        this.pedido.setValor(valorPedido);
        this.pedido.setStatus("Aguardando Entrega");
        this.pedido.setData_hora_pedido((java.util.Date) new Date());
        this.pedido.setCliente(this.cliente);
        this.pedido.setFuncionario(this.funcionario);
        this.pedido.setPeso(peso);

        this.pedido = (Pedido) acessohibernatepedido.gravarPedido(this.pedido);

        for (Itens_Pedido item_pedido : ListaItensPedido) {

            item_pedido.setPedido_id(this.pedido);
            acessohibernateitens_pedido.gravar(item_pedido);

        }
        request.getSession().setAttribute("cliente", null);
        request.getSession().setAttribute("ListaItensPedido", null);
        listar_pedido(request, response);
    }

    public void cancelar_cad_pedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("cliente", null);
        request.getSession().setAttribute("ListaItensPedido", null);
        listar_pedido(request, response);
    }
    
    public void listar_pedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
        List<Pedido> ListaPedidos;
        ListaPedidos = (List<Pedido>) acessohibernatepedido.carregarUltimos(Pedido.class, "data_hora_pedido");
        request.setAttribute("ListaPedidos", ListaPedidos);
        request.getRequestDispatcher("pedidos").forward(request, response);
        
    }
    
    public void visualizar_pedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
        List<Itens_Pedido> ListaItensPedido;
        this.pedido = (Pedido) acessohibernatepedido.carregarUm(Integer.parseInt(request.getParameter("pedido")), Pedido.class);
        ListaItensPedido = (List<Itens_Pedido>) acessohibernatepedido.carregaItensPedido(this.pedido, Itens_Pedido.class);
        request.setAttribute("Pedido",  this.pedido);
        request.setAttribute("ListaItensPedido", ListaItensPedido);
        request.getRequestDispatcher("pedidos_visualizar").forward(request, response);
        
    }

    public void listar_pedido_status(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        List<Pedido> ListaPedidos;
        ListaPedidos = (List<Pedido>) acessohibernatepedido.carregarUltimosStatus(Pedido.class, request.getParameter("statusEscolhido"));
        request.setAttribute("ListaPedidos", ListaPedidos);
        request.getRequestDispatcher("pedidos").forward(request, response);
         
    }
     
    public void pesquisar_pedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        List<Pedido> ListaPedidos;
        ListaPedidos = new ArrayList<Pedido>();
        
        if(!"".equals(request.getParameter("pedido"))){
            
            this.pedido = (Pedido) acessohibernatepedido.carregarUm(Integer.parseInt(request.getParameter("pedido")), Pedido.class);
            ListaPedidos.add(this.pedido);
            request.setAttribute("ListaPedidos", ListaPedidos);
            request.getRequestDispatcher("pedidos_pesquisar").forward(request, response);
            
        } else {
        
            this.cliente =  (Cliente) acessohibernatecliente.verificaUmCliente(request.getParameter("cliente"), Cliente.class);
            ListaPedidos = (List<Pedido>) acessohibernatepedido.pesquisarPedido(this.cliente, request.getParameter("status"), Pedido.class);
            request.setAttribute("ListaPedidos", ListaPedidos);
            request.getRequestDispatcher("pedidos_pesquisar").forward(request, response);
        }
    }
    
    public void cancelar_pedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        this.pedido = (Pedido) acessohibernatepedido.carregarUm(Integer.parseInt(request.getParameter("pedido")), Pedido.class);
        this.pedido.setJustificativa(request.getParameter("motivo"));
        this.pedido.setStatus("Cancelado");
        this.pedido.setData_hora_pedido((java.util.Date) new Date());
        if (this.pedido.getRota() != null){
            this.pedido.setRota(null);
        }
        acessohibernatepedido.alterar(this.pedido);
        listar_pedido(request, response);
      
    }
}