package com.entity;

import java.beans.Transient;
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

/**
 * Data transfer object for major
 * Based on table
 * CREATE TABLE Roles(
 *     roleId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *     roleName VARCHAR(100) NOT NULL
 * );
 */

@Entity
@Table(name="Roles")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="roleId", unique = true, nullable=false)
    private int roleId;

    @Column(name="roleName", nullable=false)
    private String roleName;

    @JsonIgnore
    @OneToMany(mappedBy="role", cascade=CascadeType.ALL)
    private Set<User> users;

    /**
     * Class Constructor
     */
    public Role() {}

    public Role(int roleId, String roleName) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
    }

    /**
     * Get roleId
     *
     * @return int roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Set RoleId
     *
     * @param int roleId
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Get role name
     *
     * @return String rolename
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Set rolename
     *
     * @param String roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Total count of user with assigned role
     * @return Integer count
     */
    public int totalCount() {
        return users.size();
    }

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
    }

}
