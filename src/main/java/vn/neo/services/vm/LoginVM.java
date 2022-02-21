package vn.neo.services.vm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author thanglv on 2/21/2022
 * @project open-identity-server
 */
public class LoginVM implements Serializable {
    @NotBlank(message = "Tên đăng nhập  không hợp lệ")
    @Size(max = 100, message = "Tên đăng nhập không hợp lệ")
    private String username;
    @NotBlank(message = "Mật khẩu không hợp lệ")
    @Size(max = 100, message = "Mật khẩu không hợp lệ")
    private String password;

    public LoginVM() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
