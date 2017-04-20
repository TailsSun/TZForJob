package DB;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;

/**
 * Created by DNS on 19.04.2017.
 */
public class DBService {
    private static final String hibernate_show_sql = "false";
    private static final String hibernate_hbm2ddl_auto = "create";

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    // стандартная для фабрики сессий
    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    // конфиг к H2
    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Test.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "tails");
        configuration.setProperty("hibernate.connection.password", "tails");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    public ArrayList<Integer> get() {
        try {
            Session session = sessionFactory.openSession();
            DAO dao = new DAO(session);
            ArrayList tests = dao.getAll();
            session.close();
            sessionFactory.close();
            return tests;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(int n) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            DAO dao = new DAO(session);
            dao.addN(n);
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
