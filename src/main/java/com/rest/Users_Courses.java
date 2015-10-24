package com.rest;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entity.Course;
import com.dao.UserCourseDAO;
import com.domain.CourseRegisterBody;
import com.util.response.ComponentResponse;

/**
 * Java class handle all API call related to users course registration call
 */
@Path("/users/courses")
public class Users_Courses {

    @Autowired
    private UserCourseDAO userCourseDAO;

    /**
     * Function to get all registered courses per userId
     *
     * @param UUID userId
     * @return ArrayList of courses
     */
    @GET
    @Path("/registered/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCourseRegistered(@NotNull @PathParam("userId") final String userId) {
        try {
            ArrayList<Course> courses = userCourseDAO.getRegisteredCourses(UUID.fromString(userId));
            return ComponentResponse.okResponse(courses);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

    /**
     * Function to register course per userId
     *
     * @param String UUID userId
     * @param CourseResgiteredBody obj
     * @return 201 statuscode for success, 500 for failure
     */
    @PUT
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourses(@NotNull @QueryParam("userId") final String userId,
                               CourseRegisterBody obj) {
        try {
            Boolean success = userCourseDAO.registerCourses(UUID.fromString(userId), obj.getCourseIds());
            if (success.equals(true)) {
                return ComponentResponse.createdResponse();
            } else {
                throw new Exception();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

    /**
     * Function drop registered course(s) per userId
     *
     * @param String UUID userId
     * @param CourseRegisteredBody obj
     * @return 200 statuscode for success, 500 for failure
     */
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCourse(@NotNull @QueryParam("userId") final String userId,
                                 CourseRegisterBody obj) {
        try {
            Boolean success = userCourseDAO.dropCourses(UUID.fromString(userId), obj.getCourseIds());
            if (success.equals(true)) {
                return ComponentResponse.okResponse();
            } else {
                throw new Exception();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

}
