package org.mrzhuyk.practice.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.mrzhuyk.practice.model.BaseDO;

/**
 * 用户登出日志表
 * @TableName user_logout_log
 */
@TableName(value ="user_logout_log")
@Data
public class UserLogoutLogDO extends BaseDO implements Serializable {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登出方式：1:手机;2:邮箱
     */
    private Integer logoutType;

    /**
     * 登录设备：1:手机;2:web
     */
    private Integer device;

    /**
     * 登录时间
     */
    private Date logoutTime;

    /**
     * 登录IP地址
     */
    private String ip;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserLogoutLogDO other = (UserLogoutLogDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLogoutType() == null ? other.getLogoutType() == null : this.getLogoutType().equals(other.getLogoutType()))
            && (this.getDevice() == null ? other.getDevice() == null : this.getDevice().equals(other.getDevice()))
            && (this.getLogoutTime() == null ? other.getLogoutTime() == null : this.getLogoutTime().equals(other.getLogoutTime()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLogoutType() == null) ? 0 : getLogoutType().hashCode());
        result = prime * result + ((getDevice() == null) ? 0 : getDevice().hashCode());
        result = prime * result + ((getLogoutTime() == null) ? 0 : getLogoutTime().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", logoutType=").append(logoutType);
        sb.append(", device=").append(device);
        sb.append(", logoutTime=").append(logoutTime);
        sb.append(", ip=").append(ip);
        sb.append(", createUser=").append(getCreateUser());
        sb.append(", updateUser=").append(getUpdateUser());
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", updateTime=").append(getUpdateUser());
        sb.append(", isDeleted=").append(getIsDeleted());
        sb.append(", version=").append(getVersion());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}