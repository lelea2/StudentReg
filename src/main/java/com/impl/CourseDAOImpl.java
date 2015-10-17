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
        List<Course>list = cr.list();
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
                        .createAlias("schedule", "schedule")
                        .add(Restrictions.eq("course.courseName", courseName));
        Course course = (Course) cr.uniqueResult();
        return course;
    }

    /**
     * Get courses by majorId
     * @params Integer majorId
     * @return ArrayList of course object
     */
    public ArrayList<Course> getByMajor(int majorId) {
        List<Course> list = sessionFactory.getCurrentSession().createCriteria(Course.class).list();
        ArrayList<Course> courseList = new ArrayList<Course>();
        if (list == null || list.size() == 0) { //Don't do anything
        } else {
            for (Course o : list) {
                courseList.add(new Course());
            }
        }
        return courseList;
    }
}
