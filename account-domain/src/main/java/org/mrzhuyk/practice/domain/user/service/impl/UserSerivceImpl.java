package org.mrzhuyk.practice.domain.user.service.impl;


import jakarta.annotation.Resource;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.param.UserRegisterParam;
import org.mrzhuyk.practice.domain.user.repository.UserRepository;
import org.mrzhuyk.practice.domain.user.service.UserSerivce;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.stereotype.Service;


@Service
public class UserSerivceImpl implements UserSerivce {
    

    @Resource
    private UserRepository userRepository;
    
    @Override
    public UserEntity query(Long userId) {
        return userRepository.findByUserId(userId);
    }
    
    @Override
    public Long register(UserRegisterParam userRegisterParam) {
        return 0L;
    }
}
