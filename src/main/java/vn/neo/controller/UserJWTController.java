package vn.neo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.neo.dto.BasicResponseDto;
import vn.neo.services.UserInfoService;
import vn.neo.services.vm.LoginVM;

import javax.validation.Valid;

/**
 * @author thanglv on 2/21/2022
 * @project open-identity-server
 */
@RestController
@RequestMapping("/")
public class UserJWTController {

    private final UserInfoService userInfoService;

    public UserJWTController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/authenticate")
    public BasicResponseDto login(@RequestBody @Valid LoginVM loginVM) {
        return new BasicResponseDto();
    }
}
