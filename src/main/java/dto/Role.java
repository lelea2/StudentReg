package dto;

/**
 * Data transfer object for major
 * Based on table
 * CREATE TABLE Roles(
 *     roleId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *     roleName VARCHAR(100) NOT NULL UNIQUE
 * );
 */

public class Role {
    private int roleId;
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
