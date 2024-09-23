package org.mrzhuyk.practice.convertor;

import org.mrzhuyk.practice.domain.user.model.LoginInfo;
import org.mrzhuyk.practice.po.UserAuthEmailPO;
import org.mrzhuyk.practice.po.UserAuthMobilePO;
import org.mrzhuyk.practice.po.UserInfoPO;
import org.mrzhuyk.practice.domain.user.model.UserEncryptPassword;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.model.UserInfo;
import org.mrzhuyk.practice.po.UserLoginLogPO;

public class UserConvertor {
    public static UserEntity userEntity(UserInfoPO userInfoPO, UserAuthMobilePO userAuthMobilePO) {
        UserEntity userEntity = new UserEntity();
        UserInfo userInfo = new UserInfo();
        userEntity.setUserInfo(userInfo);
        userEntity.getUserInfo().setUserId(userInfoPO.getUserId());
        userEntity.getUserInfo().setNickName(userInfoPO.getNickName());
        userEntity.getUserInfo().setSalt(userInfoPO.getSalt());
        userEntity.getUserInfo().setMobile(userInfoPO.getMobile());
        userEntity.getUserInfo().setEmail(userInfoPO.getEmail());
        userEntity.getUserInfo().setRegistryTime(userInfoPO.getRegistryTime());
        UserEncryptPassword userEncryptPassword = new UserEncryptPassword(userAuthMobilePO.getPassword());
        userEntity.setUserEncryptPassword(userEncryptPassword);
        return userEntity;
    }
    
    public static UserEntity userEntity(UserInfoPO userInfoPO, UserAuthEmailPO userAuthEmailPO) {
        UserEntity userEntity = new UserEntity();
        UserInfo userInfo = new UserInfo();
        userEntity.setUserInfo(userInfo);
        userEntity.getUserInfo().setUserId(userInfoPO.getUserId());
        userEntity.getUserInfo().setNickName(userInfoPO.getNickName());
        userEntity.getUserInfo().setSalt(userInfoPO.getSalt());
        userEntity.getUserInfo().setMobile(userInfoPO.getMobile());
        userEntity.getUserInfo().setEmail(userInfoPO.getEmail());
        userEntity.getUserInfo().setRegistryTime(userInfoPO.getRegistryTime());
        UserEncryptPassword userEncryptPassword = new UserEncryptPassword(userAuthEmailPO.getPassword());
        userEntity.setUserEncryptPassword(userEncryptPassword);
        return userEntity;
    }
    
    
    public static UserInfoPO userInfoDO(UserInfo userInfo) {
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setUserId(userInfo.getUserId());
        userInfoPO.setRealName(userInfo.getRealName());
        userInfoPO.setNickName(userInfo.getNickName());
        userInfoPO.setMobile(userInfo.getMobile());
        userInfoPO.setEmail(userInfo.getEmail());
        userInfoPO.setSalt(userInfo.getSalt());
        userInfoPO.setRegistryTime(userInfo.getRegistryTime());
        return userInfoPO;
    }
    
    public static UserAuthEmailPO userAuthEmail(UserEntity userEntity) {
        UserAuthEmailPO userAuthEmailPO = new UserAuthEmailPO();
        userAuthEmailPO.setUserId(userEntity.getUserInfo().getUserId());
        userAuthEmailPO.setEmail(userEntity.getUserInfo().getEmail());
        userAuthEmailPO.setPassword(userEntity.getEncryptedPassword());
        return userAuthEmailPO;
    }
    
    public static UserAuthMobilePO userAuthMobile(UserEntity userEntity) {
        UserAuthMobilePO userAuthMobilePO = new UserAuthMobilePO();
        userAuthMobilePO.setUserId(userEntity.getUserInfo().getUserId());
        userAuthMobilePO.setMobile(userEntity.getUserInfo().getMobile());
        userAuthMobilePO.setPassword(userEntity.getEncryptedPassword());
        return userAuthMobilePO;
    }
    
    public static UserLoginLogPO userLoginLog(LoginInfo loginInfo) {
        UserLoginLogPO userLoginLogPO = new UserLoginLogPO();
        userLoginLogPO.setUserId(loginInfo.getUserId());
        userLoginLogPO.setLoginType(loginInfo.getLoginType());
        userLoginLogPO.setDevice(loginInfo.getDevice());
        userLoginLogPO.setLoginTime(loginInfo.getLoginTime());
        userLoginLogPO.setIp(loginInfo.getIp());
        return userLoginLogPO;
        
    }
    
}
