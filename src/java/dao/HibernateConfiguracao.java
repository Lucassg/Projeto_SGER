package dao;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfiguracao {

    private static SessionFactory sessionFactory;

    public HibernateConfiguracao() {
    }

    public Session openSession() {
        if (sessionFactory == null) {
            criaSessionFactory();
        }
            return sessionFactory.openSession();
    }

    public void criaSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Cliente.class);
        configuration.addAnnotatedClass(Funcionario.class);
        configuration.addAnnotatedClass(Produto.class);
        configuration.addAnnotatedClass(Itens_Pedido.class);
        configuration.addAnnotatedClass(Pedido.class);
        configuration.addAnnotatedClass(Rota.class);
        configuration.addAnnotatedClass(Sger.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }
}
