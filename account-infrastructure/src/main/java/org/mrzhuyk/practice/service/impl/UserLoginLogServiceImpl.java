package org.mrzhuyk.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mrzhuyk.practice.dataobject.UserLoginLogDO;
import org.mrzhuyk.practice.service.UserLoginLogService;
import org.mrzhuyk.practice.mapper.UserLoginLogMapper;
import org.springframework.stereotype.Service;

/**
* @author mrzhu
* @description 针对表【user_login_log(用户登录日志表)】的数据库操作Service实现
* @createDate 2024-09-07 19:03:03
*/
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLogDO>
    implements UserLoginLogService{

}




