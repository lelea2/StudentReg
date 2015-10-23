package com.impl;

import java.util.Set;
import java.io.Serializable;

import javax.persistence.Entity;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.EntityKey;

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
     * Save an entity
     *
     * @param Object entity
     * @param Object id
     *
     */
    @SuppressWarnings("unchecked")
    protected void save(Object entity, Object id) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
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
        Session session = this.sessionFactory.getCurrentSession();
        Object persistentInstance = session.load(type, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
            return true;
        }
        return false;
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
        Session session = this.sessionFactory.getCurrentSession();
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
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass, entityName);
        if (forUpdate) {
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
