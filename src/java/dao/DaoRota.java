package dao;

import java.util.List;
import model.Funcionario;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DaoRota extends DaoGenerico {

    public DaoRota() {
    }

    public Object gravarRota(Object obj) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        session.close();
        return obj;
    }

    public List carregarRotasGeradas(Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("status", "Rota Gerada"));
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List consultaRotaEntregador(Class clas, Funcionario funcionario) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("funcionario", funcionario));
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List carregarRotasStatus(String status, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("status", status));
        List lista = criteria.list();
        session.close();
        return lista;
    }
}
