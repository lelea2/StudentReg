package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

import java.util.*;
import org.hibernate.annotations.Type;
import java.io.Serializable;

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
@IdClass(UserCourse.class)
public class UserCourse implements Serializable {

    @Id
    @Type(type="uuid-char")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName="userId", nullable = false)
    private User user;

    //select * from Users_Courses INNER JOIN Courses where Courses.courseId=Users_Courses.courseId;
    @Id
    @Type(type="uuid-char")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "courseId", referencedColumnName="courseId", nullable = false)
    private Course course;

    /**
     * Class constructor
     */
    public UserCourse() {
    }

    /**
     * Class constructor
     * @param User user object
     * @param Course course object
     */
    public UserCourse(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    /**
     * Get user
     *
     * @return User user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set user
     *
     * @param User userId
     */
    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "UserCourse [userId=" + user.getUserId() + ", courseId=" + course.getCourseId() +  "]";
    }

}