package dao;

import java.util.List;
import model.Cliente;
import model.Pedido;
import model.Rota;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class DaoPedido extends DaoGenerico {

    public DaoPedido() {

    }

    public Object gravarPedido(Object obj) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        session.close();
        return obj;
    }

    public List carregarUltimosStatus(Class clas, String status) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        if (!"Todos".equals(status)) {
            criteria.add(Restrictions.eq("status", status));
        }
        criteria.addOrder(Order.desc("data_hora_pedido"));
        criteria.setMaxResults(10);
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List carregaItensPedido(Pedido pedido, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas); //itens_pedido
        criteria.add(Restrictions.eq("pedido_id", pedido)); 
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List pesquisarPedido(Cliente cliente, String status, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        if (!"todos".equals(status)) {

            criteria.add(Restrictions.and(Restrictions.eq("cliente.id", cliente.getId()), Restrictions.eq("status", status)));

        } else {

            criteria.add(Restrictions.eq("cliente.id", cliente.getId()));

        }

        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List carregarPedidosAguardandoEntrega(Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("status", "Aguardando Entrega"));
        criteria.setMaxResults(23);
        List lista = criteria.list();
        session.close();
        return lista;
    }
    public List carregarPedidosFechados() throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(Pedido.class);
        criteria.add(Restrictions.or(Restrictions.eq("status", "Entregue"), 
                Restrictions.eq("status", "Não Entregue"),
                Restrictions.eq("status", "Cancelado")));
        List lista = criteria.list();
        session.close();
        return lista;
    }
    
    public List carregarPedidosRotas(Rota rota, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("rota", rota));
        List lista = criteria.list();
        session.close();
        return lista;
    }
}
