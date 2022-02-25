package vn.neo.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.neo.entity.UserInfo;
import vn.neo.repository.UserInfoRepository;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Optional;

@Configuration
public class InitData {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public InitData(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initUser() {
        Optional<UserInfo> users = userInfoRepository.findByUsername("admin");
        if (!users.isPresent()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("admin");
            userInfo.setPassword(passwordEncoder.encode("admin"));
            userInfo.setFullName("administrator");
            userInfo.setEmail("levietthang1997@gmail.com");
            userInfo.setPhone("0981576404");
            userInfo.setCreateDate(new Date());
            userInfoRepository.save(userInfo);
        }
    }
}
