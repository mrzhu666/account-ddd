package org.mrzhuyk.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.Response;
import org.mrzhuyk.practice.domain.user.model.UserInfo;
import org.mrzhuyk.practice.dto.command.UserLoginCmd;
import org.mrzhuyk.practice.dto.command.UserRegisterCmd;
import org.mrzhuyk.practice.executor.command.UserLoginCmdExe;
import org.mrzhuyk.practice.executor.command.UserRegisterCmdExe;
import org.mrzhuyk.practice.executor.query.UserInfoByIdQueryExe;
import org.mrzhuyk.practice.executor.query.UserLoginStatusQueryExe;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/user")
@Tag(name = "用户接口")
@Slf4j
public class UserController {
    
    @Resource
    private UserInfoByIdQueryExe userInfoByIdQueryExe;
    
    @Resource
    private UserRegisterCmdExe userRegisterCmdExe;
    
    @Resource
    private UserLoginCmdExe userLoginCmdExe;
    
    @Resource
    private UserLoginStatusQueryExe userLoginStatusQueryExe;

    
    @Operation(summary = "查询用户")
    @Parameters({
        @Parameter(name = "userId", description = "用户id", required = true, in = ParameterIn.QUERY)
    })
    @GetMapping("/query")
    public Response<UserInfoVO> query(@Validated @NotNull(message = "用户id不能为空") Long userId) {
        return Response.success(userInfoByIdQueryExe.execute(userId));
    }
    
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Response<UserInfoVO> login(@Validated @RequestBody UserLoginCmd userLoginCmd) {
        return Response.success(userLoginCmdExe.execute(userLoginCmd));
    }
    
    @Operation(summary = "当前用户登录状态")
    @GetMapping("/status")
    public Response<UserInfoVO> status() {
        return Response.success(userLoginStatusQueryExe.execute());
    }
    
    
    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "用户注册成功True")
    public Response<Boolean> register(@Validated @RequestBody UserRegisterCmd userRegisterCmd) {
        return Response.success(userRegisterCmdExe.execute(userRegisterCmd));
    }
    
    @Operation(summary = "测试接口")
    @Parameters({
        @Parameter(name = "testId", description = "测试id", required = true, in = ParameterIn.QUERY),
        @Parameter(name = "testName", description = "测试名", required = true, in = ParameterIn.QUERY)
    })
    @GetMapping("/hello")
    public Response<String> test(String testId, String testName) {
        
        return Response.success(testId + " " + testName);
    }
    
}
