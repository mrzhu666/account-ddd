package org.mrzhuyk.practice.config;


import lombok.Data;

/**
 * 返回数据，响应封装
 * @param <T> 响应数据，异常时为空
 */
@Data
public class Response<T> {
    private int code;
    private String message;
    private T data;
    //业务执行成功
    public static <T> Response<T> success(T data){
        Response<T> response=new Response<>();
        response.setCode(ErrorEnum.SUCCESS.getResultCode());
        response.setMessage(ErrorEnum.SUCCESS.getResultMsg());
        response.setData(data);
        return response;
    }
    
    public static Response<String> error(int code, String message){
        Response<String> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
    
    public static Response<String> error(BaseErrorInfoInterface errorInfo) {
        return error(errorInfo.getResultCode(),errorInfo.getResultMsg());
    }
    
    public static Response<String> error(BaseErrorInfoInterface errorInfo, String msg) {
        return error(errorInfo.getResultCode(),msg);
    }
    
    public static Response<String> error(BizException biz) {
        return error(biz.getErrorCode(), biz.getErrorMsg());
    }
    
    //@Override
    //public String toString() {
    // return JSONObject.toJSONString(this);
    //}
}
