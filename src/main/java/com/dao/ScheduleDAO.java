package com.dao;

import com.entity.Schedule;

/**
 * Schedule object interface
 */
public interface ScheduleDAO {

    /**
     * Function to get schedule based on scheduleId
     * @param int scheduleId
     * @return Schedule object
     */
    public Schedule getScheduleById(int scheduleId);
}
