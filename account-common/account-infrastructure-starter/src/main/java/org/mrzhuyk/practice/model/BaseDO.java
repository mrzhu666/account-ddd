package org.mrzhuyk.practice.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础DO，提供通用字段的填充。
 * DO对象继承该类
 */
@Getter
@Setter
public abstract class BaseDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;
    
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
