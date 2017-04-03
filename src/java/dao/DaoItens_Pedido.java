package dao;

import java.util.List;
import model.Pedido;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DaoItens_Pedido extends DaoGenerico {

    public DaoItens_Pedido() {
    }

    public List pesquisarItensPedido(Pedido pedido, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("pedido_id.id", pedido.getId()));
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List pesquisarTodosItensPedido(Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        List lista = criteria.list();
        session.close();
        return lista;
    }

}
