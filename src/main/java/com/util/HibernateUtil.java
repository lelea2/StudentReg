package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Utility service for hibernate connection
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public static Configuration getInitConfiguration() {
        Configuration config = new Configuration();
        config.configure();
        return config;
    }

    public static Session getSession() {
        if (factory == null) {
            Configuration config = HibernateUtil.getInitConfiguration();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    config.getProperties()).buildServiceRegistry();
            factory = config.buildSessionFactory(serviceRegistry);
        }
        Session hibernateSession = factory.getCurrentSession();
        return hibernateSession;
    }

    public static Session beginTransaction() {
        Session hibernateSession;
        hibernateSession = HibernateUtil.getSession();
        hibernateSession.beginTransaction();
        return hibernateSession;
    }

    public static void CommitTransaction() {
        HibernateUtil.getSession().getTransaction().commit();
    }

    public static void closeSession() {
        HibernateUtil.getSession().close();
    }

    public static void rollbackTransaction() {
        HibernateUtil.getSession().getTransaction().rollback();
    }

}
