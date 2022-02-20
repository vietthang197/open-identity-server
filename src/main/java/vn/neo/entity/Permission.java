package vn.neo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author thanglv on 2/20/2022
 * @project open-identity-server
 */
@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    @Id
    private Long permissionId;
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    private Date createdDate;

    public Permission() {
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
