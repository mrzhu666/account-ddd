package org.mrzhuyk.practice.util;


import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用正则表达式
 */
@UtilityClass
public class PatternUtil {
    
    /**
     * 邮箱正则表达式
     */
    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.\\+]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    
    
    
    /**
     * 手机正则表达式，中国大陆的手机号，无国家码
     */
    public static final String PHONE_PATTERN = "^1(3|4|5|7|8|9)\\d{9}$";
    
    
    /**
     * 匹配手机
     *
     * @param mobile 手机号码
     * @return true 是手机; false 不是手机
     */
    public static boolean matchMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        Pattern p = Pattern.compile(PHONE_PATTERN);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
    
    /**
     * 匹配邮箱
     *
     * @param email 邮箱
     * @return true 是邮箱; false 不是邮箱
     */
    public static boolean matchEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        Pattern p = Pattern.compile(EMAIL_PATTERN);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    
    
    
    
}
