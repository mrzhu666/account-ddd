package org.mrzhuyk.practice.domain.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.DigestUtils;


/**
 * 用户密码，存储：
 *    加密后的密码
 *    盐值
 */
@Setter
@Getter
public class UserPassword {
    private String encryptPassword;
    
    private String salt;
    
    public UserPassword(String password, String salt) {
        this.salt = salt;
        this.encryptPassword = encryptPassword(password);
    }
    
    /**
     * 校验密码是否正确
     * @param password 明文密码
     * @return true-密码正确; false-密码错误
     */
    public boolean isCorrect(String password) {
        return encryptPassword(password).equals(encryptPassword);
    }
    
    
    /**
     * 加密密码
     * @param password 明文密码
     * @return 加密后的密码
     */
    private String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }
}
