package com.entity;

import java.io.Serializable;
import java.util.*;

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
    @Column(name = "scheduleId", unique = true)
    private int scheduleId;


    @Column(name = "day")
    private String day;

    @Column(name="startTime")
    private String startTime;

    @Column(name="endTime")
    private String endTime;

    @OneToMany(targetEntity=Schedule.class, cascade=CascadeType.ALL)
    @JoinColumn(name="scheduleId")
    private List<Course> courselist = new ArrayList<Course>();

    public Schedule() {}

    public Schedule(int scheduleId, String day, String startTime, String endTime) {
        super();
        this.scheduleId = scheduleId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Schedule [scheduleId=" + scheduleId + ", day=" + day + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }

}
