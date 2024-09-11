package org.mrzhuyk.practice.domain.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class UserInfo {
    
    
    /**
     * 用户ID
     */
    private Long userId;
    
    
    /**
     * 真实名称
     */
    private String realName;
    
    /**
     * 昵称
     */
    private String nickName;
    
    
    /**
     * 加密盐
     */
    private String salt;
    
    
    
    /**
     * 手机号码
     */
    private String mobile;
    
    
    /**
     * 邮箱
     */
    private String email;
    
    
    /**
     * 注册时间
     */
    private Date registryTime;
    

    
    
}
