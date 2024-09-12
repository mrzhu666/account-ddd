package org.mrzhuyk.practice.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
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
    public UserEntity getByUserId(Long userId) {
        UserInfoDO userInfoDO = userInfoMapper.selectById(userId);
        if (userInfoDO == null) return null;
        UserAuthMobileDO userAuthMobileDO = userAuthMobileMapper.selectById(userId);
        UserAuthEmailDO userAuthEmailDO = userAuthEmailMapper.selectById(userId);
        
        // 完全没有认证方式，一般不会发生，因为注册要求必须有认证方式
        if (ObjectUtils.isEmpty(userAuthMobileDO) && ObjectUtils.isEmpty(userAuthEmailDO)) return null;
        
        if (ObjectUtils.isNotEmpty(userAuthEmailDO)) return UserConvertor.userEntity(userInfoDO, userAuthEmailDO);
        else return UserConvertor.userEntity(userInfoDO, userAuthMobileDO);
        
    }
    
    @Override
    public UserEntity getByEmailOrMobile(String email, String mobile) {
        if (ObjectUtils.isEmpty(email) && ObjectUtils.isEmpty(mobile)) {
            return null;
        }
        UserEntity userEntity;
        if (ObjectUtils.isNotEmpty(email)) {
            // 封装条件
            LambdaQueryWrapper<UserAuthEmailDO> userAuthEmailDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userAuthEmailDOLambdaQueryWrapper.eq(UserAuthEmailDO::getEmail, email);
            UserAuthEmailDO userAuthEmailDO = userAuthEmailMapper.selectOne(userAuthEmailDOLambdaQueryWrapper);
            // 查询结果为空
            if (userAuthEmailDO == null) return null;
            
            Long userId = userAuthEmailDO.getUserId();
            UserInfoDO userInfoDO = userInfoMapper.selectById(userId);
            userEntity = UserConvertor.userEntity(userInfoDO,userAuthEmailDO);
            
        } else {
            // 封装查询条件
            LambdaQueryWrapper<UserAuthMobileDO> userAuthMobileDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userAuthMobileDOLambdaQueryWrapper.eq(UserAuthMobileDO::getMobile, mobile);
            UserAuthMobileDO userAuthMobileDO = userAuthMobileMapper.selectOne(userAuthMobileDOLambdaQueryWrapper);
            // 查询结果为空
            if (userAuthMobileDO == null) return null;
            
            Long userId = userAuthMobileDO.getUserId();
            UserInfoDO userInfoDO = userInfoMapper.selectById(userId);
            userEntity = UserConvertor.userEntity(userInfoDO,userAuthMobileDO);
        }
        
        return userEntity;
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
