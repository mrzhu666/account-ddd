package org.mrzhuyk.practice.desensitization;


import java.lang.annotation.*;

/**
 * 数据脱敏注解
 * 对需要脱敏数据进行注解，可以自动脱敏
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Desensitization {
    
    /**
     * 脱敏类型
     *
     * @return 脱敏类型
     */
    DesensitizationType value() default DesensitizationType.DEFAULT;
    
    /**
     * 开始坐标
     *
     * <p>1.beginIndex=-1,endIndex=3,则表示最后3位脱敏
     * <p>2.beginIndex=3,endIndex=-1,则表示前3位脱敏
     * <p>3.beginIndex=1,endIndex=2,则表示第一位到第二位脱敏
     * <p>4.beginIndex=-1,endIndex=-1,则表示全部字符脱敏
     *
     * @return 开始坐标值
     */
    int beginIndex() default 0;
    
    /**
     * 结束坐标
     *
     * @return 结束坐标值
     */
    int endIndex() default 0;
}
