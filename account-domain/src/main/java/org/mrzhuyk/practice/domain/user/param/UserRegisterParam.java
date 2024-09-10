package org.mrzhuyk.practice.domain.user.param;


import lombok.Getter;
import lombok.Setter;

/**
 * 用户注册参数
 */
@Getter
@Setter
public class UserRegisterParam {
    
    private String realName;
    
    private String nickName;
    
    private String mobile;
    
    private String email;
    
    private String password;
    
}
