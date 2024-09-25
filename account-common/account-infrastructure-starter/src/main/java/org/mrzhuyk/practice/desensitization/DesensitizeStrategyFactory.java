package org.mrzhuyk.practice.desensitization;


import org.mrzhuyk.practice.desensitization.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 获取脱敏策略
 * 工厂模式+策略模式+单例模式
 */
public class DesensitizeStrategyFactory {
    private static final Map<DesensitizationType, DesensitizeService> strategyMap = new HashMap<>() {{
        put(DesensitizationType.CARD_NO, new CardNoDesensitizeServiceImpl());
        put(DesensitizationType.ENCRYPT_DECRYPT, new EncryptDecryptDesensitizeServiceImpl());
        put(DesensitizationType.DFA, new DfaDesensitizeServiceImpl());
        put(DesensitizationType.CUSTOM, new CustomDesensitizeServiceImpl());
        put(DesensitizationType.MOBILE, new MobileDesensitizeServiceImpl());
        put(DesensitizationType.EMAIL, new EmailDesensitizeServiceImpl());
        put(DesensitizationType.REAL_NAME, new RealNameDesensitizeServiceImpl());
        put(DesensitizationType.DEFAULT, new DefaultDesensitizeServiceImpl());
    }};
    
    
    /**
     * @param type 脱敏类型
     * @return 脱敏策略服务
     */
    public static DesensitizeService getStrategy(DesensitizationType type) {
        return Optional.ofNullable(strategyMap.get(type)).orElse(strategyMap.get(DesensitizationType.DEFAULT));
    }
}
