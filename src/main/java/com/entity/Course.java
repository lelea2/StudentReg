package com.entity;

import java.io.Serializable;
import java.util.*;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *  Data transfer object for courses
 *  Based on table
 *  CREATE TABLE Courses(
 *       courseId VARCHAR(36) NOT NULL PRIMARY KEY,
 *       courseName VARCHAR(100) NOT NULL UNIQUE,
 *       courseNumber INT NOT NULL UNIQUE,
 *       majorId INT NOT NULL,
 *       scheduleId INT NOT NULL,
 *       stateId  VARCHAR(100),
 *       professor VARCHAR(100) DEFAULT 'STAFF',
 *       description VARCHAR(255),
 *       location VARCHAR(100) DEFAULT 'TBA',
 *       CONSTRAINT FOREIGN KEY(majorId) REFERENCES Majors(majorId) ON UPDATE CASCADE ON DELETE CASCADE,
 *       CONSTRAINT FOREIGN KEY(scheduleId) REFERENCES Schedules(scheduleId) ON UPDATE CASCADE ON DELETE CASCADE
 *   );
 */

@Entity
@Table(name="Courses")
public class Course implements Serializable {

    @Id
    @NotNull
    @Type(type="uuid-char")
    @Column(name = "courseId", unique = true, nullable=false, updatable = false)
    private UUID courseId;

    @Column(name="courseName")
    private String courseName;

    @Column(name="courseNumber", unique=true)
    private int courseNumber;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "majorId", referencedColumnName="majorId", nullable = false)
    private Major major;

    @Column(name="stateId")
    private String stateId;

    @Column(name="professor")
    private String professor;

    @Column(name="description")
    private String description;

    @Column(name="location")
    private String location;

    //select * from Courses INNER JOIN Schedules where Courses.scheduleId=Schedules.scheduleId;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "scheduleId", referencedColumnName="scheduleId", nullable = false)
    private Schedule schedule;

    /**
     * Class Constructor
     */
    public Course() {}

    public Course(UUID courseId, String courseName, int courseNumber, String stateId, String professor, String description, String location) {
        super();
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.stateId = stateId;
        this.professor = professor;
        this.description = description;
        this.location = location;
    }

    /**
     * Get courseId
     *
     * @return UUID for courseId
     */
    public UUID getCourseId() {
        return courseId;
    }

    /**
     * Set courseId
     *
     * @param UUID courseId
     */
    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    /**
     * Get course name
     *
     * @return {String} for courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set course name
     *
     * @param {String} courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Get course number
     *
     * @return int courseNumber
     */
    public int getCourseNumber() {
        return courseNumber;
    }

    /**
     * Get course number
     *
     * @param int courseNumber
     */
    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    /**
     * Get Schedule object for course
     *
     * @return Schedule object (scheduleId, date, startTime, endTime)
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Set Schedule object for course
     *
     * @param Schedule object
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Get course major object
     *
     * @return Major object
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Set course major object
     *
     * @param Major major object
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * Get course state (on/off)
     *
     * @return String courseState
     */
    public String getStateId() {
        return stateId;
    }

    /**
     * Set course state
     *
     * @param stateId
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    /**
     * Get course professor
     *
     * @return String course professor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * Set course professor
     *
     * @param String professor
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    /**
     * Get course description
     *
     * @return String course description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set course description
     *
     * @param String description
     */
    public void setDescription(String description) {
        this. description = description;
    }

    /**
     * Get course location
     *
     * @return String courseLocation
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set course location
     *
     * @param String location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseNumber=" + courseNumber
                + ", majorId=" + major.getMajorId()  + ", stateId=" + stateId
                + ", professor=" + professor + ", description=" + description + ", location=" + location
                + ", scheduleId=" + schedule.getScheduleId() + ", day=" + schedule.getDay()
                + ", startTime=" + schedule.getStartTime() + ", endTime=" + schedule.getEndTime() + "]";
    }

}
