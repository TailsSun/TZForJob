package DB;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


public class DBService {

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getH2Configuration() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("src/main/resources/property.cfg")));
        } catch (IOException e) {
            System.out.println("Configuration file 'property.cfg' not found");
        }

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Test.class);
        configuration.setProperty("hibernate.dialect", props.getProperty("hibernate_dialect"));
        configuration.setProperty("hibernate.connection.driver_class", props.getProperty("hibernate_connection_driver_class"));
        configuration.setProperty("hibernate.connection.url", props.getProperty("hibernate_connection_url"));
        configuration.setProperty("hibernate.connection.username", props.getProperty("hibernate_connection_username"));
        configuration.setProperty("hibernate.connection.password", props.getProperty("hibernate_connection_password"));
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    public ArrayList<Test> getAllElement() {
        try {
            Session session = sessionFactory.openSession();
            DAO dao = new DAO(session);
            ArrayList<Test> listTestValues = dao.getAll();
            session.close();
            return listTestValues;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
        return null;
    }

    public void add(int n) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            DAO dao = new DAO(session);
            dao.addNForBD(n);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
