package org.connection.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * HibernateUtil class is used to create a session factory object.
 *
 * @author Jugh
 * @version 1.0
 */
public class HibernateUtil {
    /**
     * The sessionFactory object is used to create a session object.
     */
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    /**
     * The getSessionFactory method is used to get the sessionFactory object.
     *
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
