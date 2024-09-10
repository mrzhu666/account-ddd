package org.mrzhuyk.practice.repository.impl;

import jakarta.annotation.Resource;
import org.mrzhuyk.practice.convertor.UserConvertor;
import org.mrzhuyk.practice.dataobject.UserInfoDO;
import org.mrzhuyk.practice.domain.user.repository.UserRepository;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.exception.BizException;
import org.mrzhuyk.practice.exception.ErrorEnum;
import org.mrzhuyk.practice.mapper.UserInfoMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    
    @Resource
    UserInfoMapper userInfoMapper;
    
    
    @Override
    public UserEntity findByUserId(Long userId) {
        UserInfoDO userInfo = userInfoMapper.selectById(userId);
        
        if(userInfo == null) {
            throw new BizException(ErrorEnum.NOT_FOUND_ERROR,"请求的用户ID不存在");
        }
        
        return UserConvertor.convert(userInfo);
    }
}
