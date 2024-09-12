package org.mrzhuyk.practice.executor.command;

import jakarta.annotation.Resource;
import org.mrzhuyk.practice.assembler.UserAssembler;
import org.mrzhuyk.practice.domain.user.service.UserSerivce;
import org.mrzhuyk.practice.dto.command.UserLoginCmd;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.stereotype.Component;

@Component
public class UserLoginCmdExe {
    @Resource
    private UserSerivce userSerivce;
    
    public UserInfoVO execute(UserLoginCmd userLoginCmd) {
        return UserAssembler.assemble(userSerivce.login(userLoginCmd));
    }
    
}
