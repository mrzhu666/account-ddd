package org.mrzhuyk.practice.executor.query;

import jakarta.annotation.Resource;
import org.mrzhuyk.practice.assembler.UserAssembler;
import org.mrzhuyk.practice.domain.user.service.UserService;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.stereotype.Component;

@Component
public class UserLoginStatusQueryExe {
    
    @Resource
    private UserService userService;
    
    public UserInfoVO execute() {
        return UserAssembler.assemble(userService.status());
    }
}
