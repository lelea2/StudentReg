package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

/**
 * UserRequest object contains user body request for authentication
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestBody implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;;
    private String userPassword;

    /**
     * Get user name
     * @return String userName (which is email)
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set user name
     * @param String userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRequestBody)) return false;
        UserRequestBody that = (UserRequestBody) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(userPassword, that.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPassword);
    }

    @Override
    public String toString() {
        return "UserRequestBody{" +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
