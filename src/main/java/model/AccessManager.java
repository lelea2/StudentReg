package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.Access;
import dao.Database;
import dto.Course;

public class AccessManager {
    /**
     * Get courses
     * @return <ArrayList> of courses
     * @throws Exception
     */
    public ArrayList<Course> getCourses() throws Exception {
        ArrayList<Course> courseList = new ArrayList<Course>();
        Database db = new Database();
        Connection con = db.getConnection();
        Access access = new Access();
        courseList = access.getCourses(con);
        return courseList;
    }
}
