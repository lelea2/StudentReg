package com.impl;

import java.util.*;

import com.entity.Course;
import com.util.exception.DAOException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.criterion.*;
import org.hibernate.Criteria;

import com.dao.MajorDAO;
import com.entity.Major;

/**
 * Detail implementation for Major related
 */
@Repository("Majors")
public class MajorDAOImpl extends BaseDAOImpl implements MajorDAO {

    /**
     * Constructor class
     * @param sessionFactory
     */
    public MajorDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Function to get all existing major
     * @return ArrayList of Major object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public ArrayList<Major> getMajors() throws DAOException {
        try {
            Criteria cr = this.createCriteria(Major.class, "major", false).setCacheable(true).addOrder(Order.asc("majorName"));
            ArrayList<Major> majorList = new ArrayList<Major>();
            List<Major> list = cr.list();
            if (list == null || list.size() == 0) { //Don't do anything
            } else {
                for (Major o : list) {
                    majorList.add(new Major(o.getMajorId(), o.getMajorName()));
                }
            }
            return majorList;
        } catch(Exception e) {
            String msg = String.format("Error getting all major, Message : %s", e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
    }
}
