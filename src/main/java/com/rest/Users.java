package com.rest;

import java.util.*;
import java.util.UUID;
import java.util.ArrayList;

import com.domain.UserRegisterBody;
import com.util.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.domain.UserRequestBody;
import com.domain.UserRegisterBody;
import com.util.constant.Constant;

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

    /**
     * Function to get all users existing in DB
     * @return ArrayList of user object
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAll() {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            userList = userDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Function to get User by on userId
     * @return User object
     */
    @GET
    @Path("/id/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@NotNull @PathParam("userId") final String userId) {
        User user = null;
        try {
            user = userDAO.getById(UUID.fromString(userId));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /***
     * Function authenticate user based on email and password
     * @return JSON object contains userId
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logUserIn(UserRequestBody obj) {
        try {
            User user = userDAO.getByEmail(obj.getUserName(), obj.getUserPassword());
            if (user != null) {
                UUID userId = user.getUserId();
                return Response.status(Status.OK).entity("{'token': '" + userId + "'}").build();
            } else {
                throw new NullPointerException();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(Constant.Status.FAIL).build();
        }
    }

    /**
     * Function to register user
     *
     * @param UserRegisterBody object has user information for registration
     * @return statusCode 200 for success, 500 for failure
     *
     */
    @PUT
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserRegisterBody obj) {
        try {
            Boolean success = userDAO.createUser(obj.getUserEmail(), obj.getUserPassword(),
                    obj.getFirstName(), obj.getLastName(), obj.getRoleId(), obj.getMajorId());
            if (success.equals(true)) {
                return Response.status(Status.CREATED).entity(Constant.Status.SUCCESS).build();
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(Constant.Status.FAIL).build();
        }
    }

    /**
     * Function to update user
     *
     * @param String userId
     * @param UserRegisterBody object has user information for registration
     * @return statusCode 200 for success, 500 for failure
     *
     */
    @PUT
    @Path("/update/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@NotNull @PathParam("userId") final String userId,
                               UserRegisterBody obj) {
        try {
            Boolean success = userDAO.updateUser(UUID.fromString(userId), obj.getUserPassword(), obj.getFirstName(),
                                obj.getLastName(), obj.getRoleId(), obj.getMajorId());
            if (success.equals(true)) {
                return Response.status(Status.CREATED).entity(Constant.Status.SUCCESS).build();
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(Constant.Status.FAIL).build();
        }
    }

    @DELETE
    @Path("/delete/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@NotNull @PathParam("userId") final String userId) {
        try {
            Boolean success = userDAO.deleteUser(UUID.fromString(userId));
            if (success.equals(true)) {
                return Response.status(Status.CREATED).entity(Constant.Status.SUCCESS).build();
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(Constant.Status.FAIL).build();
        }
    }

}