package com.entity;

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

import com.entity.Schedule;

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
public class Course {

    @Id
    @Column(name = "courseId", unique = true)
    private String courseId;

    @Column(name="courseName")
    private String courseName;

    @Column(name="courseNumber")
    private int courseNumber;

    @Column(name="majorId")
    private int majorId;

    @Column(name="stateId")
    private String stateId;

    @Column(name="professor")
    private String professor;

    @Column(name="description")
    private String description;;

    @Column(name="location")
    private String location;

    //select * from Courses INNER JOIN Schedules where Courses.scheduleId=Schedules.scheduleId;
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "scheduleId", nullable = false)
    private Schedule schedule;

    public Course() {}

    public Course(String courseId, String courseName, int courseNumber, int majorId, String stateId, String professor, String description, String location) {
        super();
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.majorId = majorId;
        this.stateId = stateId;
        this.professor = professor;
        this.description = description;
        this.location = location;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this. description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseNumber=" + courseNumber
                + ", majorId=" + majorId  + ", stateId=" + stateId
                + ", professor=" + professor + ", description=" + description + ", location=" + location + "]";
    }

}
