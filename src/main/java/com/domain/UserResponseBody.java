package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * UserResponseBody object contains response after autherization
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID userId;

    /**
     * Class constructor
     */
    public UserResponseBody() {}

    /**
     * Constructor
     * @param userId
     */
    public UserResponseBody(UUID userId) {
        this.userId = userId;
    }

    /**
     * Get userId
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
        this.userId  = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponseBody)) return false;
        UserResponseBody that = (UserResponseBody) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "UserResponseBody{" +
                ", userId='" + userId.toString() +
                '}';
    }

}
