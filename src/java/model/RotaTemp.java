package model;

/**
 *
 * @author Lucas Garcia
 */
public class RotaTemp {

    private int id;
    private Pedido pedido1;
    private Pedido pedido2;
    private long distancia;

    public RotaTemp() {

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
     * @return the pedido1
     */
    public Pedido getPedido1() {
        return pedido1;
    }

    /**
     * @param pedido1 the pedido1 to set
     */
    public void setPedido1(Pedido pedido1) {
        this.pedido1 = pedido1;
    }

    /**
     * @return the pedido2
     */
    public Pedido getPedido2() {
        return pedido2;
    }

    /**
     * @param pedido2 the pedido2 to set
     */
    public void setPedido2(Pedido pedido2) {
        this.pedido2 = pedido2;
    }

    /**
     * @return the distancia
     */
    public long getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(long distancia) {
        this.distancia = distancia;
    }
}
