package vn.neo.services.impl;

import org.springframework.stereotype.Service;
import vn.neo.dto.UserInfoDto;
import vn.neo.mapper.DtoMapper;
import vn.neo.repository.UserInfoRepository;
import vn.neo.services.UserInfoService;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final DtoMapper dtoMapper;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository, DtoMapper dtoMapper) {
        this.userInfoRepository = userInfoRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<UserInfoDto> findAll() {
        return dtoMapper.listUserInfoToDto(userInfoRepository.findAll());
    }
}
