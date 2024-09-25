package org.mrzhuyk.practice.desensitization.service;

import cn.hutool.dfa.SensitiveUtil;
import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.desensitization.Desensitization;
import org.mrzhuyk.practice.desensitization.DesensitizeService;

/**
 * dfa算法
 * 注：使用dfa算法时，一定要先init，否则不生效
 */
public class DfaDesensitizeServiceImpl implements DesensitizeService {
    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value) && SensitiveUtil.isInited()) {
            value = SensitiveUtil.sensitiveFilter(value);
        }
        return value;
    }
}
