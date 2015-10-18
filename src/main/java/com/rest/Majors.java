package com.rest;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entity.Major;
import com.entity.Course;
import com.dao.MajorDAO;
import com.dao.CourseDAO;

/**
 * Java class handle all API call related to majors
 */
@Path("/majors")
public class Majors {

    @Autowired
    private MajorDAO majorDAO;

    @Autowired
    private CourseDAO courseDAO;

    /**
     * Get all majors available
     * @return Array contains JSON major object
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Major> allMajors() {
        ArrayList<Major> majorList = new ArrayList<Major>();
        try {
            majorList = majorDAO.getMajors();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return majorList;
    }

    /**
     * Get all courses available for requested major
     * @return Array contains JSON course object
     */
    @GET
    @Path("/{majorId}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Course> coursePerMajor(@DefaultValue("courseName") @QueryParam("sortBy") final String sortBy,
                                            @NotNull @PathParam("majorId") final int majorId) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        try {
            courseList = courseDAO.getByMajor(majorId, sortBy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }
}
