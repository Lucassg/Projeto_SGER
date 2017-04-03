package dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DaoCliente extends DaoGenerico {

    public DaoCliente() {
    }

    public List verificaCliente(String valor, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.or(Restrictions.ilike("celular", "%" + valor + "%"), Restrictions.ilike("telefone", "%" + valor + "%"), Restrictions.eq("cpf", valor), Restrictions.ilike("nome", "%" + valor + "%")));
        List lista = criteria.list();
        transaction.commit();
        session.close();
        return lista;
    }

    public Object verificaUmCliente(String valor, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.or(Restrictions.eq("celular", valor), Restrictions.like("telefone", valor), Restrictions.eq("cpf", valor)));
        Object obj = criteria.uniqueResult();
        transaction.commit();
        session.close();
        return obj;
    }
    
    public Object salvaOuAltera(Object obj) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
        session.close();
        return obj;
    }
}
