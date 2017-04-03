package dao;

import model.Sger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.hibernate.criterion.Projections.id;
import org.hibernate.criterion.Restrictions;

public class DaoSger extends DaoGenerico {
   
    public DaoSger(){
    
    }
    
        public Object carregaSger() throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Sger.class);
        Object obj = criteria.uniqueResult();
        transaction.commit();
        session.close();
        return obj;
    }
}
