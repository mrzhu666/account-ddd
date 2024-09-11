package org.mrzhuyk.practice.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.mrzhuyk.practice.convertor.UserConvertor;
import org.mrzhuyk.practice.dataobject.UserAuthEmailDO;
import org.mrzhuyk.practice.dataobject.UserAuthMobileDO;
import org.mrzhuyk.practice.dataobject.UserInfoDO;
import org.mrzhuyk.practice.domain.user.repository.UserRepository;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.exception.BizException;
import org.mrzhuyk.practice.exception.ErrorEnum;
import org.mrzhuyk.practice.mapper.UserAuthEmailMapper;
import org.mrzhuyk.practice.mapper.UserAuthMobileMapper;
import org.mrzhuyk.practice.mapper.UserInfoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    
    @Resource
    UserInfoMapper userInfoMapper;
    
    @Resource
    UserAuthEmailMapper userAuthEmailMapper;
    
    @Resource
    UserAuthMobileMapper userAuthMobileMapper;
    
    @Override
    public UserEntity findByUserId(Long userId) {
        UserInfoDO userInfo = userInfoMapper.selectById(userId);
        
        if (userInfo == null) {
            throw new BizException(ErrorEnum.NOT_FOUND_ERROR, "请求的用户ID不存在");
        }
        
        return UserConvertor.userEntity(userInfo);
    }
    
    @Override
    public long insertUserInfo(UserEntity userEntity) {
        UserInfoDO userInfoDO = UserConvertor.userInfoDO(userEntity.getUserInfo());
        userInfoMapper.insert(userInfoDO);
        return userInfoDO.getUserId();
    }
    
    @Override
    public int insertUserAuthEmail(UserEntity userEntity) {
        UserAuthEmailDO userAuthEmailDO = UserConvertor.userAuthEmail(userEntity);
        return userAuthEmailMapper.insert(userAuthEmailDO);
    }
    
    @Override
    public int insertUserAuthMobile(UserEntity userEntity) {
        UserAuthMobileDO userAuthMobileDO = UserConvertor.userAuthMobile(userEntity);
        return userAuthMobileMapper.insert(userAuthMobileDO);
    }
    
    
    @Override
    public Boolean checkEmail(String email) {
        LambdaQueryWrapper<UserInfoDO> userInfoDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoDOLambdaQueryWrapper.eq(UserInfoDO::getEmail, email);
        Long count = userInfoMapper.selectCount(userInfoDOLambdaQueryWrapper);
        return count > 0;
    }
    
    @Override
    public Boolean checkMobile(String mobile) {
        LambdaQueryWrapper<UserInfoDO> userInfoDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoDOLambdaQueryWrapper.eq(UserInfoDO::getMobile, mobile);
        Long count = userInfoMapper.selectCount(userInfoDOLambdaQueryWrapper);
        return count > 0;
    }
    
    @Override
    public Boolean checkNickName(String nickName) {
        LambdaQueryWrapper<UserInfoDO> userInfoDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoDOLambdaQueryWrapper.eq(UserInfoDO::getNickName, nickName);
        Long count = userInfoMapper.selectCount(userInfoDOLambdaQueryWrapper);
        return count > 0;
    }
}
