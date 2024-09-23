package org.mrzhuyk.practice.domain.user.param;


import lombok.Data;

/**
 * 用户登录参数
 */
@Data
public class UserLoginParam {
    
    private String userAccount;
    
    private String password;

}
