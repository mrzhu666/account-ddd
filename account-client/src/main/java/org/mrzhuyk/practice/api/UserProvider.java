package org.mrzhuyk.practice.api;

public interface UserProvider {
    
    /**
     * 获取当前登录的用户ID
     *
     * @return 用户ID
     */
    Long getStatusUserId();
}
