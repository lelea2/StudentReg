package com.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;

/**
 * Data transfer object for major
 * Based on table
 * CREATE TABLE Schedules(
 *      scheduleId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *      day VARCHAR(3) DEFAULT ‘TBA’,
 *      startTime VARCHAR(100) DEFAULT ‘TBA’,
 *      endTime VARCHAR(100) DEFAULT ‘TBA’
 * );
 */

@Entity
@Table(name="Schedules")
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "scheduleId", unique = true, nullable = false)
    private int scheduleId;

    @Column(name = "day", nullable = false)
    private String day;

    @Column(name="startTime", nullable = false)
    private String startTime;

    @Column(name="endTime", nullable = false)
    private String endTime;

    @OneToMany(targetEntity=Schedule.class, cascade=CascadeType.ALL)
    @JoinColumn(name="scheduleId")
    private List<Course> courselist = new ArrayList<Course>();

    /**
     * Class Constructor
     */
    public Schedule() {}

    public Schedule(int scheduleId, String day, String startTime, String endTime) {
        super();
        this.scheduleId = scheduleId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Get scheduleid
     *
     * @return int scheduleId
     */
    public int getScheduleId() {
        return scheduleId;
    }

    /**
     * Set scheduleId
     * @param int scheduleId
     */
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * Get schedule day
     *
     * @return String schedule day
     */
    public String getDay() {
        return day;
    }

    /**
     * Set schedule day
     *
     * @param String day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Get schedule start-time
     *
     * @return string time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Set schedule start-time
     *
     * @param String startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Get schedule end time
     *
     * @return String endtime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Set schedule end time
     *
     * @param String endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Schedule [scheduleId=" + scheduleId + ", day=" + day + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }

}
