package org.mrzhuyk.practice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@ToString
public class UserInfoVO implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "昵称")
    private String nickName;
    
    @Schema(description = "手机号码")
    private String mobile;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "注册时间")
    private Date registryTime;
}
