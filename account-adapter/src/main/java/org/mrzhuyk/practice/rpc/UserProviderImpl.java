package org.mrzhuyk.practice.rpc;

import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.mrzhuyk.practice.api.UserProvider;
import org.mrzhuyk.practice.executor.query.UserLoginStatusQueryExe;
import org.mrzhuyk.practice.vo.UserInfoVO;

@DubboService
public class UserProviderImpl implements UserProvider {
    
    @Resource
    private UserLoginStatusQueryExe userLoginStatusQueryExe;
    
    @Override
    public Long getStatusUserId() {
        UserInfoVO userInfoVO = userLoginStatusQueryExe.execute();
        return userInfoVO.getUserId();
    }
}
