package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

/**
 * UserRegitser object contains user body request for signup
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterBody implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userEmail;
    private String userPassword;
    private String firstName;
    private String lastName;
    private int roleId;
    private int majorId;

    /**
     * Get user email
     * @return String userEmail (which is email)
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Set user email
     * @param String userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Get user password
     * @return String password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Set user password
     * @param String userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Get firstname
     * @return String firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set firstname
     * @param String firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get lastname
     * @return String lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set lastname
     * @param String lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get roleId
     * @return int roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Set roleId
     * @param int roleId
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Set majorId
     * @return int majorId
     */
    public int getMajorId() {
        return majorId;
    }

    /**
     * Set majorId
     * @param int majorId
     */
    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRegisterBody)) return false;
        UserRegisterBody that = (UserRegisterBody) o;
        return Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userPassword, that.userPassword) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(majorId, that.majorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, userPassword, firstName, lastName, roleId, majorId);
    }

    @Override
    public String toString() {
        return "UserRegisterBody{" +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roleId='" + roleId + '\'' +
                ", majorId='" + majorId + '\'' +
                '}';
    }
}
