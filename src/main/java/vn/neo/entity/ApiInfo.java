package vn.neo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "api_info")
public class ApiInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long apiId;
    @Column(length = 500)
    private String name;
    @Column(nullable = false, length = 500)
    private String path;
    @Column(nullable = false, length = 50)
    private String method;
    @ManyToOne(fetch = FetchType.LAZY)
    private ApiGroup apiGroup;

    public ApiInfo() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ApiGroup getApiGroup() {
        return apiGroup;
    }

    public void setApiGroup(ApiGroup apiGroup) {
        this.apiGroup = apiGroup;
    }
}
