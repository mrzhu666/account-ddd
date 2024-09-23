package org.mrzhuyk.practice.domain.user.enums;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.domain.user.model.UserEntity;

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
     * 根据用户实体的
     *
     * @param userEntity 用户实体
     * @return 登录方式
     */
    public static LoginType checkMobileOrEmail(UserEntity userEntity) {
        if (StringUtils.isNotBlank(userEntity.getUserInfo().getMobile())) {
            return LoginType.MOBILE;
        } else if (StringUtils.isNotBlank(userEntity.getUserInfo().getEmail())) {
            return LoginType.EMAIL;
        }
        return null;
        
    }
    
    
}
