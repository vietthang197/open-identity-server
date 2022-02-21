package vn.neo.dto;

import java.io.Serializable;

/**
 * @author thanglv on 2/21/2022
 * @project open-identity-server
 */
public class BasicResponseDto implements Serializable {
    private int status;
    private String message;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
