package vn.neo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.neo.dto.UserInfoDto;
import vn.neo.services.UserInfoService;

import java.util.List;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/")
    public List<UserInfoDto> getAllUserInfo() {
        return userInfoService.findAll();
    }
}
