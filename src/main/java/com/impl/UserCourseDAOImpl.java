package com.impl;

import java.lang.reflect.Array;
import java.util.*;

import com.dao.CourseDAO;
import com.util.exception.DAOException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.criterion.*;
import org.hibernate.Criteria;
import org.hibernate.sql.JoinType;


import com.dao.UserCourseDAO;
import com.entity.UserCourse;
import com.entity.Course;

/**
 * Detail implemenation for User_Course related
 */
@Repository("UserCourseDAO")
public class UserCourseDAOImpl extends BaseDAOImpl implements UserCourseDAO {

    /**
     * Constructor class
     * @param SessionFactory object sessionFactory
     */
    public UserCourseDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Get registered courses based on userId
     * @param UUID userId
     * @return ArrayList of courses
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    public ArrayList<Course> getRegisteredCourses(UUID userId) throws DAOException {
        Criteria cr = this.createCriteria(UserCourse.class, "user_course", false)
                .createAlias("course", "course", JoinType.INNER_JOIN)
                .add(Restrictions.eq("user_course.userId", userId))
                .addOrder(Order.asc("course.courseName"));
        List<UserCourse> list = cr.list();
        return generateCourseList(list);
    }

    /**
     * Function to register courses for specific userId
     * @param UUID userId
     * @param ArrayList of courseIds
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Boolean registerCourses(UUID userId, ArrayList<UUID> courseIds) throws DAOException {
        return true;
    }

    /**
     * Function to drop courses per specific userId
     * @param UUID userId
     * @param ArrayList of courseIds
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Boolean dropCourses(UUID userId, ArrayList<UUID> courseIds) throws DAOException {
        return true;
    }

    /**
     * Private function to generate array of Course list
     * @param UserCourse list
     * @return ArrayList of courses
     */
    private ArrayList<Course> generateCourseList(List<UserCourse> list) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        if (list == null || list.size() == 0) { //Don't do anything
        } else {
            for (UserCourse obj : list) {
                courseList.add(obj.getCourse());
            }
        }
        return courseList;
    }
}
