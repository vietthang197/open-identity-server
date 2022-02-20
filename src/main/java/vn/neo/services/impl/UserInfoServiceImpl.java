package vn.neo.services.impl;

import org.springframework.stereotype.Service;
import vn.neo.dto.UserInfoDto;
import vn.neo.repository.UserInfoRepository;
import vn.neo.services.UserInfoService;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public List<UserInfoDto> findAll() {
        return userInfoRepository.findAll();
    }
}
