package org.mrzhuyk.practice.convertor;

import org.mrzhuyk.practice.dataobject.UserAuthEmailDO;
import org.mrzhuyk.practice.dataobject.UserAuthMobileDO;
import org.mrzhuyk.practice.dataobject.UserInfoDO;
import org.mrzhuyk.practice.domain.user.model.UserEncryptPassword;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.model.UserInfo;

public class UserConvertor {
    public static UserEntity userEntity(UserInfoDO userInfoDO,UserAuthMobileDO userAuthMobileDO) {
        UserEntity userEntity = new UserEntity();
        UserInfo userInfo = new UserInfo();
        userEntity.setUserInfo(userInfo);
        userEntity.getUserInfo().setUserId(userInfoDO.getUserId());
        userEntity.getUserInfo().setNickName(userInfoDO.getNickName());
        userEntity.getUserInfo().setSalt(userInfoDO.getSalt());
        userEntity.getUserInfo().setMobile(userInfoDO.getMobile());
        userEntity.getUserInfo().setEmail(userInfoDO.getEmail());
        userEntity.getUserInfo().setRegistryTime(userInfoDO.getRegistryTime());
        UserEncryptPassword userEncryptPassword = new UserEncryptPassword(userAuthMobileDO.getPassword());
        userEntity.setUserEncryptPassword(userEncryptPassword);
        return userEntity;
    }
    
    public static UserEntity userEntity(UserInfoDO userInfoDO,UserAuthEmailDO userAuthEmailDO) {
        UserEntity userEntity = new UserEntity();
        UserInfo userInfo = new UserInfo();
        userEntity.setUserInfo(userInfo);
        userEntity.getUserInfo().setUserId(userInfoDO.getUserId());
        userEntity.getUserInfo().setNickName(userInfoDO.getNickName());
        userEntity.getUserInfo().setSalt(userInfoDO.getSalt());
        userEntity.getUserInfo().setMobile(userInfoDO.getMobile());
        userEntity.getUserInfo().setEmail(userInfoDO.getEmail());
        userEntity.getUserInfo().setRegistryTime(userInfoDO.getRegistryTime());
        UserEncryptPassword userEncryptPassword = new UserEncryptPassword(userAuthEmailDO.getPassword());
        userEntity.setUserEncryptPassword(userEncryptPassword);
        return userEntity;
    }
    
    
    
    public static UserInfoDO userInfoDO(UserInfo userInfo) {
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserId(userInfo.getUserId());
        userInfoDO.setRealName(userInfo.getRealName());
        userInfoDO.setNickName(userInfo.getNickName());
        userInfoDO.setMobile(userInfo.getMobile());
        userInfoDO.setEmail(userInfo.getEmail());
        userInfoDO.setSalt(userInfo.getSalt());
        userInfoDO.setRegistryTime(userInfo.getRegistryTime());
        return userInfoDO;
    }
    
    public static UserAuthEmailDO userAuthEmail(UserEntity userEntity) {
        UserAuthEmailDO userAuthEmailDO = new UserAuthEmailDO();
        userAuthEmailDO.setUserId(userEntity.getUserInfo().getUserId());
        userAuthEmailDO.setEmail(userEntity.getUserInfo().getEmail());
        userAuthEmailDO.setPassword(userEntity.getEncryptedPassword());
        return userAuthEmailDO;
    }
    
    public static UserAuthMobileDO userAuthMobile(UserEntity userEntity) {
        UserAuthMobileDO userAuthMobileDO = new UserAuthMobileDO();
        userAuthMobileDO.setUserId(userEntity.getUserInfo().getUserId());
        userAuthMobileDO.setMobile(userEntity.getUserInfo().getMobile());
        userAuthMobileDO.setPassword(userEntity.getEncryptedPassword());
        return userAuthMobileDO;
    }
    
}
