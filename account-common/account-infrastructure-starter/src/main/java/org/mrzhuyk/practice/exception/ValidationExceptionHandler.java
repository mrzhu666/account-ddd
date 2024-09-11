package org.mrzhuyk.practice.exception;


import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.mrzhuyk.practice.Response;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 参数校验异常捕捉
 */
@Slf4j
@Order(Integer.MIN_VALUE) // 最大优先级
@RestControllerAdvice
public class ValidationExceptionHandler {
    /**
     * 参数检验异常
     */
    @ExceptionHandler(value = BindException.class)
    public Response<String> bindExceptionHandler(BindException e) {
        log.info("========BindException======");
        log.error(e.getMessage(), e);
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ObjectError objectError : e.getAllErrors()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中
            detailMessage.append(objectError.getDefaultMessage());
        }
        return Response.error(ErrorEnum.PARAMS_ERROR, "参数校检失败：" + detailMessage.toString());
    }
    
    /**
     * RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     * 兼容Validation校验框架：参数效验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response<String> parameterExceptionHandler(MethodArgumentNotValidException e) {
        log.info("========MethodArgumentNotValidException=======");
        log.info(e.getMessage(), e);
        BindingResult binding = e.getBindingResult();

        //有错误信息，则返回错误信息。无则返回默认错误信息
        if (binding.hasErrors()) {
            StringBuilder builder = new StringBuilder("参数校检失败：");
            //拼接校检错误信息
            for (FieldError error : binding.getFieldErrors()) {
                builder.append(error.getField())
                    .append(":")
                    .append(error.getDefaultMessage())
                    .append("，");
            }
            log.info(builder.toString());
            //return Response.error(ErrorEnum.PARAMS_ERROR,builder.toString());
            // 返回第一个检验失败的参数
            return Response.error(ErrorEnum.PARAMS_ERROR,binding.getFieldErrors().get(0).getDefaultMessage());
        }
        return Response.error(ErrorEnum.PARAMS_ERROR);
    }
    
    /**
     *  RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     *  兼容Validation校验框架：参数效验异常
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Response<String> handleConstraintViolationException(ConstraintViolationException e){
        log.info("========ConstraintViolationException=======");
        log.error(e.getMessage(), e);
        //return Response.error(ErrorEnum.PARAMS_ERROR, "参数校检失败："+e.getMessage());
        return Response.error(ErrorEnum.PARAMS_ERROR, e.getMessage());
    }
    

    
    /**
     * 兼容Validation校验框架：缺少请求体异常处理器
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response<String> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.info("========HttpMessageNotReadableException=======");
        log.error(e.getMessage(), e);
        return Response.error(ErrorEnum.PARAMS_ERROR, "参数体不能为空");
    }

}
