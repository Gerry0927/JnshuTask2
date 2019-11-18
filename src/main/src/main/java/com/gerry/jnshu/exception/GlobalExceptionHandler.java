package com.gerry.jnshu.exception;

import com.gerry.jnshu.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler{

        private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
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
        public Result<String>  handleHttpMediaTypeNotSupportedException(Exception e) {
            logger.error("不支持当前媒体类型", e);
            return Result.failed("content_type_not_supported");
        }

        /**
         * 500 - Internal Server Error
         */
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        @ExceptionHandler(Exception.class)
        public Result<String>  handleException(Exception e) {
            if (e instanceof CustomException){
                return Result.failed(e.getMessage());
            }

            logger.error("服务运行异常", e);
            e.printStackTrace();
            return Result.failed("server_error");
        }

}
