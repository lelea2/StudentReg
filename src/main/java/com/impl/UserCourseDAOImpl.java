package com.impl;

import java.lang.reflect.Array;
import java.util.*;

import com.dao.CourseDAO;
import com.rest.Users_Courses;
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
import com.entity.User;

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
        try {
            Criteria cr = this.createCriteria(UserCourse.class, "user_course", false)
                    .createAlias("user", "user", JoinType.INNER_JOIN)
                    .createAlias("course", "course", JoinType.INNER_JOIN)
                    .add(Restrictions.eq("user.userId", userId))
                    .addOrder(Order.asc("course.courseName"));
            return generateCourseList(cr.list());
        } catch(Exception e) {
            String msg = String.format("Error getting registered courses userId=%s, Message : %s", userId.toString(), e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
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
        try {
            ArrayList<UserCourse> user_courses = getEntityObj(userId, courseIds);
            this.saveBatch(UserCourse.class, user_courses);
            return true;
        } catch(DAOException e) {
            return false; //TODO: handle to bubble up different errorcode to REST
        } catch (Exception e) {
            String msg = String.format("Error registered courses userId=%s, Message : %s", userId.toString(), e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
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
        try {
            ArrayList<UserCourse> user_courses = getEntityObj(userId, courseIds);
            this.deleteBatch(UserCourse.class, user_courses);
            return true;
        } catch(DAOException e) {
            return false; //TODO: handle to bubble up different errorcode to REST
        } catch (Exception e) {
            String msg = String.format("Error drop courses userId=%s, Message : %s", userId.toString(), e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
    }

    /**
     * Helper function to generate list of composite keys
     * @param User user object
     * @param ArrayList courseId
     * @return ArrayList of composite key
     */
    private ArrayList<UserCourse> getEntityObj(UUID userId, ArrayList<UUID> courseIds) throws DAOException {
        try {
            User user = (User) this.get(User.class, userId);
            ArrayList<UserCourse> user_courses = new ArrayList<UserCourse>();
            for (int i = 0; i < courseIds.size(); i++) {
                Course course = (Course) this.get(Course.class, courseIds.get(i));
                UserCourse usercourse = new UserCourse();
                usercourse.setUser(user);
                usercourse.setCourse(course);
                user_courses.add(usercourse);
            }
            return user_courses;
        } catch (Exception e) {
            String msg = String.format("Error getting object userId=%s, courseIds=%s, Message : %s", userId.toString(), courseIds.toString(), e.getMessage());
            this.getLogger().error(msg);
            throw new DAOException(msg);
        }
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
