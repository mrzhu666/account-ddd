package org.mrzhuyk.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mrzhuyk.practice.dataobject.UserAuthEmailDO;
import org.mrzhuyk.practice.service.UserAuthEmailService;
import org.mrzhuyk.practice.mapper.UserAuthEmailMapper;
import org.springframework.stereotype.Service;

/**
* @author mrzhu
* @description 针对表【user_auth_email(用户邮箱认证表)】的数据库操作Service实现
* @createDate 2024-09-07 19:03:03
*/
@Service
public class UserAuthEmailServiceImpl extends ServiceImpl<UserAuthEmailMapper, UserAuthEmailDO>
    implements UserAuthEmailService{

}




