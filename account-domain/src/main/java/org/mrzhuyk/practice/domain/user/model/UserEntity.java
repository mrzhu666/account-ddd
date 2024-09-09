package org.mrzhuyk.practice.domain.user.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserEntity {

    private Long userId;
    private String nickName;
    private String salt;
    private String mobile;
    private String email;
    private Date registryTime;
}
