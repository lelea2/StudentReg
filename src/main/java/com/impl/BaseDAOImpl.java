package com.impl;

import java.util.*;
import java.io.Serializable;

import javax.persistence.Entity;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.EntityKey;
import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseDAO for entity Daos that use Hibernate with a relational
 * database
 */
public abstract class BaseDAOImpl {

    protected SessionFactory sessionFactory;

    private Logger logger; // Logger

    /**
     * Class constructor
     */
    public BaseDAOImpl() {}

    /**
     * Class constructor
     */
    public BaseDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Helper function to get current session
     * @return Session current session
     */
    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * Save an entity
     *
     * @param Object entity
     * @param Object id
     *
     */
    @SuppressWarnings("unchecked")
    protected void save(Object entity, Object id) throws Exception {
        Session session = this.getSession();
        Set<EntityKey> keys = session.getStatistics().getEntityKeys();
        for (EntityKey key : keys) {
            if (key.getIdentifier().equals(id)) {
                session.merge(entity);
                return;
            }
        }
        session.saveOrUpdate(entity);
    }

    /**
     * Delete transient entity from data store
     * @param type
     * @param id
     * @return Boolean value for delete result
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected <T> boolean deleteById(Class<T> type, Serializable id) throws Exception {
        Session session = this.getSession();
        Object persistentInstance = session.load(type, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
            return true;
        }
        return false;
    }

    /**
     * Update/save in batch
     * @param Class entity
     * @param Arraylist of entity to update
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    protected <T> void saveBatch(Class<T> type, List<T> entity) throws Exception {
        Session session = this.getSession();
        for (int i = 0; i < entity.size(); i++) {
            session.save(entity.get(i));
            if (i % 20 == 0 && i > 0) { //size from config. TODO: need to get from config
                clearSession();
            }
        }
    }

    /**
     * Delete in batch
     * @param Class entity
     * @param Arraylist of entity to update
     * @param <T>
     */
    protected <T> void deleteBatch(Class<T> type, List<T> entity) throws Exception {
        Session session = this.getSession();
        for (int i = 0; i < entity.size(); i++) {
            session.delete(entity.get(i));
            if (i % 20 == 0 && i > 0) { //size from config. TODO: need to get from config
                clearSession();
            }
        }
    }

    /**
     * Clear current session (used during batch update/insert/delete)
     * @throws Exception
     */
    private void clearSession() {
        Session session = this.getSession();
        session.flush();
        session.clear();
    }

    /**
     * Get value of an entity
     *
     * @param Class entityClass
     * @param  Object id
     * @return Entity object
     * @throws Exception
     */
    protected <T> Object get(Class<T> entityClass, Serializable id) throws Exception {
        Session session = this.getSession();
        return session.load(entityClass, id);
    }

    /**
     * Create criteria with appropriate lock mode
     *
     * @param entityClass Entity class
     * @param forUpdate   Whether the user is being fetched for update
     *
     * @return Criteria
     * throws Exception
     */
    protected <T> Criteria createCriteria(Class<T> entityClass, String entityName, boolean forUpdate) {
        Session session = this.getSession();
        Criteria criteria = session.createCriteria(entityClass, entityName);
        if (forUpdate) {
            //Locking the parent objects is sufficient. Deadlocks won't necessarily occur
            criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
        } else {
            criteria.setLockMode(LockMode.NONE);
        }
        return criteria;
    }

    /**
     * Get logger
     *
     * @return Logger for this instance
     */
    protected Logger getLogger() {
        if (this.logger == null) {
            this.logger = LoggerFactory.getLogger(this.getClass());
        }
        return this.logger;
    }

}
