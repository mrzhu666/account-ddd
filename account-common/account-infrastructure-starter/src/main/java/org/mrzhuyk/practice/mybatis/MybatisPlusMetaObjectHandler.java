package org.mrzhuyk.practice.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mrzhuyk.practice.util.LoginUserContext;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 配置MybatisPlus自动填充的字段
 * <a href="https://baomidou.com/guides/auto-fill-field/">自动填充字段</a>
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    
    String DEFAULT_CREATE_TIME = "createTime";
    
    String DEFAULT_UPDATE_TIME = "updateTime";
    
    String DEFAULT_CREATE_USER = "createUser";
    
    String DEFAULT_UPDATE_USER = "updateUser";
    
    String DEFAULT_DELETED = "isDeleted";
    
    String DEFAULT_VERSION = "version";
    
    Integer DEFAULT_DELETED_VALUE = 0;
    
    Integer DEFAULT_VERSION_VALUE = 0;
    
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        this.strictInsertFill(metaObject, DEFAULT_CREATE_TIME, Date.class, now);
        this.strictInsertFill(metaObject, DEFAULT_UPDATE_TIME, Date.class, now);
        this.strictInsertFill(metaObject, DEFAULT_CREATE_USER, Long.class, LoginUserContext.getLoginId());
        this.strictInsertFill(metaObject, DEFAULT_UPDATE_USER, Long.class, LoginUserContext.getLoginId());
        this.strictInsertFill(metaObject, DEFAULT_DELETED, Integer.class, DEFAULT_DELETED_VALUE);
        this.strictInsertFill(metaObject, DEFAULT_VERSION, Integer.class, DEFAULT_VERSION_VALUE);
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, DEFAULT_UPDATE_TIME, Date.class, new Date());
        this.strictUpdateFill(metaObject, DEFAULT_UPDATE_USER, Long.class, LoginUserContext.getLoginId());
    }
}
