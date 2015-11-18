package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import com.util.security.Crypto;

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

@Entity
@Table(name="Users")
public class User implements Serializable {

    @Id
    @NotNull
    @Type(type="uuid-char")
    @Column(name = "userId", unique = true, nullable=false, updatable = false)
    private UUID userId;

    @Column(name="email", unique=true, nullable=false, updatable = false)
    private String email;

    @Column(name="firstName", nullable=false)
    private String firstName;

    @Column(name="lastName", nullable=false)
    private String lastName;

    @Column(name="password", nullable=false)
    private String encryptedPassword;

    //select * from Users INNER JOIN Roles where Roles.roleId=Users.roleId;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "roleId", referencedColumnName="roleId", nullable = false)
    private Role role;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "majorId", referencedColumnName="majorId", nullable = false)
    private Major major;

    @JsonIgnore
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private Set<UserCourse> usercourses;

    /**
     * Class Constructor
     */
    public User() {
    }

    public User(UUID userId, String email, String password, String firstName, String lastName) {
        super();
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.encryptedPassword = Crypto.encrypt(password); //password set always need to be encrypted
    }

    /**
     * Get user UUID
     * @return uuid userId
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Set user UUID
     * @param userId
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Get user-email
     * @return String userEmail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set userEmail
     * @param String email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get user firstname
     * @return String firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set user firstname
     * @param String firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get user last name
     * @return String lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set user lastname
     * @param String lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get user password
     * @return String password
     */
    @JsonIgnore
    public String getPassword() {
        String password = "";
        try {
            password = Crypto.decrypt(encryptedPassword);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * Set user password
     * @param password
     */
    @JsonIgnore
    public void setPassword(String password) {
        try {
            this.encryptedPassword = Crypto.encrypt(password);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get user role
     * @return
     */
    public Role getRole() {
        return role;
    }

    /**
     * Set user role
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Get user major object
     * @return Major object
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Set user major
     * @param major
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * Get usercourses
     *
     * @return Set of usercourses
     */
    public Set<UserCourse> getUsercourses() {
        return usercourses;
    }

    /**
     * Set usercourses
     * @param Set usercourses
     */
    public void setUsercourses(Set<UserCourse> usercourses) {
        this.usercourses = usercourses;
    }

    /**
     * Return number of courses that user has registered
     * @return integer number of registered courses
     */
    public int getCoursesCount() {
        return usercourses.size();
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + getPassword() + ", roleName=" + role.getRoleName()
                + ", majorId=" + major.getMajorId() + "]";
    }

}
