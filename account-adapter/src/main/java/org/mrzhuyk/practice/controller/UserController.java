package org.mrzhuyk.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mrzhuyk.practice.config.Response;
import org.mrzhuyk.practice.service.UserSerivce;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Tag(name = "用户接口")
public class UserController {
    
    @Resource
    private UserSerivce userSerivce;
    
    @Operation(summary = "查询用户")
    @Parameters({
        @Parameter(name="userId",description = "用户id",required = true,in = ParameterIn.QUERY)
    })
    @GetMapping("/query")
    public Response<UserInfoVO> query(Long userId) {
        return Response.success(userSerivce.query(userId));
    }
    
    
    
    @Operation(summary = "测试接口")
    @Parameters({
        @Parameter(name="testId",description = "测试id",required = true,in = ParameterIn.QUERY),
        @Parameter(name="testName",description = "测试名",required = true,in=ParameterIn.QUERY)
    })
    @GetMapping("/hello")
    public Response<String> test(String testId, String testName) {
        return Response.success(testId+" "+testName);
    }

}
