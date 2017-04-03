package Controle.logico;

import model.Produto;
import dao.DaoProduto;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControleLogicoProduto implements ControleLogico {

    DaoProduto acessohibernateproduto;
    private Produto produto;

    public ControleLogicoProduto() {

       acessohibernateproduto = new DaoProduto();

    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");

        switch (acao) {
            case "cadastrar_produto":
                cadastrar_produto(request, response);
                break;
            case "listar_produto":
                listar_produto(request, response);
                break;
            case "carregar_alterar_produto":
                carregar_alterar_produto(request, response);
                break;
            case "alterar_produto":
                alterar_produto(request, response);
                break;
            case "buscar_produto":
                buscar_produto(request, response);
                break;
            default:
                break;
        }    
    }

    public void cadastrar_produto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Produto produto = new Produto();

        produto.setNome(request.getParameter("nomeprod"));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setValor(new BigDecimal(request.getParameter("valor")));
        produto.setAtivo("sim");
        produto.setPeso(Float.parseFloat(request.getParameter("peso")));
        setProduto(produto);
        
        acessohibernateproduto.gravar(getProduto());
        
        request.getSession().setAttribute("produto", produto);

        listar_produto(request, response);
    }
    
    public void buscar_produto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String busca;
        List<Produto> ListaProdutos;
        busca = request.getParameter("pesquisa");
        
        ListaProdutos = (List<Produto>) acessohibernateproduto.verificaProduto(busca, Produto.class);
        
        request.setAttribute("ListaProdutos", ListaProdutos);
        request.getRequestDispatcher("produtos").forward(request, response);
    }

    public void listar_produto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Produto> ListaProdutos;
        ListaProdutos = (List<Produto>) acessohibernateproduto.carregarUltimos(Produto.class, "id");

        request.setAttribute("ListaProdutos", ListaProdutos);
        request.getRequestDispatcher("produtos").forward(request, response);

    }
    
    public void carregar_alterar_produto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        int codigo;
        codigo = Integer.parseInt(request.getParameter("id"));
        
        produto = (Produto) acessohibernateproduto.carregarUm(codigo, Produto.class);
        
        request.setAttribute("produto", produto);
        request.getRequestDispatcher("produtos_alterar").forward(request, response);
    
    }
    
    public void alterar_produto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        Produto produto = new Produto();

        produto.setCodigo(Integer.parseInt(request.getParameter("id")));
        produto.setNome(request.getParameter("nomeprod"));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setValor(new BigDecimal(request.getParameter("valor")));
        produto.setAtivo(request.getParameter("ativo"));
        produto.setPeso(Float.parseFloat(request.getParameter("peso")));
        setProduto(produto);
        
        acessohibernateproduto.alterar(getProduto());
        
        request.getSession().setAttribute("produto", produto);
        listar_produto(request, response);
        
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
