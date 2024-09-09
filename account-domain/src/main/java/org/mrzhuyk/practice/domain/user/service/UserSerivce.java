package org.mrzhuyk.practice.domain.user.service;

import org.mrzhuyk.practice.vo.UserInfoVO;

public interface UserSerivce {

    public UserInfoVO query(Long userId);
}
