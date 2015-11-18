package com.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entity.Course;
import com.dao.CourseDAO;
import com.util.response.ComponentResponse;

/**
 * Java class handle all API call related to courses
 */

@Path("/courses")
public class Courses {

    @Autowired
    private CourseDAO courseDAO;

    /**
     * Get all courses available in the databases
     * @return ArrayList of Course object
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourses(@DefaultValue("courseNumber") @QueryParam("sortBy") final String sortBy,
                                  @DefaultValue("0") @QueryParam("startIndex") final Integer pageNumber,
                                  @DefaultValue("100") @QueryParam("number") final Integer pageSize) {
        try {
            ArrayList<Course> courseList = courseDAO.getAll(sortBy, pageNumber, pageSize);
            return ComponentResponse.okResponse(courseList);
        } catch (Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

    /**
     * Get array list of course which have similar as requested coursename
     * @return ArrayList of Course object
     */
    @GET
    @Path("/courseName/{courseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoursesByName(@NotNull @PathParam("courseName") final String courseName) {
        try {
            ArrayList<Course> courseList = courseDAO.getCoursesByName(courseName);
            return ComponentResponse.okResponse(courseList);
        } catch (Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

    /**
     * Get array list of courses based on scheduleId
     * @param int scheduleId
     * @return Array list of Course object
     */
    @GET
    @Path("/schedule/{scheduleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoursesByScheduleId(@NotNull @PathParam("scheduleId") final int scheduleId) {
        try {
            ArrayList<Course> courseList = courseDAO.getBySchedule(scheduleId);
            return ComponentResponse.okResponse(courseList);
        } catch(Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

    /**
     * Get specific course based on courseNumber or courseName
     * @param courseNumber
     * @param courseName
     * @return
     * @throws Exception
     */
    @GET
    @Path("/course")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecificCourse(@QueryParam("id") Integer courseNumber,
                                    @QueryParam("name") String courseName,
                                    @DefaultValue("courseName") @QueryParam ("type") String type) throws Exception {
        try {
            Course course = null;
            if (type.equals("courseNumber")) {
                course = courseDAO.getByNumber(courseNumber);
            } else { //Search by course name
                course = courseDAO.getByName(courseName);
            }
            return ComponentResponse.okResponse(course);
        } catch(Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }
}