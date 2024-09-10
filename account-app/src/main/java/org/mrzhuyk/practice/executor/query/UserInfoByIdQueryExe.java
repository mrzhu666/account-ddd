package org.mrzhuyk.practice.executor.query;

import jakarta.annotation.Resource;
import org.mrzhuyk.practice.assembler.UserAssembler;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.service.UserSerivce;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.stereotype.Component;

@Component
public class UserInfoByIdQueryExe {

    @Resource
    private UserSerivce userSerivce;
    
    public UserInfoVO execute(Long userId) {
        UserEntity query = userSerivce.query(userId);
        
        
        return UserAssembler.assemble(query);
    }
}
