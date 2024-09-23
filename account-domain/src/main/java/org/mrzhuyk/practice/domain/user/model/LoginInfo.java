package org.mrzhuyk.practice.domain.user.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mrzhuyk.practice.domain.user.enums.Device;
import org.mrzhuyk.practice.util.HttpServletContext;

import java.util.Date;


/**
 * 登录信息值对象
 */
@Getter
@Setter
@ToString
public class LoginInfo {
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 登录方式：1:手机;2:邮箱
     */
    private Integer loginType;
    
    /**
     * 登录设备：1:手机;2:web
     */
    private Integer device;
    
    /**
     * 登录时间
     */
    private Date loginTime;
    
    /**
     * 登录IP地址
     */
    private String ip;
    
    public static LoginInfo build(UserEntity userEntity) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(userEntity.getUserInfo().getUserId());
        loginInfo.setLoginType(Device.getDevice(HttpServletContext.getRequest()).getType());
        loginInfo.setDevice(0);
        loginInfo.setLoginTime(new Date());
        loginInfo.setIp(HttpServletContext.getIpAddress());
        
        return loginInfo;
    }

}
