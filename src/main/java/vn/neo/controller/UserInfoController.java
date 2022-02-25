package vn.neo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.neo.dto.BasicResponseDto;
import vn.neo.services.UserInfoService;
import vn.neo.utils.ErrorCode;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasPermission('#${httpServletRequest}', 'read')")
    public BasicResponseDto getUserInfomation(HttpServletRequest httpServletRequest) {
        return userInfoService.getUserInfomation();
    }

    @GetMapping
    @PreAuthorize("hasPermission('#{httpServletRequest}', 'read')")
    public BasicResponseDto getAllUserInfo() {
        return new BasicResponseDto(ErrorCode.SUCC_200, "Thành công", userInfoService.findAll());
    }
}
