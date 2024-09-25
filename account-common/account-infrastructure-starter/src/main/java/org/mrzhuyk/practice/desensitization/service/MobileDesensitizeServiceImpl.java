package org.mrzhuyk.practice.desensitization.service;

import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.desensitization.Desensitization;
import org.mrzhuyk.practice.desensitization.DesensitizeService;

/**
 * 手机号脱敏算法
 * 中间屏蔽四个字符
 */
public class MobileDesensitizeServiceImpl implements DesensitizeService {
    
    private static final String Mobile_Regex = "(\\d{3})\\d{4}(\\d{4})";
    
    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            if (value.length() == 11) {
                value = value.replaceAll(Mobile_Regex, "$1****$2");
            } else {
                value = String.format("%s****%s", value.substring(0, 3), value.substring(7));
            }
        }
        return value;
    }
}
