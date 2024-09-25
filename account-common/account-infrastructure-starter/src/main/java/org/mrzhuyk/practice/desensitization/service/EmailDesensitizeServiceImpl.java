package org.mrzhuyk.practice.desensitization.service;

import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.desensitization.Desensitization;
import org.mrzhuyk.practice.desensitization.DesensitizeService;

/**
 * 邮箱脱敏算法
 */
public class EmailDesensitizeServiceImpl implements DesensitizeService {
    
    private static final String Email_Regex = "(\\w+)\\w{%d}@(\\w+)";
    
    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            int index = value.indexOf("@");
            if (index > 0) {
                index = (int) (index * 0.6);
                value = value.replaceAll(String.format(Email_Regex, index), "$1****$2");
            }
        }
        return value;
    }
}
