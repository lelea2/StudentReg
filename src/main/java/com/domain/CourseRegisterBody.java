package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;
import java.io.Serializable;

/**
 * CourseRegisterBody object contains courseIds for course register or drop courses
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseRegisterBody implements  Serializable {

    private static final long serialVersionUID = 1L;

    private UUID userId;
    private ArrayList<UUID> courseIds;

    /**
     * Get userID
     * @return UUID userId
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Set userId
     * @param UUID userId
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Get courseIds array
     * @return ArrayList of coursesId
     */
    public ArrayList<UUID> getCourseIds() {
        return courseIds;
    }

    /**
     * Set courseIds
     * @param ArrayList courseIds
     */
    public void setCourseIds(ArrayList<UUID> courseIds) {
        this.courseIds  = courseIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseRegisterBody)) return false;
        CourseRegisterBody that = (CourseRegisterBody) o;
        return Objects.equals(courseIds, that.courseIds) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseIds, userId);
    }

    @Override
    public String toString() {
        return "CourseRegisterBody{" +
                ", userId='" + userId + '\'' +
                ", courseIds='" + courseIds.toString() +
                '}';
    }

}
