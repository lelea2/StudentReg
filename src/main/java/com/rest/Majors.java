package com.rest;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;

import com.entity.Major;
import com.dao.MajorDAO;

/**
 * Java class handle all API call related to majors
 */
@Path("/majors")
public class Majors {

    @Autowired
    private MajorDAO majorDAO;

    /**
     * Get all majors available
     * @return Array contains JSON major object
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String allMajors() {
        String majors = "";
        ArrayList<Major> majorList = new ArrayList<Major>();
        try {
            majorList = majorDAO.getMajors();
            Gson gson = new Gson();
            majors = gson.toJson(majorList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return majors;
    }

    /**
     * Get all courses available for requested major
     * @return Array contains JSON course object
     */

    @GET
    @Path("/{majorId}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public String coursePerMajor() {
        return "";
    }
}
