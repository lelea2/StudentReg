package com.rest;

import java.util.UUID;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.domain.UserRequestBody;
import com.domain.UserRegisterBody;
import com.domain.UserResponseBody;
import com.util.response.ComponentResponse;

import com.entity.User;
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
    public Response getAll() {
        try {
            ArrayList<User> userList = userDAO.getAll();
            return ComponentResponse.okResponse(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

    /**
     * Function to get User by on userId
     * @return User object
     */
    @GET
    @Path("/id/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@NotNull @PathParam("userId") final String userId) {
        try {
            User user = userDAO.getById(UUID.fromString(userId));
            return ComponentResponse.okResponse(user);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ComponentResponse.errorResponse();
        }
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
                return ComponentResponse.okResponse(new UserResponseBody(userId));
            } else {
                throw new NullPointerException();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
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
            UUID userId = userDAO.createUser(obj.getUserEmail(), obj.getUserPassword(),
                    obj.getFirstName(), obj.getLastName(), obj.getRoleId(), obj.getMajorId());
            if (!userId.toString().isEmpty()) {
                return ComponentResponse.createdResponse(new UserResponseBody(userId));
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
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
                return ComponentResponse.createdResponse();
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

    /**
     * Function to delete specifc user based on userId
     *
     * @param UUID userId
     * @return statusCode 200 for success, 500 for failure
     */
    @DELETE
    @Path("/delete/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@NotNull @PathParam("userId") final String userId) {
        try {
            Boolean success = userDAO.deleteUser(UUID.fromString(userId));
            if (success.equals(true)) {
                return ComponentResponse.okResponse();
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

}