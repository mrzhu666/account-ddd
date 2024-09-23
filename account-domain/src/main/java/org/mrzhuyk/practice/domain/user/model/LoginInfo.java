package org.mrzhuyk.practice.domain.user.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mrzhuyk.practice.domain.user.enums.Device;
import org.mrzhuyk.practice.domain.user.enums.LoginType;
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
    
    /**
     * 返回登录信息
     *
     * @param userEntity 用户实体，只使用用户ID
     * @param loginType  登录方式
     * @return 登录信息
     */
    public static LoginInfo getLoginInfo(UserEntity userEntity, LoginType loginType) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(userEntity.getUserInfo().getUserId());
        loginInfo.setLoginType(loginType.getType());
        loginInfo.setDevice(Device.getDevice(HttpServletContext.getRequest()).getType());
        loginInfo.setLoginTime(new Date());
        loginInfo.setIp(HttpServletContext.getIpAddress());
        
        return loginInfo;
    }
    
}
