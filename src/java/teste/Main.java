package teste;

import dao.DaoArea_Entrega;
import java.util.ArrayList;
import java.util.List;
import model.Area_Entrega;

public class Main {

    public static void main(String[] args){

        DaoArea_Entrega acessohibernateareaentrega;
        Area_Entrega area_entrega;
        acessohibernateareaentrega = new DaoArea_Entrega();
        area_entrega = new Area_Entrega();
               
        area_entrega = (Area_Entrega) acessohibernateareaentrega.verificaAreaEntrega("13060702");
        
        if (area_entrega == null){
            System.out.println("false");
        } else{
            System.out.println("verdadeiro");
        }
      
        List<Area_Entrega> ceps = new ArrayList<>();
        ceps = acessohibernateareaentrega.carregarTudoOrdenado(Area_Entrega.class, "cep");
        
        
//        for (int i = 0; i <= ceps.size(); i++) {
//            System.out.println("CEP: " + ceps.get(i).getCep());
//        }

    }
}
