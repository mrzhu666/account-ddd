package org.mrzhuyk.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mrzhuyk.practice.dataobject.UserInfoDO;
import org.mrzhuyk.practice.service.UserInfoService;
import org.mrzhuyk.practice.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author mrzhu
* @description 针对表【user_info(用户基础信息表)】的数据库操作Service实现
* @createDate 2024-09-07 19:03:03
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDO>
    implements UserInfoService{

}




