package by.epam.rolesOrganisationsUsersController.bean;

import java.io.Serializable;

public class Organisation implements FactoryType, Serializable {

    private static final long serialVersionUID = 2050812806625683000L;

    public Organisation() {
    }

    public Organisation(int id, String name, int roleID, String role, boolean isBlocked, String info) {
        this.id = id;
        this.name = name;
        this.roleID = roleID;
        this.role = role;
        this.isBlocked = isBlocked;
        this.info = info;
    }

    private int id;
    private String name;
    private int roleID;
    private String role;
    private boolean isBlocked;
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organisation that = (Organisation) o;

        if (id != that.id) return false;
        if (roleID != that.roleID) return false;
        if (isBlocked != that.isBlocked) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return info != null ? info.equals(that.info) : that.info == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + roleID;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Organisation{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", roleID=").append(roleID);
        sb.append(", role='").append(role).append('\'');
        sb.append(", isBlocked=").append(isBlocked);
        sb.append(", info='").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
