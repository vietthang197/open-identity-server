package vn.neo.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
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
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserJWTController(UserInfoService userInfoService, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userInfoService = userInfoService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public BasicResponseDto login(@RequestBody @Valid LoginVM loginVM) {
        UsernamePasswordAuthenticationToken aupt = new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(aupt);
        return new BasicResponseDto();
    }
}
