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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    private Date createDate;
    @ManyToMany
    @JoinTable(name = "role_api_access", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    }, inverseJoinColumns = {
            @JoinColumn(name = "api_id", referencedColumnName = "apiId")
    })
    private Set<ApiInfo> apiInfoList = new HashSet<>();

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

    public Set<ApiInfo> getApiInfoList() {
        return apiInfoList;
    }

    public void setApiInfoList(Set<ApiInfo> apiInfoList) {
        this.apiInfoList = apiInfoList;
    }
}
