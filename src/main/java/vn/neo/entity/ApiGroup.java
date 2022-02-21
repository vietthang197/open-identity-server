package vn.neo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author thanglv on 2/21/2022
 * @project open-identity-server
 */
@Entity
@Table(name = "api_group")
public class ApiGroup implements Serializable {
    @Id
    private Long apiId;
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<ApiInfo> apiInfoList;

    public ApiGroup() {
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ApiInfo> getApiInfoList() {
        return apiInfoList;
    }

    public void setApiInfoList(Set<ApiInfo> apiInfoList) {
        this.apiInfoList = apiInfoList;
    }
}
