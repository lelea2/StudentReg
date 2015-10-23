package com.rest;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.entity.Schedule;
import com.dao.ScheduleDAO;

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
    public ArrayList<Schedule> getAll() {
        ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
        try {
            scheduleList = scheduleDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheduleList;
    }
}
