package com.dao;

import com.entity.Schedule;

import java.util.ArrayList;

/**
 * Schedule object interface
 */
public interface ScheduleDAO {

    /**
     * Function to get schedules available
     * @return ArrayList of Schedule object
     */
    public ArrayList<Schedule> getAll();

    /**
     * Function to get schedule based on scheduleId
     * @param int scheduleId
     * @return Schedule object
     */
    public Schedule getScheduleById(int scheduleId);
}
