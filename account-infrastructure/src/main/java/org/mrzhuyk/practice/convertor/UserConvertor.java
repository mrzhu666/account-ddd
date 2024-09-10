package org.mrzhuyk.practice.convertor;

import org.mrzhuyk.practice.dataobject.UserInfoDO;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.vo.UserInfoVO;

public class UserConvertor {
    public static UserEntity convert(UserInfoDO userInfoDO) {
        UserEntity userEntity = new UserEntity();
        userEntity.getUserInfo().setUserId(userInfoDO.getUserId());
        userEntity.getUserInfo().setNickName(userInfoDO.getNickName());
        userEntity.getUserInfo().setSalt(userInfoDO.getSalt());
        userEntity.getUserInfo().setMobile(userInfoDO.getMobile());
        userEntity.getUserInfo().setEmail(userInfoDO.getEmail());
        userEntity.getUserInfo().setRegistryTime(userInfoDO.getRegistryTime());
        return userEntity;
    
    }
}
