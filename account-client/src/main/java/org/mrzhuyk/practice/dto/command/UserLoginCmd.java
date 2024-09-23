package org.mrzhuyk.practice.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginCmd {
    
    @Schema(description = "手机/邮箱", required = true)
    @NotBlank(message = "不能为空")
    private String userAccount;
    
    @Schema(description = "密码",example = "12345678aA!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[a-zA-Z\\d@#$%^&+=!]{8,20}$",message = "密码请输入8到20位数字、字母、特殊字符组合")
    private String password;
    
}
