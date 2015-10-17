package com.entity;


import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;

/**
 * Data transfer object for major
 * Based on table
 * CREATE TABLE Roles(
 *     roleId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *     roleName VARCHAR(100) NOT NULL UNIQUE
 * );
 */

public class Role {

    @Id
    @Column(name="roleId", unique = true, nullable=false)
    private int roleId;

    @Column(name="roleName")
    private String roleName;

    public Role() {}

    public Role(int roleId, String roleName) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
    }

}
