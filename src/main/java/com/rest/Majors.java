package com.rest;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entity.Major;
import com.entity.Course;
import com.dao.MajorDAO;
import com.dao.CourseDAO;
import com.util.response.ComponentResponse;

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
    public Response allMajors() {
        try {
            ArrayList<Major> majorList = majorDAO.getMajors();
            return ComponentResponse.okResponse(majorList);
        } catch (Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }

    /**
     * Get all courses available for requested major
     * @return Array contains JSON course object
     */
    @GET
    @Path("/{majorId}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response coursePerMajor(@DefaultValue("courseName") @QueryParam("sortBy") final String sortBy,
                                            @NotNull @PathParam("majorId") final int majorId) {
        try {
            ArrayList<Course> courseList = courseDAO.getByMajor(majorId, sortBy);
            return ComponentResponse.okResponse(courseList);
        } catch (Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }
}
