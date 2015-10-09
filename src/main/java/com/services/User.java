package com.services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import model.AccessManager;

import dto.Course;

@Path("/user")
public class User {
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String courses() {
        String courses = null;
        ArrayList<Course> courseList = new ArrayList<Course>();
        try {
            courseList = new AccessManager().getCourses();
            Gson gson = new Gson();
            courses = gson.toJson(courseList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
}