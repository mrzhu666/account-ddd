package org.mrzhuyk.practice.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterCmd {
    
    @Schema(description = "真实姓名",example = "张三")
    private String realName;
    
    @Schema(description = "昵称",example = "user1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickName;
    
    @Schema(description = "手机号码，和邮箱两者必须有一个",example = "12345678901")
    private String mobile;
    
    @Schema(description = "邮箱，和手机号码两者必须有一个",example = "12345678901@qq.com")
    private String email;
    
    @Schema(description = "密码，8到20位数字和字母组合",example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
