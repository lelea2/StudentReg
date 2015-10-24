package com.rest;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entity.Schedule;
import com.dao.ScheduleDAO;
import com.util.response.ComponentResponse;

/**
 * Java class handle all API call related to majors
 */
@Path("/schedules")
public class Schedules {

    @Autowired
    private ScheduleDAO scheduleDAO;

    /**
     * Function to get all schedules available
     * @return Arraylist of Schedule object
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            ArrayList<Schedule> scheduleList = scheduleDAO.getAll();
            return ComponentResponse.okResponse(scheduleList);
        } catch (Exception e) {
            e.printStackTrace();
            return ComponentResponse.errorResponse();
        }
    }
}
