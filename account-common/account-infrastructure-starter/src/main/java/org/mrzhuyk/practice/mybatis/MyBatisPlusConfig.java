package org.mrzhuyk.practice.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 */
@Configuration
public class MyBatisPlusConfig {
    
    /**
     * 拦截器配置，分页配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    
    
    /**
     * @return 脱敏拦截器
     */
    @Bean
    @ConditionalOnProperty(prefix = "desensitization.database", name = "enable", havingValue = "true")
    public MapperResultInterceptor mapperResultInterceptor() {
        return new MapperResultInterceptor();
    }
}