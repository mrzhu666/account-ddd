package org.mrzhuyk.practice.executor.command;

import jakarta.annotation.Resource;
import org.mrzhuyk.practice.assembler.UserAssembler;
import org.mrzhuyk.practice.domain.user.service.UserService;
import org.mrzhuyk.practice.dto.command.UserLoginCmd;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.stereotype.Component;

@Component
public class UserLoginCmdExe {
    @Resource
    private UserService userService;
    
    public UserInfoVO execute(UserLoginCmd userLoginCmd) {
        return UserAssembler.assemble(userService.login(userLoginCmd));
    }
    
}
