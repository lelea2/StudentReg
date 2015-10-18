package com.dao;

import java.util.ArrayList;
import com.entity.Course;

/**
 *  Course object interface
 */
public interface CourseDAO {

    /**
     * Function to get all course existing in DB
     * @param String sortBy (default by courseNumber)
     * @return Array list of course object
     */
    public ArrayList<Course> getAll(String sortBy);

    /**
     * Function to get course by course number
     * @param int courseNumber
     * @return Course object or null
     */
    public Course getByNumber(int courseNumber);

    /**
     * Function to get course by matching course name
     * @param String courseName
     * @return Course object or null
     */
    public Course getByName(String courseName);

    /**
     * Function to get courses by matching with name
     * This will get back lists of courses which have matching/similar name
     * @param courseName
     * @return Arraylist of courses object
     */
    public ArrayList<Course> getCoursesByName(String courseName);

    /**
     * Function to get course by major Id
     * @param int majorId
     * @param String sortBy (Default sortBy courseName)
     * @return ArrayList of Course object
     */
    public ArrayList<Course> getByMajor(int majorId, String sortBy);

    /**
     * Function to get course by scheduleId
     * @param int ScheduleId
     * @return
     */
    public ArrayList<Course> getBySchedule(int scheduleId);

}