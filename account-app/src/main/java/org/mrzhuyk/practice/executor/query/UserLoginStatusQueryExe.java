package org.mrzhuyk.practice.executor.query;

import jakarta.annotation.Resource;
import org.mrzhuyk.practice.assembler.UserAssembler;
import org.mrzhuyk.practice.domain.user.service.UserSerivce;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.stereotype.Component;

@Component
public class UserLoginStatusQueryExe {
    
    @Resource
    private UserSerivce userSerivce;
    
    public UserInfoVO execute() {
        return UserAssembler.assemble(userSerivce.status());
    }
}
