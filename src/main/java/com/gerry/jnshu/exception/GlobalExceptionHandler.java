package com.gerry.jnshu.exception;

import com.gerry.jnshu.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.gerry.jnshu.response.ResultCode.VALIDATE_FAILED;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Result<String> validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        }else {
            msg = "系统繁忙，请稍后重试...";
        }
        return Result.failed(-1, msg);
    }


//    /**
//     * 方法参数校验
//     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException e) {
        logger.error(e.getMessage(), e);
        String msg = e.getMessage();
        if (msg.contains("NumberFormatException")) {
            msg = "参数类型错误！";
        }
        return Result.failed(VALIDATE_FAILED.getCode(), msg);
    }


        /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return Result.failed("could_not_read_json");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return Result.failed("request_method_not_supported");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result<String> handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return Result.failed("content_type_not_supported");
    }

    /**
     * ValidationException
     */
//    @ExceptionHandler(ValidationException.class)
//    public Result<String> handleValidationException(ValidationException e) {
//        logger.error(e.getMessage(), e);
//        StringBuilder errorMsg = new StringBuilder();
//        if (e instanceof ConstraintViolationException) {
//            ConstraintViolationException exs = (ConstraintViolationException) e;
//
//            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
//            for (ConstraintViolation<?> item : violations) {
//                /**打印验证不通过的信息*/
//                errorMsg.append(item.getMessage()).append("\n");
//            }
//
//        }
//        else{
//           errorMsg = new StringBuilder(e.getMessage());
//        }
//        return Result.failed(VALIDATE_FAILED.getCode(), errorMsg.toString());
//    }
//
    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        if (e instanceof CustomException) {
            return Result.failed(e.getMessage());
        }

        logger.error("服务运行异常", e);
        e.printStackTrace();
        return Result.failed("server_error");
    }


    /**
     *

    Spring常见异常及HTTP状态码
    BindException
    400 - Bad Request

    ConversionNotSupportedException
    500 - Internal Server Error

    HttpMediaTypeNotAcceptableException
    406 - Not Acceptable

    HttpMediaTypeNotSupportedException
    415 - Unsupported Media Type

    HttpMessageNotReadableException
    400 - Bad Request

    HttpMessageNotWritableException
    500 - Internal Server Error

    HttpRequestMethodNotSupportedException
    405 - Method Not Allowed

    MethodArgumentNotValidException
    400 - Bad Request

    MissingServletRequestParameterException
    400 - Bad Request

    MissingServletRequestPartException
    400 - Bad Request

    NoSuchRequestHandlingMethodException
    404 - Not Found

    TypeMismatchException
    400 - Bad Request

    ConstraintViolationException
     400 - Bad Request
    */

}
