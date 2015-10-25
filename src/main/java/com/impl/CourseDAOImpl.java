package com.impl;

import java.util.*;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.criterion.*;
import org.hibernate.Criteria;
import org.hibernate.sql.JoinType;

import com.dao.CourseDAO;
import com.entity.Course;
import com.util.exception.DAOException;

/**
 * Details implementation for courses related
 */
@Repository("CourseDAO")
public class CourseDAOImpl extends BaseDAOImpl implements CourseDAO {

    /**
     * Constructor class
     * @param sessionFactory
     */
    public CourseDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Function to get all course existing in DB
     * @param String sortBy (default by courseNumber)
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public ArrayList<Course> getAll(String sortBy) throws DAOException {
        try {
            Criteria cr = this.createCriteria(Course.class, "course", false)
                    .setCacheable(true)
                    .createAlias("major", "major", JoinType.INNER_JOIN)
                    .createAlias("schedule", "schedule", JoinType.INNER_JOIN);
            if (sortBy.equals("courseName")) {
                cr.addOrder(Order.asc("courseName"));
            } else {
                cr.addOrder(Order.asc("courseNumber"));
            }
            return generateCourseList(cr.list());
        } catch(Exception e) {
            String msg = String.format("Error getting all courses, Message : %s", e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
    }

    /**
     * Get course by course number
     * @params Integer courseNumber
     * @return Course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public Course getByNumber(int courseNumber) throws DAOException {
        try {
            Criteria cr = this.createCriteria(Course.class, "course", false)
                    .setCacheable(true)
                    .createAlias("major", "major", JoinType.INNER_JOIN)
                    .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                    .add(Restrictions.eq("course.courseNumber", courseNumber));
            return (Course) cr.uniqueResult();
        } catch(Exception e) {
            String msg = String.format("Error getting courses with courseNumber=%d, Message : %s", courseNumber,  e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
    }

    /**
     * Get course by course name
     * @params String courseName
     * @return Course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public Course getByName(String courseName) throws DAOException {
        try {
            Criteria cr = this.createCriteria(Course.class, "course", false)
                    .setCacheable(true)
                    .createAlias("major", "major", JoinType.INNER_JOIN)
                    .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                    .add(Restrictions.eq("course.courseName", courseName));
            return (Course) cr.uniqueResult();
        } catch(Exception e) {
            String msg = String.format("Error getting unique course with courseName=%s, Message : %s", courseName,  e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
    }

    /**
     * Get array list of course with similar search name
     * @param String courseName
     * @return Arraylist of Course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public ArrayList<Course> getCoursesByName(String courseName) throws DAOException {
        try {
            Criteria cr = this.createCriteria(Course.class, "course", false)
                    .setCacheable(true)
                    .createAlias("major", "major", JoinType.INNER_JOIN)
                    .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                    .add(Restrictions.like("course.courseName", courseName + "%"));
            return generateCourseList(cr.list());
        } catch(Exception e) {
            String msg = String.format("Error getting courses with courseName=%s, Message : %s", courseName,  e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
    }

    /**
     * Get courses by majorId
     * @params Integer majorId
     * @return ArrayList of course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public ArrayList<Course> getByMajor(int majorId, String sortBy) throws DAOException {
        try {
            Criteria cr = this.createCriteria(Course.class, "course", false)
                    .setCacheable(true)
                    .createAlias("major", "major", JoinType.INNER_JOIN)
                    .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                    .add(Restrictions.eq("major.majorId", majorId));
            if (sortBy.equals("courseNumber")) {
                cr.addOrder(Order.asc("courseNumber"));
            } else {
                cr.addOrder(Order.asc("courseName"));
            }
            return generateCourseList(cr.list());
        } catch(Exception e) {
            String msg = String.format("Error getting courses with majorId=%d, Message : %s", majorId, e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
    }

    /**
     * Get array list of courses based on scheduleId (search course by schedule)
     * @param int scheduleId
     * @return ArrayList of course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public ArrayList<Course> getBySchedule(int scheduleId) throws DAOException {
        try {
            Criteria cr = this.createCriteria(Course.class, "course", false)
                    .setCacheable(true)
                    .createAlias("major", "major", JoinType.INNER_JOIN)
                    .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                    .add(Restrictions.eq("schedule.scheduleId", scheduleId))
                    .addOrder(Order.asc("courseName"));
            return generateCourseList(cr.list());
        } catch(Exception e) {
            String msg = String.format("Error getting courses with scheduleId=%d, Message : %s", scheduleId, e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
    }

    /**
     * Helper function to generate array list of courses based on result query
     * @param List of course
     * @return ArrayList of course object
     */
    private ArrayList<Course> generateCourseList(List<Course> list) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        if (list == null || list.size() == 0) { //Don't do anything
        } else {
            for (Course obj : list) {
                courseList.add(obj);
            }
        }
        return courseList;
    }
}
