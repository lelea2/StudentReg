package com.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.*;

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
public class Major {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="majorId", unique = true, nullable=false)
    private int majorId;

    @Column(name="majorName", nullable=false)
    private String majorName;

    public Major() {}

    public Major(int majorId, String majorName) {
        super();
        this.majorId = majorId;
        this.majorName = majorName;
    }

    /**
     * Get course majorId
     * @return int majorId
     */
    public int getMajorId() {
        return majorId;
    }

    /**
     * Set course majorId
     * @param int majorId
     */
    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    /**
     * Get major name
     * @return String majorName
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * Set major name
     * @param String majorName
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "Major [majorId=" + majorId + ", majorName=" + majorName + "]";
    }

}
