package vn.neo.services;

import vn.neo.dto.UserInfoDto;

import java.util.List;

public interface UserInfoService {
    List<UserInfoDto> findAll();
}
