package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity

public class Itens_Pedido implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido_id;
    @Id
    @ManyToOne
    @JoinColumn(name="produto_id")
    public Produto produto_id;
    private int quantidade;
    private String observacao;

    public Itens_Pedido(){
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the pedido_id
     */
    public Pedido getPedido_id() {
        return pedido_id;
    }

    /**
     * @param pedido_id the pedido_id to set
     */
    public void setPedido_id(Pedido pedido_id) {
        this.pedido_id = pedido_id;
    }

    /**
     * @return the produto_id
     */
    public Produto getProduto_id() {
        return produto_id;
    }

    /**
     * @param produto_id the produto_id to set
     */
    public void setProduto_id(Produto produto_id) {
        this.produto_id = produto_id;
    }
}

  