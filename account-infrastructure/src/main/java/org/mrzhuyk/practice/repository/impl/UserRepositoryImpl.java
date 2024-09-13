package org.mrzhuyk.practice.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.mrzhuyk.practice.convertor.UserConvertor;
import org.mrzhuyk.practice.po.UserAuthEmailPO;
import org.mrzhuyk.practice.po.UserAuthMobilePO;
import org.mrzhuyk.practice.po.UserInfoPO;
import org.mrzhuyk.practice.domain.user.repository.UserRepository;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
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
        UserInfoPO userInfoPO = userInfoMapper.selectById(userId);
        if (userInfoPO == null) return null;
        UserAuthMobilePO userAuthMobilePO = userAuthMobileMapper.selectById(userId);
        UserAuthEmailPO userAuthEmailPO = userAuthEmailMapper.selectById(userId);
        
        // 完全没有认证方式，一般不会发生，因为注册要求必须有认证方式
        if (ObjectUtils.isEmpty(userAuthMobilePO) && ObjectUtils.isEmpty(userAuthEmailPO)) return null;
        
        if (ObjectUtils.isNotEmpty(userAuthEmailPO)) return UserConvertor.userEntity(userInfoPO, userAuthEmailPO);
        else return UserConvertor.userEntity(userInfoPO, userAuthMobilePO);
        
    }
    
    @Override
    public UserEntity getByEmailOrMobile(String email, String mobile) {
        if (ObjectUtils.isEmpty(email) && ObjectUtils.isEmpty(mobile)) {
            return null;
        }
        UserEntity userEntity;
        if (ObjectUtils.isNotEmpty(email)) {
            // 封装条件
            LambdaQueryWrapper<UserAuthEmailPO> userAuthEmailDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userAuthEmailDOLambdaQueryWrapper.eq(UserAuthEmailPO::getEmail, email);
            UserAuthEmailPO userAuthEmailPO = userAuthEmailMapper.selectOne(userAuthEmailDOLambdaQueryWrapper);
            // 查询结果为空
            if (userAuthEmailPO == null) return null;
            
            Long userId = userAuthEmailPO.getUserId();
            UserInfoPO userInfoPO = userInfoMapper.selectById(userId);
            userEntity = UserConvertor.userEntity(userInfoPO, userAuthEmailPO);
            
        } else {
            // 封装查询条件
            LambdaQueryWrapper<UserAuthMobilePO> userAuthMobileDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userAuthMobileDOLambdaQueryWrapper.eq(UserAuthMobilePO::getMobile, mobile);
            UserAuthMobilePO userAuthMobilePO = userAuthMobileMapper.selectOne(userAuthMobileDOLambdaQueryWrapper);
            // 查询结果为空
            if (userAuthMobilePO == null) return null;
            
            Long userId = userAuthMobilePO.getUserId();
            UserInfoPO userInfoPO = userInfoMapper.selectById(userId);
            userEntity = UserConvertor.userEntity(userInfoPO, userAuthMobilePO);
        }
        
        return userEntity;
    }
    
    @Override
    public long insertUserInfo(UserEntity userEntity) {
        UserInfoPO userInfoPO = UserConvertor.userInfoDO(userEntity.getUserInfo());
        userInfoMapper.insert(userInfoPO);
        return userInfoPO.getUserId();
    }
    
    @Override
    public int insertUserAuthEmail(UserEntity userEntity) {
        UserAuthEmailPO userAuthEmailPO = UserConvertor.userAuthEmail(userEntity);
        return userAuthEmailMapper.insert(userAuthEmailPO);
    }
    
    @Override
    public int insertUserAuthMobile(UserEntity userEntity) {
        UserAuthMobilePO userAuthMobilePO = UserConvertor.userAuthMobile(userEntity);
        return userAuthMobileMapper.insert(userAuthMobilePO);
    }
    
    
    @Override
    public Boolean checkEmail(String email) {
        LambdaQueryWrapper<UserInfoPO> userInfoDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoDOLambdaQueryWrapper.eq(UserInfoPO::getEmail, email);
        Long count = userInfoMapper.selectCount(userInfoDOLambdaQueryWrapper);
        return count > 0;
    }
    
    @Override
    public Boolean checkMobile(String mobile) {
        LambdaQueryWrapper<UserInfoPO> userInfoDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoDOLambdaQueryWrapper.eq(UserInfoPO::getMobile, mobile);
        Long count = userInfoMapper.selectCount(userInfoDOLambdaQueryWrapper);
        return count > 0;
    }
    
    @Override
    public Boolean checkNickName(String nickName) {
        LambdaQueryWrapper<UserInfoPO> userInfoDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoDOLambdaQueryWrapper.eq(UserInfoPO::getNickName, nickName);
        Long count = userInfoMapper.selectCount(userInfoDOLambdaQueryWrapper);
        return count > 0;
    }
}
