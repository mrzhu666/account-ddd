package org.mrzhuyk.practice.executor.command;

import jakarta.annotation.Resource;
import org.mrzhuyk.practice.assembler.UserAssembler;
import org.mrzhuyk.practice.domain.user.service.UserService;
import org.mrzhuyk.practice.dto.command.UserRegisterCmd;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterCmdExe {
    @Resource
    private UserService userService;
    
    public Boolean execute(UserRegisterCmd userRegisterCmd) {
        return userService.register(UserAssembler.assemble(userRegisterCmd));
    }
}
