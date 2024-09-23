package org.mrzhuyk.practice.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mrzhuyk.practice.util.PatternUtil;

@Getter
@AllArgsConstructor
public enum LoginType {
    MOBILE(1, "手机号码登录"),
    EMAIL(2, "邮箱登录"),
    ;
    
    private final int type;
    private final String desc;
    
    public static LoginType getByType(int type) {
        for (LoginType loginType : LoginType.values()) {
            if (type == loginType.type) {
                return loginType;
            }
        }
        return null;
    }
    
    
    /**
     * 根据用户登录命令获取登录类型
     *
     * @param userAccount 用户登录账号
     * @return 登录方式
     */
    public static LoginType getByUserAccount(String userAccount) {
        if (PatternUtil.matchMobile(userAccount)) {
            return LoginType.MOBILE;
        } else if (PatternUtil.matchEmail(userAccount)) {
            return LoginType.EMAIL;
        }
        return null;
        
    }
    
    
}
