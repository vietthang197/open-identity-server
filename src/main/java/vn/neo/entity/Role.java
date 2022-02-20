package vn.neo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author thanglv on 2/20/2022
 * @project open-identity-server
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    private Long roleId;
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    private Date createDate;
    @ManyToMany
    private Set<Permission> permissions = new HashSet<>();

    public Role() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
