package vn.neo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.neo.dto.BasicResponseDto;
import vn.neo.dto.LoginResponseDto;
import vn.neo.jwt.TokenProvider;
import vn.neo.services.vm.LoginVM;
import vn.neo.utils.ErrorCode;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author thanglv on 2/21/2022
 * @project open-identity-server
 */
@RestController
@RequestMapping("/")
public class UserJWTController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public BasicResponseDto login(@RequestBody @Valid LoginVM loginVM, HttpServletResponse httpServletResponse) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        LoginResponseDto res = new LoginResponseDto(token);
        httpServletResponse.setHeader("Authorization", "Bearer " + token);
        return new BasicResponseDto(ErrorCode.SUCC_200, "Đăng nhập thành công", res);
    }
}
