package com.rest;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import model.AccessManager;

import com.entity.Course;
import com.dao.CourseDAO;

@Path("/courses")
public class Courses {

    @Autowired
    private CourseDAO courseDAO;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCourses() {
        String courses = null;

        return courses;
    }

    /**
     * Get course based on courseNumber or courseName
     * @param courseNumber
     * @param courseName
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpecificCourse(@QueryParam("id") Integer courseNumber,
                                    @QueryParam("name") String courseName,
                                    @DefaultValue("courseNumber") @QueryParam ("type") String type) throws Exception {
        Course course;
        if (type.equals("courseNumber")) {
            course = courseDAO.getByNumber(courseNumber);
        } else { //Search by course name
            course = courseDAO.getByName(courseName);
        }
        return course.toString();
    }
}