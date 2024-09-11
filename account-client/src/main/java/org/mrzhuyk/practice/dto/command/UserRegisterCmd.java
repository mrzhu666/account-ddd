package org.mrzhuyk.practice.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@ToString
public class UserRegisterCmd {
    
    @Schema(description = "真实姓名",example = "张三")
    private String realName;
    
    @Schema(description = "昵称",example = "user1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    
    @Schema(description = "手机号码，可以为空，但手机号码和邮箱不能同时为空",example = "13123456789")
    @Pattern(regexp = "^(1(3|4|5|7|8|9)\\d{9})?$",message = "请输入正确的手机号码格式")
    private String mobile;
    
    @Schema(description = "邮箱，可以为空，但手机号码和邮箱不能同时为空",example = "12345678901@qq.com")
    @Pattern(regexp = "^(([a-zA-Z0-9_\\-\\.\\+]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?))?$",message = "请输入正确的邮箱格式")
    private String email;
    
    @NotBlank(message = "手机号码和邮箱不能同时为空")
    private String getMobileOrEmail() {
        return StringUtils.isBlank(mobile) ? email : mobile;
    }
    
    @Schema(description = "密码，8到20位数字、字母、特殊字符组合",example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[a-zA-Z\\d@#$%^&+=!]{8,20}$",message = "请输入8到20位数字、字母、特殊字符组合")
    private String password;
    
}
