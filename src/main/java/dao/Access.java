package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Course;

public class Access {
    /**
     * Get Courses in data bases
     * @param con
     * @return
     * @throws SQLException
     */
    public ArrayList<Course> getCourses(Connection con) throws SQLException {
        ArrayList<Course> courseList = new ArrayList<Course>();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Courses");
        ResultSet rs = stmt.executeQuery();
        try {
            while(rs.next()) {
                Course courseObj = new Course();
                courseObj.setCourseId(rs.getString("courseId"));
                courseObj.setCourseName(rs.getString("courseName"));
                courseObj.setCourseNumber(rs.getInt("courseNumber"));
                courseObj.setMajorId(rs.getInt("majorId"));
                courseObj.setScheduleId(rs.getInt("scheduleId"));
                courseObj.setStateId(rs.getString("stateId"));
                courseObj.setDescription(rs.getString("description"));
                courseObj.setProfessor(rs.getString("professor"));
                courseObj.setLocation(rs.getString("location"));
                courseList.add(courseObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }
}