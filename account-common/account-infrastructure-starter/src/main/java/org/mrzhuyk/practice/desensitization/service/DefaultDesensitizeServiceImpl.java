package org.mrzhuyk.practice.desensitization.service;

import org.mrzhuyk.practice.desensitization.Desensitization;
import org.mrzhuyk.practice.desensitization.DesensitizeService;

/**
 * 默认脱敏算法
 */
public class DefaultDesensitizeServiceImpl implements DesensitizeService {
    @Override
    public String desensitize(String value, Desensitization annotation) {
        return value;
    }
}
