package com.dao;

import java.util.ArrayList;
import com.entity.Course;

public interface CourseDAO {

    public Course getByNumber(int courseNumber);
    public Course getByName(String courseName);
    public ArrayList<Course> getByMajor(int majorId);;
}