package dao;

import java.util.List;
import model.Funcionario;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DaoFuncionario extends DaoGenerico {

    public DaoFuncionario() {

    }

    public Object verificaUsuario(String valor, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("usuario", valor));
        Object obj = criteria.uniqueResult();
        transaction.commit();
        session.close();
        return obj;
    }

    public List verificaFuncionario(String valor, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.or(Restrictions.ilike("celular", "%" + valor + "%"), Restrictions.ilike("telefone", "%" + valor + "%"), Restrictions.eq("cpf", valor), Restrictions.ilike("nome", "%" + valor + "%"), Restrictions.ilike("usuario", "%" + valor + "%")));
        List lista = criteria.list();
        transaction.commit();
        session.close();
        return lista;
    }

    public List consultaEntregadores(Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.and(Restrictions.eq("funcao", "Entregador"), Restrictions.eq("ativo", "sim")));
        List lista = criteria.list();
        session.close();
        return lista;
    }

    public Object consultaEntregador(String cpf, Class clas) throws HibernateException {
        Session session = hibernateConfiguracao.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("cpf", cpf));
        Object obj = criteria.uniqueResult();
        transaction.commit();
        session.close();
        return obj;
    }
}
