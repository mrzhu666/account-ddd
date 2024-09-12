package org.mrzhuyk.practice.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
public class UserLoginCmd {

    @Schema(description = "手机号码",example = "13123456789")
    @Pattern(regexp = "^(1(3|4|5|7|8|9)\\d{9})?$",message = "请输入正确的手机号码格式")
    private String mobile;
    
    @Schema(description = "邮箱",example = "12345678901@qq.com")
    @Pattern(regexp = "^(([a-zA-Z0-9_\\-\\.\\+]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?))?$",message = "请输入正确的邮箱格式")
    private String email;
    
    @NotBlank(message = "请输入手机号码或邮箱")
    private String getMobileOrEmail() {
        return StringUtils.isBlank(mobile) ? email : mobile;
    }
    
    @Schema(description = "密码",example = "12345678aA!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[a-zA-Z\\d@#$%^&+=!]{8,20}$",message = "密码请输入8到20位数字、字母、特殊字符组合")
    private String password;
    
}
