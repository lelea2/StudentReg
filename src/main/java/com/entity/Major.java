package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

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
    @Column(name="majorId", unique = true)
    private int majorId;

    @Column(name="majorName")
    private String majorName;

    public Major() {}

    public Major(int majorId, String majorName) {
        super();
        this.majorId = majorId;
        this.majorName = majorName;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "Major [majorId=" + majorId + ", majorName=" + majorName + "]";
    }

}
