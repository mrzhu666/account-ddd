package org.mrzhuyk.practice.executor.query;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.mrzhuyk.practice.assembler.UserAssembler;
import org.mrzhuyk.practice.domain.user.model.UserEntity;
import org.mrzhuyk.practice.domain.user.service.UserSerivce;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
public class UserInfoByIdQueryExe {

    @Resource
    private UserSerivce userSerivce;
    
    public UserInfoVO execute(@Validated @NotNull Long userId) {
        UserEntity query = userSerivce.query(userId);
        return UserAssembler.assemble(query);
    }
}
