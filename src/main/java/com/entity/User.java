package com.entity;

/**
 * Data transfer object for major
 * Based on table
 * CREATE TABLE Users (
 *      userId VARCHAR(36) NOT NULL PRIMARY KEY,
 *      email VARCHAR(100) NOT NULL UNIQUE,
 *      firstName VARCHAR(100) NOT NULL,
 *      lastName VARCHAR(100) NOT NULL,
 *      password VARCHAR(44) NOT NULL,
 *      roleId INT NOT NULL,
 *      majorId INT NOT NULL,
 *      CONSTRAINT FOREIGN KEY (roleId) REFERENCES Roles(roleId) ON UPDATE CASCADE ON DELETE CASCADE,
 *      CONSTRAINT FOREIGN KEY (majorId) REFERENCES Majors(majorId) ON UPDATE CASCADE ON DELETE CASCADE
 * );
 */

public class User {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int roleId;
    private int majorId;

    public User() {
    }

    public User(String userId, String email, String firstName, String lastName, String password, int roleId, int majorId) {
        super();
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roleId = roleId;
        this.majorId = majorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId=roleId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + password + ", roleId=" + roleId + ", major=" + majorId + "]";
    }

}
