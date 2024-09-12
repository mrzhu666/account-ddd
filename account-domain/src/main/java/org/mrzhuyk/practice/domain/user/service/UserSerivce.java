package org.mrzhuyk.practice.domain.user.service;

import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.model.UserInfo;
import org.mrzhuyk.practice.domain.user.param.UserRegisterParam;
import org.mrzhuyk.practice.dto.command.UserLoginCmd;
import org.mrzhuyk.practice.vo.UserInfoVO;

public interface UserSerivce {
    
    /**
     * 查询用户信息
     *
     * @param userId 用户ID
     * @return 用户实体
     */
    UserEntity query(Long userId);
    
    /**
     * 用户注册
     *
     * @param userRegisterParam 用户注册Param
     * @return true-注册成功; false-注册失败
     */
    Boolean register(UserRegisterParam userRegisterParam);
    
    
    /**
     * 用户登录
     *
     * @param userLoginCmd 用户登录参数
     * @return 用户实体
     */
    UserEntity login(UserLoginCmd userLoginCmd);
    
    
    /**
     * 当前登录用户实体
     *
     * @return 用户登录实体
     */
    UserEntity status();
}
