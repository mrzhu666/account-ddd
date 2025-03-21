package org.mrzhuyk.practice.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;


/**
 * 用户密码，存储：
 * 加密后的密码
 * 盐值
 */
@Setter
@Getter
@AllArgsConstructor
public class UserEncryptPassword {
    
    /**
     * 加密后的密码
     */
    private String encryptedPassword;
    
    
    /**
     * 保存盐值和加密后的密码，且不会存储明文密码
     *
     * @param password 明文密码
     * @param salt     盐值
     */
    public UserEncryptPassword(String password, String salt) {
        this.encryptedPassword = encryptPassword(password, salt);
    }
    
    /**
     * 校验密码是否正确
     *
     * @param password 明文密码
     * @return true-密码正确; false-密码错误
     */
    public boolean isCorrect(String password, String salt) {
        return StringUtils.equals(encryptPassword(password, salt), encryptedPassword);
    }
    
    
    /**
     * 加密密码
     *
     * @param password 明文密码
     * @return 加密后的密码
     */
    private String encryptPassword(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }
}
