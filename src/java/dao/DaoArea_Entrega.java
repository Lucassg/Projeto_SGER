package dao;

import model.Area_Entrega;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DaoArea_Entrega extends DaoGenerico {
    
    public DaoArea_Entrega(){
    
    }
    
    public Object verificaAreaEntrega(String cep) throws HibernateException {
        Boolean teste;
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(Area_Entrega.class);
        criteria.add(Restrictions.eq("cep", cep));
        Object obj = criteria.uniqueResult();
        session.close();
        return obj;
    }
}
