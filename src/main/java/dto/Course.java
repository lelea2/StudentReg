package dto;

/**
 *  Data transfer object for courses
 *  Based on table
 *  CREATE TABLE Courses(
 *       courseId VARCHAR(36) NOT NULL PRIMARY KEY,
 *       courseName VARCHAR(100) NOT NULL UNIQUE,
 *       courseNumber INT NOT NULL UNIQUE,
 *       majorId INT NOT NULL,
 *       scheduleId INT NOT NULL,
 *       stateId  VARCHAR(100),
 *       professor VARCHAR(100) DEFAULT 'STAFF',
 *       description VARCHAR(255),
 *       location VARCHAR(100) DEFAULT 'TBA',
 *       CONSTRAINT FOREIGN KEY(majorId) REFERENCES Majors(majorId) ON UPDATE CASCADE ON DELETE CASCADE,
 *       CONSTRAINT FOREIGN KEY(scheduleId) REFERENCES Schedules(scheduleId) ON UPDATE CASCADE ON DELETE CASCADE
 *   );
 */
public class Course {
    private String courseId;
    private String courseName;
    private int courseNumber;
    private int majorId;
    private int scheduleId;
    private String stateId;
    private String professor;
    private String description;
    private String location;

    public Course() {}

    public Course(String courseId, String courseName, int courseNumber, int majorId, int scheduleId, String stateId, String professor, String description, String location) {
        super();
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.majorId = majorId;
        this.scheduleId = scheduleId;
        this.stateId = stateId;
        this.professor = professor;
        this.description = description;
        this.location = location;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this. description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseNumber=" + courseNumber
                + ", majorId=" + majorId + ", scheduleid=" + scheduleId + ", stateId=" + stateId
                + ", professor=" + professor + ", description=" + description + ", location=" + location + "]";
    }

}
