package com.impl;

import java.util.*;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.criterion.*;
import org.hibernate.Criteria;


import com.dao.ScheduleDAO;
import com.entity.Schedule;

/**
 * Detail implemenation for schedule related
 */
@Repository("Schedule")
public class ScheduleDAOImpl implements ScheduleDAO {
    private SessionFactory sessionFactory;

    public ScheduleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get schedule based on scheduleId
     * @param Integer scheduleId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public Schedule getScheduleById(int scheduleId) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Schedule.class).add(Restrictions.eq("scheduleId", scheduleId));
        Schedule schedule = (Schedule) cr.uniqueResult();
        return schedule;
    }
}
