package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rota implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name="funcionario_id")
    private Funcionario funcionario;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_hora_rota;
    
    
    public Rota(){
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
     * @return the data_hora_rota
     */
    public Date getData_hora_rota() {
        return data_hora_rota;
    }

    /**
     * @param data_hora_rota the data_hora_rota to set
     */
    public void setData_hora_rota(Date data_hora_rota) {
        this.data_hora_rota = data_hora_rota;
    }

}