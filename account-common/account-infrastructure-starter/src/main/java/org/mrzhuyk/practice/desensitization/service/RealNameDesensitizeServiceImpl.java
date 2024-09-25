package org.mrzhuyk.practice.desensitization.service;

import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.desensitization.Desensitization;
import org.mrzhuyk.practice.desensitization.DesensitizeService;

/**
 * 真实姓名脱敏算法
 */
public class RealNameDesensitizeServiceImpl implements DesensitizeService {
    
    private static final String Name_Regex = "(\\W)(\\w*)(\\W)";
    
    @Override
    public String desensitize(String value, Desensitization annotation) {
        int length;
        if (StringUtils.isNotBlank(value) && (length = value.length()) > 1) {
            value = StringUtils.left(value, 1) + StringUtils.leftPad(StringUtils.right(value, 1), length > 2 ? value.length() - 1 : length, "*");
        }
        return value;
    }
}
