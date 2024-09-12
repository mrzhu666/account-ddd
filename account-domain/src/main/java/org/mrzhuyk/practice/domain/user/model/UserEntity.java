package org.mrzhuyk.practice.domain.user.model;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.mrzhuyk.practice.util.RandomUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Getter
@Setter
public class UserEntity {

    
    private UserInfo userInfo;
    
    /**
     * 用户加密密码
     */
    private UserEncryptPassword userEncryptPassword;
    
    /**
     * 传入明文密码，并且生成随机盐值对其加密，不会存储明文密码
     *
     * @param password 明文密码
     */
    public void setEncryptedPassword(String password) {
        userInfo.setSalt(RandomUtil.randomChars(16));
        userEncryptPassword = new UserEncryptPassword(password, userInfo.getSalt());
    }
    
    /**
     * @return 加密后的密码
     */
    public String getEncryptedPassword() {
        return userEncryptPassword.getEncryptedPassword();
    }
    
    
    /**
     * 校验密码是否正确
     *
     * @param password 要验证的明文密码
     * @return true-密码正确; false-密码错误
     */
    public boolean isCorrectPassword(String password) {
        return userEncryptPassword.isCorrect(password, userInfo.getSalt());
    }
    
    /**
     * 用户登录态键
     */
    public static final String USER_LOGIN_STATE = "userLoginState";
    
    /**
     * 设置用户登录态
     *
     * @param userEntity 用户实体
     */
    public static void setSessionUserEntity(UserEntity userEntity) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(USER_LOGIN_STATE, userEntity);
    }
    
    /**
     * 获取用户登录态
     *
     * @return 用户实体
     */
    public static UserEntity getSessionUserEntity() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpSession session = request.getSession();
        return (UserEntity) session.getAttribute(USER_LOGIN_STATE);
    }
}
