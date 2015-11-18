package com.dao;

import java.util.UUID;
import java.util.ArrayList;

import com.entity.User;
import com.util.exception.DAOException;

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
    public ArrayList<User> getAll(int pageNumber, int pageSize) throws DAOException;

    /**
     * Function to get user by userId
     *
     * @param UUID userId
     *
     * @return User object
     */
    public User getById(UUID userId) throws DAOException;

    /**
     * Function to get user by email and password
     *
     * @param String email
     * @param String pwd
     * @return User object
     */
    public User getByEmail(String email, String pwd) throws DAOException;

    /**
     * Function to update exisiting user
     *
     * @param UUID userId
     * @param String firstName
     * @param String lastName
     * @param String pwd
     * @param int majorId
     * @param int roleId
     *
     * @return Boolean value for SUCCESS/FAILURE
     */
    public boolean updateUser(UUID userId, String pwd, String firstName, String lastName, int roleId, int majorId) throws DAOException;

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
     * @return new userId created
     */
    public UUID createUser(String email, String pwd, String firstName, String lastName, int roleId, int majorId) throws DAOException;

    /**
     * Function to delete user by userId
     *
     * @param UUID userId
     * @return Boolean value for SUCCESS/FAILURE
     *
     */
    public boolean deleteUser(UUID userId) throws DAOException;

}
