package org.mrzhuyk.practice.domain.user.service;

import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.param.UserRegisterParam;
import org.mrzhuyk.practice.vo.UserInfoVO;

public interface UserSerivce {
    
    /**
     * 查询用户信息
     * @param userId 用户ID
     * @return 用户实体
     */
    UserEntity query(Long userId);
    
    /**
     * 用户注册
     * @param userRegisterParam 用户注册Param
     * @return true-注册成功; false-注册失败
     */
    Boolean register(UserRegisterParam userRegisterParam);
}
