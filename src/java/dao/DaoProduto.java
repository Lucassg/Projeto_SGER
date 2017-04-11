package dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DaoProduto extends DaoGenerico {

    public DaoProduto() {

    }

    public List verificaProduto(String valor, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.or(Restrictions.like("nome", "%" + valor + "%"), Restrictions.like("descricao", "%" + valor + "%")));
        List lista = criteria.list();
        transaction.commit();
        session.close();
        return lista;
    }
}
