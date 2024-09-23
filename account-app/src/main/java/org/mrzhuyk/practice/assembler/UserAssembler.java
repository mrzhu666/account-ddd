package org.mrzhuyk.practice.assembler;
import java.util.Date;

import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.param.UserLoginParam;
import org.mrzhuyk.practice.domain.user.param.UserRegisterParam;
import org.mrzhuyk.practice.dto.command.UserLoginCmd;
import org.mrzhuyk.practice.dto.command.UserRegisterCmd;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.stereotype.Component;


public class UserAssembler {

    public static UserInfoVO assemble(UserEntity userEntity) {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(userEntity.getUserInfo().getUserId());
        userInfoVO.setNickName(userEntity.getUserInfo().getNickName());
        userInfoVO.setMobile(userEntity.getUserInfo().getMobile());
        userInfoVO.setEmail(userEntity.getUserInfo().getEmail());
        userInfoVO.setRegistryTime(userEntity.getUserInfo().getRegistryTime());
        return userInfoVO;
    }
    
    public static UserRegisterParam assemble(UserRegisterCmd userRegisterCmd) {
        UserRegisterParam userRegisterParam = new UserRegisterParam();
        userRegisterParam.setRealName(userRegisterCmd.getRealName());
        userRegisterParam.setNickName(userRegisterCmd.getNickName());
        userRegisterParam.setMobile(userRegisterCmd.getMobile());
        userRegisterParam.setEmail(userRegisterCmd.getEmail());
        userRegisterParam.setPassword(userRegisterCmd.getPassword());
        return userRegisterParam;
    }
    
    public static UserLoginParam userLoginParam(UserLoginCmd userLoginCmd) {
        UserLoginParam userLoginParam = new UserLoginParam();
        userLoginParam.setUserAccount(userLoginCmd.getUserAccount());
        userLoginParam.setPassword(userLoginCmd.getPassword());
        return userLoginParam;
        
    }
}
