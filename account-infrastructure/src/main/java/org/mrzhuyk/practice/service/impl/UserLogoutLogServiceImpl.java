package org.mrzhuyk.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mrzhuyk.practice.dataobject.UserLogoutLogDO;
import org.mrzhuyk.practice.service.UserLogoutLogService;
import org.mrzhuyk.practice.mapper.UserLogoutLogMapper;
import org.springframework.stereotype.Service;

/**
* @author mrzhu
* @description 针对表【user_logout_log(用户登出日志表)】的数据库操作Service实现
* @createDate 2024-09-07 19:03:03
*/
@Service
public class UserLogoutLogServiceImpl extends ServiceImpl<UserLogoutLogMapper, UserLogoutLogDO>
    implements UserLogoutLogService{

}




