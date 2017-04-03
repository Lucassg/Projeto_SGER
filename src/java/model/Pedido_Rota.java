package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Pedido_Rota implements Serializable{
    
    @Id
    @ManyToOne
    @JoinColumn(name="rota_id")
    private Rota rota;
    @Id
    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;
    
    public Pedido_Rota(){
    
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

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    } 
}
