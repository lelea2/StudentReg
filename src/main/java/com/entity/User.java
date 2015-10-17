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

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.*;
import java.util.UUID;
import java.util.ArrayList;

import com.entity.Course;
import com.entity.Role;

/**
 * Data transfer object for major
 * Based on table
 * CREATE TABLE Users (
 *      userId VARCHAR(36) NOT NULL PRIMARY KEY,
 *      email VARCHAR(100) NOT NULL UNIQUE,
 *      firstName VARCHAR(100) NOT NULL,
 *      lastName VARCHAR(100) NOT NULL,
 *      password VARCHAR(44) NOT NULL,
 *      roleId INT NOT NULL,
 *      majorId INT NOT NULL,
 *      CONSTRAINT FOREIGN KEY (roleId) REFERENCES Roles(roleId) ON UPDATE CASCADE ON DELETE CASCADE,
 *      CONSTRAINT FOREIGN KEY (majorId) REFERENCES Majors(majorId) ON UPDATE CASCADE ON DELETE CASCADE
 * );
 */

public class User {

    @Id
    @NotNull
    @Type(type="uuid-binary")
    @Column(name = "courseId", unique = true, nullable=false, updatable = false)
    private UUID userId;

    @Id
    @Column(name="email", unique=true, nullable=false, updatable = false)
    private String email;

    @Column(name="firstName", nullable=false)
    private String firstName;

    @Column(name="lastName", nullable=false)
    private String lastName;

    @Column(name="password", nullable=false)
    private String password;

    //select * from Users INNER JOIN Roles where Roles.roleId=Users.roleId;
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "majorId", nullable = false)
    private Major major;

    private ArrayList<Course> courseList;

    public User() {
    }

    public User(UUID userId, String email, String firstName, String lastName, String password, ArrayList<Course> courseList) {
        super();
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.courseList = courseList;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public ArrayList<Course> getCourseList() { return courseList; }

    public void setCourseList(ArrayList<Course> courseList) { this.courseList = courseList; }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + password + ", roleName=" + role.getRoleName()
                + ", majorId=" + major.getMajorId() + "]";
    }

}
