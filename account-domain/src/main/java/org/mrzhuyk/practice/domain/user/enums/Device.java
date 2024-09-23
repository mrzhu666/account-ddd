package org.mrzhuyk.practice.domain.user.enums;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
@Slf4j
@ToString
public enum Device {
    MOBILE(1, "移动端"),
    WEB(2, "网页端"),
    ;
    
    private final int type;
    private final String desc;
    
    public static Device getByType(int type) {
        for (Device device : Device.values()) {
            if (type == device.type) {
                return device;
            }
        }
        return null;
    }
    
    
    /**
     * 获取设备类型
     *
     * @param request http request
     * @return 设备类型
     */
    public static Device getDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isBlank(userAgent)) {
            return Device.WEB;
        }
        userAgent = userAgent.toLowerCase();
        log.info("http header User-Agent : {}", userAgent);
        if (userAgent.contains("android")
            || userAgent.contains("mobile")
            || userAgent.contains("ipad")
            || userAgent.contains("iphone")
            || userAgent.contains("ipod")
            || userAgent.contains("silk")) {
            return Device.MOBILE;
        } else {
            return Device.WEB;
        }
    }
}
