package org.mrzhuyk.practice.domain.user.repository;

import org.mrzhuyk.practice.domain.user.model.LoginInfo;
import org.mrzhuyk.practice.domain.user.model.UserEntity;

public interface UserRepository {
    
    /**
     * 插入用户数据，用于注册
     *
     * @param userEntity 用户实体
     * @return 用户ID
     */
    long insertUserInfo(UserEntity userEntity);
    
    
    /**
     * 插入登录信息
     *
     * @param loginInfo 登录信息
     * @return true:插入成功; false:插入失败
     */
    boolean insertLoginInfo(LoginInfo loginInfo);
    
    
    /**
     * 插入邮箱认证
     *
     * @param userEntity 用户实体
     * @return 受影响的行数
     */
    int insertUserAuthEmail(UserEntity userEntity);
    
    
    /**
     * 插入手机认证
     *
     * @param userEntity 用户实体
     * @return 受影响的行数
     */
    int insertUserAuthMobile(UserEntity userEntity);
    
    
    /**
     * 根据用户ID查询用户实体
     *
     * @param userId 用户ID
     * @return 用户实体UserEntity
     */
    UserEntity getByUserId(Long userId);
    
    
    /**
     * 根据邮箱查询用户实体
     *
     * @param email 邮箱
     * @return 用户实体，查询不到返回null
     */
    UserEntity getByEmail(String email);
    
    
    /**
     * 根据手机查询用户实体
     *
     * @param mobile 手机号
     * @return 用户实体，查询不到返回null
     */
    UserEntity getByMobile(String mobile);
    
    
    /**
     * 判断邮箱是否注册过
     *
     * @param email 邮箱
     * @return True-存在; False-不存在
     */
    Boolean checkEmail(String email);
    
    
    /**
     * 判断手机号是否注册过
     *
     * @param mobile 手机号
     * @return True-存在; False-不存在
     */
    Boolean checkMobile(String mobile);
    
    
    /**
     * 判断昵称是否注册过
     *
     * @param nickName 昵称
     * @return True-存在; False-不存在
     */
    Boolean checkNickName(String nickName);
}
