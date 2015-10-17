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
import com.entity.Schedule;

@Repository("CourseDAO")
public class CourseDAOImpl implements CourseDAO {

    private SessionFactory sessionFactory;

    public CourseDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Function to get all course existing in DB
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public ArrayList<Course> getAll() {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Course.class, "course")
                .createAlias("schedule", "schedule", JoinType.INNER_JOIN);
        List<Course> list = cr.list();
        ArrayList<Course> courseList = new ArrayList<Course>();
        if (list == null || list.size() == 0) { //Don't do anything
        } else {
            for (Course obj : list) {
                courseList.add((Course) obj);
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
    @Transactional
    public Course getByNumber(int courseNumber) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Course.class, "course")
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
    @Transactional
    public Course getByName(String courseName) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Course.class, "course")
                        .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                        .add(Restrictions.eq("course.courseName", courseName));
        Course course = (Course) cr.uniqueResult();
        return course;
    }

    /**
     * Get courses by majorId
     * @params Integer majorId
     * @return ArrayList of course object
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public ArrayList<Course> getByMajor(int majorId) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Course.class, "course")
                .createAlias("schedule", "schedule", JoinType.INNER_JOIN)
                .add(Restrictions.eq("course.majorId", majorId));
        List<Course> list = cr.list();
        ArrayList<Course> courseList = new ArrayList<Course>();
        if (list == null || list.size() == 0) { //Don't do anything
        } else {
            for (Course obj : list) {
                courseList.add((Course) obj);
            }
        }
        return courseList;
    }

}
