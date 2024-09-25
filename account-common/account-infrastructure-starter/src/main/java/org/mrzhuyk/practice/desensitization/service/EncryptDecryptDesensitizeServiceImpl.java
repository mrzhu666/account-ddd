package org.mrzhuyk.practice.desensitization.service;

import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.desensitization.Desensitization;
import org.mrzhuyk.practice.desensitization.DesensitizeService;
import org.mrzhuyk.practice.util.CryptoUtil;

import java.util.Objects;

/**
 * 加解密脱敏
 */
public class EncryptDecryptDesensitizeServiceImpl implements DesensitizeService {
    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            if (Objects.isNull(annotation)) {
                value = CryptoUtil.Symmetric.encryptAES(value, "rZxl3zy!rZxl3zy!");
            } else {
                value = CryptoUtil.Symmetric.decryptAES(value, "rZxl3zy!rZxl3zy!");
            }
        }
        return value;
    }
}
