package org.mrzhuyk.practice.domain.user.repository;

import org.mrzhuyk.practice.domain.user.model.UserEntity;

public interface UserRepository {
    
    /**
     * 根据用户ID查询用户实体
     * @param userId 用户ID
     * @return 用户实体UserEntity
     */
    public UserEntity findByUserId(Long userId);
}
