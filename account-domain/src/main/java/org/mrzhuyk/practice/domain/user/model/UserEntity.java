package org.mrzhuyk.practice.domain.user.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {

    private UserInfo userInfo;
    
    /**
     * 用户密码
     */
    private UserPassword userPassword;
    
    
}
