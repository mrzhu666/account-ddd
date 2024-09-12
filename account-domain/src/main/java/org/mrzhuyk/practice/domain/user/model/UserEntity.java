package org.mrzhuyk.practice.domain.user.model;


import lombok.Getter;
import lombok.Setter;
import org.mrzhuyk.practice.util.RandomUtil;

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
    public void setPassword(String password) {
        userInfo.setSalt(RandomUtil.randomChars(16));
        userEncryptPassword = new UserEncryptPassword(password, userInfo.getSalt());
    }
    
    /**
     * @return 加密后的密码
     */
    public String getPassword() {
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
}
