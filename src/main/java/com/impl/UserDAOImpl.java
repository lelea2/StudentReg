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

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {

    /**
     * Function to get all exisiting user
     * @return Arraylist of User object
     */
    public ArrayList<User> getAll() {
        ArrayList<User> userList = new ArrayList<User>();
        return userList;
    }

    /**
     * Function to get user by userId
     * @param String userId
     * @return User object
     */
    public User getById(String userId) {
        User user = new User();
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


}