package com.entity;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import java.io.*;
import java.util.Set;

/**
 *  Data transfer object for major
 *  Based on table
 *  CREATE TABLE Majors(
 *      majorId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *      majorName VARCHAR(100) NOT NULL UNIQUE
 *  );
 */

@Entity
@Table(name="Majors")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="majors")
public class Major implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="majorId", unique = true, nullable=false)
    private int majorId;

    @Column(name="majorName", nullable=false)
    private String majorName;

    @JsonIgnore
    @OneToMany(mappedBy="major",cascade=CascadeType.ALL)
    private Set<User> users;

    @JsonIgnore
    @OneToMany(mappedBy="major",cascade=CascadeType.ALL)
    private Set<Course> courses;

    /**
     * Class Constructor
     */
    public Major() {}

    public Major(int majorId, String majorName) {
        super();
        this.majorId = majorId;
        this.majorName = majorName;
    }

    /**
     * Get course majorId
     *
     * @return int majorId
     */
    public int getMajorId() {
        return majorId;
    }

    /**
     * Set course majorId
     *
     * @param int majorId
     */
    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    /**
     * Get major name
     *
     * @return String majorName
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * Set major name
     *
     * @param String majorName
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    /**
     * Total count of user with assigned role
     * @return Integer user count
     */
    public int totalUsers() {
        return users.size();
    }

    /**
     * Total count of courses in major
     * @return Integer courses count
     */
    public int totalCourses() {
        return courses.size();
    }

    @Override
    public String toString() {
        return "Major [majorId=" + majorId + ", majorName=" + majorName + "]";
    }

}
