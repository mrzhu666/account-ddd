package org.mrzhuyk.practice.domain.user.model.copyor;

import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.model.UserInfo;
import org.mrzhuyk.practice.domain.user.param.UserRegisterParam;

import java.util.Date;

public class UserCopyor {
    
    public static UserInfo copy(UserRegisterParam userRegisterParam) {
        UserInfo userInfo = new UserInfo();
        userInfo.setRealName(userRegisterParam.getRealName());
        userInfo.setNickName(userRegisterParam.getNickName());
        userInfo.setMobile(userRegisterParam.getMobile());
        userInfo.setEmail(userRegisterParam.getEmail());
        userInfo.setRegistryTime(new Date());
        return userInfo;
    }
    
}
