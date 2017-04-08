package dao;

import java.util.Date;
import java.util.List;
import model.Funcionario;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DaoRelatorio extends DaoGenerico {

    public DaoRelatorio() {

    }

    public List pedidosEntregues(Class clas, Date datainicio, Date datafinal) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        //criteria.add(Restrictions.eq("status", "Entrega em Andamento")); 
        //criteria.add(Restrictions.and(Restrictions.between("data_hora_pedido", datainicio, datafinal)));
        criteria.add(Restrictions.between("data_hora_pedido", datainicio, datafinal));
        List lista = criteria.list();
        session.close();
        return lista;
    }    

    public List pedidosEntregador(Integer id) throws HibernateException {
        
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(Funcionario.class);
        criteria.add(Restrictions.eq("id", id)); 
        List lista = criteria.list();
        session.close();
        return lista;
    }    
}
