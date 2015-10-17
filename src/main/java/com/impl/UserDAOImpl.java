package com.impl;

import java.util.*;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.criterion.*;
import org.hibernate.Criteria;
import org.hibernate.sql.JoinType;

import com.dao.UserDAO;
import com.entity.Major;
import com.entity.Course;
import com.entity.User;

/**
 * Detail implementations for user related
 */
@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Function to get all exisiting user
     * @return Arraylist of User object
     */
    public ArrayList<User> getAll() {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(User.class, "user")
                        .createAlias("major", "major", JoinType.INNER_JOIN)
                        .createAlias("role", "role", JoinType.INNER_JOIN);
        List<User> list = cr.list();
        return generateUserList(list);
    }

    /**
     * Function to get user by userId
     * @param UUID userId
     * @return User object
     */
    public User getById(UUID userId) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(User.class, "user")
                        .createAlias("major", "major", JoinType.INNER_JOIN)
                        .createAlias("role", "role", JoinType.INNER_JOIN)
                        .add(Restrictions.eq("user.userId", userId));
        User user = (User) cr.uniqueResult();
        return user;
    }

    /**
     * Function to get user by email and password
     * @param String email
     * @param String pwd
     * @return User object
     */
    public User getByEmail(String email, String pwd) {
        User user = new User();
        return user;
    }

    /**
     * Function to update exisiting user
     * @param User user object
     * @return Boolean value for SUCCESS/FAILURE
     */
    public boolean updateUser(User user) {
        return false;
    }

    /**
     * Function to create user
     * @param User user object
     * @return Boolean value for SUCCESS/FAILURE
     */
    public boolean createUser(User user) {
        return false;
    }

    /**
     * Function to delete user by userId
     * @param String userId
     * @return Boolen value for SUCCESS/FAILURE
     */
    public boolean deleteUser(String userId) {
        return false;
    }

    /**
     * Helper function to generate array list of users based on result query
     * @param List of user
     * @return ArrayList of user object
     */
    private ArrayList<User> generateUserList(List<User> list) {
        ArrayList<User> userList = new ArrayList<User>();
        if (list == null || list.size() == 0) { //Don't do anything
        } else {
            for (User obj : list) {
                userList.add((User) obj);
            }
        }
        return userList;
    }


}