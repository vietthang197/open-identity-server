package vn.neo.services;

import vn.neo.dto.BasicResponseDto;
import vn.neo.dto.UserInfoDto;

import java.util.List;

public interface UserInfoService {
    List<UserInfoDto> findAll();

    BasicResponseDto getUserInfomation();
}
