package org.mrzhuyk.practice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mrzhuyk.practice.controller.UserController;
import org.mrzhuyk.practice.domain.user.model.UserInfo;
import org.mrzhuyk.practice.domain.user.repository.UserRepository;
import org.mrzhuyk.practice.dto.command.UserLoginCmd;
import org.mrzhuyk.practice.dto.command.UserRegisterCmd;
import org.mrzhuyk.practice.mapper.*;
import org.mrzhuyk.practice.vo.UserInfoVO;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;


@SpringBootTest
@Slf4j
@ActiveProfiles("test") // 指定测试环境
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 启用
public class UserControllerTest {
    
    @Resource
    private UserController userController;
    
    @Resource
    private UserLoginLogMapper userLoginLogMapper;
    
    @Resource
    private UserLogoutLogMapper userLogoutLogMapper;
    
    @Resource
    private UserInfoMapper userInfoMapper;
    
    @Resource
    private UserAuthMobileMapper userAuthMobileMapper;
    
    @Resource
    private UserAuthEmailMapper userAuthEmailMapper;
    
    /**
     * 测试用户注册
     */
    @Test
    @Order(Integer.MIN_VALUE) // 注册用户，最高优先级，最先先执行
    public void testRegister() {
        //清空数据
        userInfoMapper.delete(new QueryWrapper<>());
        userAuthEmailMapper.delete(new QueryWrapper<>());
        userAuthMobileMapper.delete(new QueryWrapper<>());
        userLoginLogMapper.delete(new QueryWrapper<>());
        userLogoutLogMapper.delete(new QueryWrapper<>());
        
        UserRegisterCmd userRegisterCmd = new UserRegisterCmd();
        userRegisterCmd.setRealName("张三");
        userRegisterCmd.setNickName("user1");
        userRegisterCmd.setMobile("13123456789");
        userRegisterCmd.setEmail("12345678901@qq.com");
        userRegisterCmd.setPassword("12345678aA!");
        Response<Boolean> register = userController.register(userRegisterCmd);
        Assert.isTrue(register.getData(), "注册失败");
    }
    
    /**
     * 测试获取用户实体
     */
    @Test
    public void testQueryUserInfoVO() {
        //Response<UserInfoVO> query = userController.query(1L);
        //Assert.notNull(query.getData(), "获取用户失败");
    }
    
    
    /**
     * 测试用户登录
     */
    @Test
    public void testLogin() {
        UserLoginCmd userLoginMobile = new UserLoginCmd();
        userLoginMobile.setUserAccount("13123456789");
        userLoginMobile.setPassword("12345678aA!");
        Response<UserInfoVO> login = userController.login(userLoginMobile);
        Assert.notNull(login.getData(), "手机登录失败");
        
        UserLoginCmd userLoginEmail = new UserLoginCmd();
        userLoginEmail.setUserAccount("12345678901@qq.com");
        userLoginEmail.setPassword("12345678aA!");
        login = userController.login(userLoginEmail);
        Assert.notNull(login.getData(), "邮箱登录失败");
        
        log.debug(login.getData().toString());
    }
    
}
