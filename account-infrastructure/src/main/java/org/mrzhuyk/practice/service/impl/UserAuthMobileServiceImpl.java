package org.mrzhuyk.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mrzhuyk.practice.dataobject.UserAuthMobileDO;
import org.mrzhuyk.practice.service.UserAuthMobileService;
import org.mrzhuyk.practice.mapper.UserAuthMobileMapper;
import org.springframework.stereotype.Service;

/**
* @author mrzhu
* @description 针对表【user_auth_mobile(用户手机认证表)】的数据库操作Service实现
* @createDate 2024-09-07 19:03:03
*/
@Service
public class UserAuthMobileServiceImpl extends ServiceImpl<UserAuthMobileMapper, UserAuthMobileDO>
    implements UserAuthMobileService{

}




