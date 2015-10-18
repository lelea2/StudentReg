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
import org.omg.CORBA.OBJ_ADAPTER;

/**
 * BaseDAO for entity Daos that use Hibernate with a relational
 * database
 */
public abstract class BaseDAOImpl {

    protected SessionFactory sessionFactory;

    public BaseDAOImpl() {}

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
    public void save(Object entity, Object id) throws Exception {
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
     * Get value of an entity
     *
     * @param Class entityClass
     * @param  Object id
     * @return Entity object
     * @throws Exception
     */
    protected <T> Object get(Class<T> entityClass, Serializable id) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(entityClass, id);
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

}
