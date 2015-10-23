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
public class ScheduleDAOImpl extends BaseDAOImpl implements ScheduleDAO {

    /**
     * Constructor class
     * @param sessionFactory
     */
    public ScheduleDAOImpl(SessionFactory sessionFactory) { super(sessionFactory); }

    /**
     * Get all schedules available
     * @return ArrayList of schedules
     */
    public ArrayList<Schedule> getAll() {
        List<Schedule> list = this.createCriteria(Schedule.class, "schedule", false).list();
        ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
        if (list == null || list.size() == 0) { //Don't do anything
        } else {
            for (Schedule o : list) {
                scheduleList.add(new Schedule(o.getScheduleId(), o.getDay(), o.getStartTime(), o.getEndTime()));
            }
        }
        return scheduleList;
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
        Criteria cr = this.createCriteria(Schedule.class, "schedule", false)
                            .add(Restrictions.eq("scheduleId", scheduleId));
        Schedule schedule = (Schedule) cr.uniqueResult();
        return schedule;
    }
}
