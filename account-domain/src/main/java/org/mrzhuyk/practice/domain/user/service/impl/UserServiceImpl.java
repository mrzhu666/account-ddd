package org.mrzhuyk.practice.domain.user.service.impl;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.domain.user.enums.LoginType;
import org.mrzhuyk.practice.domain.user.model.LoginInfo;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.model.UserInfo;
import org.mrzhuyk.practice.domain.user.model.copyor.UserCopyor;
import org.mrzhuyk.practice.domain.user.param.UserRegisterParam;
import org.mrzhuyk.practice.domain.user.repository.UserRepository;
import org.mrzhuyk.practice.domain.user.service.UserService;
import org.mrzhuyk.practice.dto.command.UserLoginCmd;
import org.mrzhuyk.practice.exception.BizException;
import org.mrzhuyk.practice.exception.ErrorEnum;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    
    
    @Resource
    private UserRepository userRepository;
    
    @Override
    public UserEntity query(Long userId) {
        UserEntity byUserId = userRepository.getByUserId(userId);
        if (ObjectUtils.isEmpty(byUserId)) {
            throw new BizException(ErrorEnum.NOT_FOUND_ERROR, "请求的用户ID不存在");
        }
        
        return byUserId;
    }
    
    @Override
    public Boolean register(UserRegisterParam userRegisterParam) {
        if (StringUtils.isNotBlank(userRegisterParam.getEmail()) && userRepository.checkEmail(userRegisterParam.getEmail())) {
            throw new BizException(ErrorEnum.PARAMS_ERROR, "该邮箱已被注册");
        }
        if (StringUtils.isNotBlank(userRegisterParam.getMobile()) && userRepository.checkMobile(userRegisterParam.getMobile())) {
            throw new BizException(ErrorEnum.PARAMS_ERROR, "该手机号已被注册");
        }
        if (userRepository.checkNickName(userRegisterParam.getNickName())) {
            throw new BizException(ErrorEnum.PARAMS_ERROR, "该昵称已被占用");
        }
        
        UserInfo userInfo = UserCopyor.copy(userRegisterParam);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserInfo(userInfo);
        // 设置用户加密密码，并生成盐
        userEntity.setEncryptedPassword(userRegisterParam.getPassword());
        // 插入用户数据，用于注册
        long userId = userRepository.insertUserInfo(userEntity);
        userEntity.getUserInfo().setUserId(userId);
        if (StringUtils.isNotBlank(userEntity.getUserInfo().getEmail())) {
            // 插入邮箱认证
            userRepository.insertUserAuthEmail(userEntity);
        }
        
        if (StringUtils.isNotBlank(userEntity.getUserInfo().getMobile())) {
            // 插入手机认证
            userRepository.insertUserAuthMobile(userEntity);
        }
        
        return true;
    }
    
    @Override
    public UserEntity login(UserLoginCmd userLoginCmd) {

        String password = userLoginCmd.getPassword();
        if (StringUtils.isBlank(password)) {
            throw new BizException(ErrorEnum.PARAMS_ERROR, "请输入密码");
        }
        
        String userAccount = userLoginCmd.getUserAccount();
        // 判断登录账号的登录方式
        LoginType loginType = LoginType.getByUserAccount(userAccount);
        if (ObjectUtils.isEmpty(loginType)) {
            throw new BizException(ErrorEnum.PARAMS_ERROR, "用户不存在或密码错误");
        }
        
        UserEntity userEntity = null;
        if(loginType==LoginType.EMAIL) {
            // 邮箱登录
            userEntity = userRepository.getByEmail(userAccount);
        } else if (loginType==LoginType.MOBILE) {
            // 手机号登录
            userEntity = userRepository.getByMobile(userAccount);
        }
        
        if (ObjectUtils.isEmpty(userEntity)) {
            throw new BizException(ErrorEnum.PARAMS_ERROR, "用户不存在或密码错误");
        }
        if (!userEntity.isCorrectPassword(password)) {
            throw new BizException(ErrorEnum.PARAMS_ERROR, "用户不存在或密码错误");
        }
        
        LoginInfo loginInfo = LoginInfo.getLoginInfo(userEntity, loginType);
        userRepository.insertLoginInfo(loginInfo);
        
        // 设置用户态
        UserEntity.setSessionUserEntity(userEntity);
        return userEntity;
    }
    
    @Override
    public UserEntity status() {
        UserEntity userEntity = UserEntity.getSessionUserEntity();
        if (userEntity == null) {
            throw new BizException(ErrorEnum.NOT_LOGIN_ERROR);
        }
        return UserEntity.getSessionUserEntity();
    }
}
