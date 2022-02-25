package vn.neo.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.neo.entity.UserInfo;
import vn.neo.obj.Users;
import vn.neo.repository.UserInfoRepository;

import java.util.Collections;
import java.util.Optional;

/**
 * @author thanglv on 2/21/2022
 * @project open-identity-server
 */
@Service
public class UsersDetailServiceImpl implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;

    public UsersDetailServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfoOpt =  userInfoRepository.findByUsername(username);
        if (!userInfoOpt.isPresent())
            throw new UsernameNotFoundException("User doesn't exists");
        UserInfo userInfo = userInfoOpt.get();
        return new Users(userInfo.getUsername(), userInfo.getPassword(), Collections.emptyList());
    }
}
