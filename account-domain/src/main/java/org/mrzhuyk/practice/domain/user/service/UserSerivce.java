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
    public UserEntity query(Long userId);
    
    public Long register(UserRegisterParam userRegisterParam);
}
