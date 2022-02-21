package vn.neo.mapper;

import org.mapstruct.Mapper;
import vn.neo.dto.UserInfoDto;
import vn.neo.entity.UserInfo;

import java.util.List;

/**
 * @author thanglv on 2/21/2022
 * @project open-identity-server
 */
@Mapper(componentModel = "spring")
public interface DtoMapper {
    UserInfoDto userInfoToDto(UserInfo userInfo);
    List<UserInfoDto> listUserInfoToDto(List<UserInfo> userInfoList);
}
