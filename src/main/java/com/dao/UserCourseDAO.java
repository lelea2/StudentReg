package com.dao;

import java.util.*;
import java.util.UUID;
import java.util.ArrayList;

import com.entity.User;
import com.entity.Course;
import com.entity.UserCourse;

/**
 * Object interface for UserCourse
 */
public interface UserCourseDAO {

    /**
     * Get user's registered courses
     * @param UUID userId
     * @return ArrayList of courses
     */
    public ArrayList<Course> getRegisteredCourses(UUID userId);

    /**
     * Register courses
     * @param UUID userId
     * @param ArrayList of courses to be register
     * @return
     */
    public Boolean registerCourses(UUID userId, ArrayList<Course> courses);

    /**
     * Drop courses
     * @param UUID userId
     * @param ArrayList of courses to be dropped
     * @return
     */
    public Boolean dropCourses(UUID userId, ArrayList<Course> course);
}
