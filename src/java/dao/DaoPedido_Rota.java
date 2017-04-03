package dao;

import java.util.List;
import model.Rota;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DaoPedido_Rota extends DaoGenerico {
    
    public DaoPedido_Rota(){
   
    }   
    
    public List carregaPedidosRota(Rota rota, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("rota", rota));
        List lista = criteria.list();
        session.close();
        return lista;
    }
}
