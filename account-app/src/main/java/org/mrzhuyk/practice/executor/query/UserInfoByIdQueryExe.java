package org.mrzhuyk.practice.executor.query;

import jakarta.annotation.Resource;
import org.mrzhuyk.practice.domain.user.service.UserSerivce;
import org.springframework.stereotype.Component;

@Component
public class UserInfoByIdQueryExe {

    @Resource
    private UserSerivce userSerivce;
    
    public String query(Long userId) {
        userSerivce.query(userId);
        return null;
    }
}
