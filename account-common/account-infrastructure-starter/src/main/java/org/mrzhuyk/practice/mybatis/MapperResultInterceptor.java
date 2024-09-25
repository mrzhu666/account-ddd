package org.mrzhuyk.practice.mybatis;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.util.ReflectUtil;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.mrzhuyk.practice.desensitization.Desensitization;
import org.mrzhuyk.practice.desensitization.DesensitizeStrategyFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 查询结果集拦截器，对Mybatis-plus的查询结果进行脱敏
 */
@Intercepts({@Signature(
    type = ResultSetHandler.class,
    method = "handleResultSets",
    args = {Statement.class}
)})
public class MapperResultInterceptor implements Interceptor {
    
    private final ConcurrentHashMap<Class<?>, List<Field>> containMap = new ConcurrentHashMap<>();
    private final ConcurrentHashSet<Class<?>> nonContainSet = new ConcurrentHashSet<>();
    
    
    /**
     * todo 这里只是提供了一个思路，没有解决多层套用关系（只对第一层字段进行判断）
     * todo 这里也没有解决根据某个角色或者权限去判断，动态的脱敏，可以自行增加上下文信息，进行设计
     *
     * @param invocation 调用信息
     * @return 结果
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // Proceed with the original invocation and get the result
        Object result = invocation.proceed();
        
        // If the result is null, return immediately
        if (Objects.isNull(result)) {
            return null;
        }
        
        // Check if the result is a list
        if (result instanceof List<?> resultList) {
            // If the list is empty, return the original result
            if (CollectionUtils.isEmpty(resultList)) {
                return result;
            }
            
            // Get the first element of the list
            Object first = resultList.get(0);
            
            // Check if the class of the first element is already marked as not containing desensitization fields
            if (nonContainSet.contains(first.getClass())) {
                // If so, return the original result
                return result;
            }
            
            // Get the list of fields that need desensitization for the class of the first element
            List<Field> fields = containMap.get(first.getClass());
            
            // If the list of fields is empty, calculate it
            if (CollectionUtils.isEmpty(fields)) {
                // Get all fields of the class and filter those with the @Desensitization annotation
                fields = Arrays.stream(ReflectUtil.getFields(first.getClass()))
                    .filter(f -> f.isAnnotationPresent(Desensitization.class))
                    .toList();
                
                // If no fields need desensitization, mark the class as not containing desensitization fields
                if (CollectionUtils.isEmpty(fields)) {
                    nonContainSet.add(first.getClass());
                    return result;
                } else {
                    // Otherwise, cache the list of fields for future use
                    containMap.putIfAbsent(first.getClass(), fields);
                }
            }
            
            // Iterate over the list and perform desensitization on each element
            for (Object obj : resultList) {
                fields.forEach(f -> {
                    // Get the @Desensitization annotation for the current field
                    Desensitization desensitization = f.getAnnotation(Desensitization.class);
                    
                    // Get the value of the current field
                    Object value = ReflectUtil.getFieldValue(obj, f);
                    
                    // Check if the value is a non-empty string
                    if (Objects.nonNull(value) && value instanceof String && !value.toString().trim().isEmpty()) {
                        // Perform desensitization on the value using the strategy specified in the @Desensitization annotation
                        ReflectUtil.setFieldValue(obj, f, DesensitizeStrategyFactory.getStrategy(desensitization.value()).desensitize(value.toString(), desensitization));
                    }
                });
            }
        }
        
        // Return the desensitized result
        return result;
    }
    
    
    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }
}

