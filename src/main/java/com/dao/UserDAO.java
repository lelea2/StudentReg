package com.dao;

import java.util.*;
import java.util.UUID;
import java.util.ArrayList;

import com.entity.User;

/**
 * User object interface
 */
public interface UserDAO {

    /**
     * Function to get all user
     *
     * @return ArrayList of User object
     *
     */
    public ArrayList<User> getAll();

    /**
     * Function to get user by userId
     *
     * @param UUID userId
     *
     * @return User object
     */
    public User getById(UUID userId);

    /**
     * Function to get user by email and password
     *
     * @param String email
     * @param String pwd
     * @return User object
     */
    public User getByEmail(String email, String pwd);

    /**
     * Function to update exisiting user
     *
     * @param String email
     * @param String firstName
     * @param String lastName
     * @param String pwd
     * @param int majorId
     * @param int roleId
     *
     * @return Boolean value for SUCCESS/FAILURE
     */
    public boolean updateUser(String email, String pwd, String firstName, String lastName, int roleId, int majorId);

    /**
     * Function to create user
     *
     * @param String email
     * @param String firstName
     * @param String lastName
     * @param String pwd
     * @param int majorId
     * @param int roleId
     *
     * @return Boolean value for SUCCESS/FAILURE
     */
    public boolean createUser(String email, String pwd, String firstName, String lastName, int roleId, int majorId);

    /**
     * Function to delete user by userId
     *
     * @param String userId
     * @return Boolen value for SUCCESS/FAILURE
     *
     */
    public boolean deleteUser(String userId);

}
