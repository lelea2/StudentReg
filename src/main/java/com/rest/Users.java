package com.rest;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entity.Major;
import com.entity.Course;
import com.entity.User;
import com.dao.MajorDAO;
import com.dao.CourseDAO;
import com.dao.UserDAO;

/**
 * Java class handle all API call related to users
 */

@Path("/users")
public class Users {

    @Autowired
    private UserDAO userDAO;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAllUser () {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            userList = userDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

}