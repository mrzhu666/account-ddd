package org.mrzhuyk.practice.desensitization;


/**
 * 反序列化数据脱敏接口
 */
public interface DesensitizeService {
    
    /**
     * 脱敏
     *
     * @param value      原字段
     * @param annotation 脱敏注解
     * @return 脱敏后字段
     */
    String desensitize(String value, Desensitization annotation);
    
    
}
