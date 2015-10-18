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
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.*;
import java.util.UUID;
import java.util.ArrayList;

import com.entity.Course;

/**
 * Data transfer object for Users_Courses
 * Based on table
 * CREATE TABLE Users_Courses(
 *      userId VARCHAR(36) NOT NULL,
 *      courseId VARCHAR(36) NOT NULL,
 *      FOREIGN KEY(userId) REFERENCES Users(userId) ON UPDATE CASCADE ON DELETE CASCADE,
 *      FOREIGN KEY(courseId) REFERENCES Courses(courseId) ON UPDATE CASCADE ON DELETE CASCADE,
 *      PRIMARY KEY (userId, courseId)
 * );
 */

@Entity
@Table(name="Users_Courses")
public class UserCourse implements Serializable {

    @Id
    @NotNull
    @Type(type="uuid-char")
    @Column(name = "userId", unique = true, nullable=false, updatable = false)
    private UUID userId;

    //select * from Users_Courses INNER JOIN Courses where Courses.courseId=Users_Courses.courseId;
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    /**
     * Class constructor
     */
    public UserCourse() {
    }

    public UserCourse(UUID userId) {
        super();
        this.userId = userId;
    }

    /**
     * Get userId
     *
     * @return UUID userId
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Set userId
     *
     * @param UUID userId
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Get course
     * @return
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Set course
     * @param Course course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

}