package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    private BigDecimal valor;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_hora_pedido;
    @ManyToOne
    @JoinColumn(name="Rota_id")
    private Rota rota;
    @ManyToOne
    @JoinColumn(name="Cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="funcionario_id")
    private Funcionario funcionario;
    private Float peso;

public Pedido(){

}

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the data_hora_pedido
     */
    public Date getData_hora_pedido() {
        return data_hora_pedido;
    }

    /**
     * @param data_hora_pedido the data_hora_pedido to set
     */
    public void setData_hora_pedido(Date data_hora_pedido) {
        this.data_hora_pedido = data_hora_pedido;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the rota
     */
    public Rota getRota() {
        return rota;
    }

    /**
     * @param rota the rota to set
     */
    public void setRota(Rota rota) {
        this.rota = rota;
    }
    
    public String pedidoToSting(){
        return "ID Pedido: " + this.id;
    }

    /**
     * @return the peso
     */
    public Float getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Float peso) {
        this.peso = peso;
    }
}
