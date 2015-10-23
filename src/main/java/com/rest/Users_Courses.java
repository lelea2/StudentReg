package com.rest;

import java.util.ArrayList;
import java.util.UUID;

import com.dao.UserCourseDAO;
import com.dao.UserDAO;
import com.entity.User;
import com.util.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entity.Course;
import com.dao.UserCourseDAO;

/**
 * Java class handle all API call related to users course registration call
 */
@Path("/users/courses")
public class Users_Courses {

    @Autowired
    private UserCourseDAO userCourseDAO;

    @GET
    @Path("/registered/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCourseRegistered(@NotNull @PathParam("userId") final String userId) {
        try {
            ArrayList<Course> courses = userCourseDAO.getRegisteredCourses(UUID.fromString(userId));
            return Response.status(Response.Status.OK).entity(courses).build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Constant.Status.FAIL).build();
        }
    }
}
