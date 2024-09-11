package org.mrzhuyk.practice.exception;


import lombok.extern.slf4j.Slf4j;
import org.mrzhuyk.practice.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕捉
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获业务错误
     */
    @ExceptionHandler(value = BizException.class)
    public Response<String> bizExceptionHandler(BizException biz) {
        
        log.error("业务异常：{}", biz.getErrorMsg()); // 日志记录，用于复查
        // 获取抛出异常的堆栈信息
        StackTraceElement[] stackTrace = biz.getStackTrace();
        if (stackTrace.length > 0) {
            StackTraceElement element = stackTrace[0];
            log.error("异常发生在类：{}，方法：{}，行号：{}",
                element.getClassName(),
                element.getMethodName(),
                element.getLineNumber());
        }
        
        // 打印完整堆栈跟踪
        biz.printStackTrace();
        return Response.error(biz);
    }
    
    /**
     * 捕获其它所有异常
     */
    @ExceptionHandler(value = Exception.class)
    public Response<String> exceptionHandler(Exception e) {
        log.error("服务内部异常：", e);
        return Response.error(ErrorEnum.INTERNAL_SERVER_ERROR, "服务器异常：" + e.getMessage());
    }
    
}
