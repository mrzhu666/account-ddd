package org.mrzhuyk.practice.domain.user.repository;

import org.mrzhuyk.practice.domain.user.model.UserEntity;

public interface UserRepository {

    public UserEntity findByUserId(Long userId);
}
