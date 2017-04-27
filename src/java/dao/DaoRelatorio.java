package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Funcionario;
import model.Rota;
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
        criteria.add(Restrictions.eq("status", "Entregue"));
        criteria.add(Restrictions.between("data_hora_pedido", datainicio, datafinal));
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List pedidosNaoEntregues(Class clas, Date datainicio, Date datafinal) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("status", "Não Entregue"));
        criteria.add(Restrictions.between("data_hora_pedido", datainicio, datafinal));
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List consultaEntregador(Class clas, int id) throws HibernateException {

        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("id", id));
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List consultaRotaEntregador(Class clas, Funcionario funcionario, Date datainicio, Date datafinal) throws HibernateException {

        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("funcionario", funcionario));
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List pedidosEntregador(Class clas, Date datainicio, Date datafinal, List<Rota> rotas /*Rota rota*/) throws HibernateException {

        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.and(Restrictions.in("rota", rotas), Restrictions.between("data_hora_pedido", datainicio, datafinal)));
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List PedidosAbertos(Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.or(Restrictions.eq("status", "Aguardando Entrega"), Restrictions.eq("status", "Entrega em Andamento")));
//        criteria.setMaxResults(5);
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public List PedidosFechados(Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.or(Restrictions.eq("status", "Entregue"), Restrictions.eq("status", "Nao Entregue"), Restrictions.eq("status", "Cancelado")));
//        criteria.setMaxResults(5);
        List lista = criteria.list();
        session.close();
        return lista;
    }
}
