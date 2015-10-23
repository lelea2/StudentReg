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

/**
 * Details implementation for courses related
 */
@Repository("CourseDAO")
public class CourseDAOImpl extends BaseDAOImpl implements CourseDAO {

    private SessionFactory sessionFactory;

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
    public ArrayList<Course> getAll(String sortBy) {
        Criteria cr = this.createCriteria(Course.class, "course", false)
                        .createAlias("schedule", "schedule", JoinType.INNER_JOIN);
        if (sortBy.equals("courseName")) {
            cr.addOrder(Order.asc("courseName"));
        } else {
            cr.addOrder(Order.asc("courseNumber"));
        }
        List<Course> list = cr.list();
        ArrayList<Course> courseList = new ArrayList<Course>();
        if (list == null || list.size() == 0) { //Don't do anything
        } else {
            for (Course obj : list) {
                courseList.add( obj);
            }
        }
        return courseList;
    }

    /**
     * Get course by course number
     * @params Integer courseNumber
     * @return Course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public Course getByNumber(int courseNumber) {
        Criteria cr = this.createCriteria(Course.class, "course", false)
                        .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                        .add(Restrictions.eq("course.courseNumber", courseNumber));
        Course course = (Course) cr.uniqueResult();
        return course;
    }

    /**
     * Get course by course name
     * @params String courseName
     * @return Course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public Course getByName(String courseName) {
        Criteria cr = this.createCriteria(Course.class, "course", false)
                        .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                        .add(Restrictions.eq("course.courseName", courseName));
        Course course = (Course) cr.uniqueResult();
        return course;
    }

    /**
     * Get array list of course with similar search name
     * @param String courseName
     * @return Arraylist of Course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public ArrayList<Course> getCoursesByName(String courseName) {
        Criteria cr = this.createCriteria(Course.class, "course", false)
                .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                .add(Restrictions.like("course.courseName", courseName + "%"));
        List<Course> list = cr.list();
        return generateCourseList(list);
    }

    /**
     * Get courses by majorId
     * @params Integer majorId
     * @return ArrayList of course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public ArrayList<Course> getByMajor(int majorId, String sortBy) {
        Criteria cr = this.createCriteria(Course.class, "course", false)
                .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                .add(Restrictions.eq("course.majorId", majorId));
        if (sortBy.equals("courseNumber")) {
            cr.addOrder(Order.asc("courseNumber"));
        } else {
            cr.addOrder(Order.asc("courseName"));
        }
        List<Course> list = cr.list();
        return generateCourseList(list);
    }

    /**
     * Get array list of courses based on scheduleId (search course by schedule)
     * @param int scheduleId
     * @return ArrayList of course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public ArrayList<Course> getBySchedule(int scheduleId) {
        Criteria cr = this.createCriteria(Course.class, "course", false)
                .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                .add(Restrictions.eq("schedule.scheduleId", scheduleId))
                .addOrder(Order.asc("courseName"));
        List<Course> list = cr.list();
        return generateCourseList(list);
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
