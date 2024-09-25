package org.mrzhuyk.practice.desensitization.service;

import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.desensitization.Desensitization;
import org.mrzhuyk.practice.desensitization.DesensitizeService;

/**
 * 身份证号脱敏算法
 */
public class CardNoDesensitizeServiceImpl implements DesensitizeService {
    
    private static final String Card_No_Regex = "(\\d{4})\\d{10}(\\w{4})";
    
    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            if (value.length() == 18) {
                value = value.replaceAll(Card_No_Regex, "$1****$2");
            } else {
                value = String.format("%s****%s", value.substring(0, 4), value.substring(14));
            }
        }
        return value;
    }
}
