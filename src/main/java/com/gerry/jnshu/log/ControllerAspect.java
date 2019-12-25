package com.gerry.jnshu.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gerry.jnshu.exception.GlobalExceptionHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Component
@Aspect
public class ControllerAspect {
    private Logger logger= LoggerFactory.getLogger(ControllerAspect.class);
    private static final ObjectMapper mapper = new ObjectMapper().
            disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    public ControllerAspect(){}

    @AfterThrowing(throwing = "ex",pointcut = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void logAspect(JoinPoint joinPoint,Throwable ex) throws JsonProcessingException {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
//        String className = joinPoint.getTarget().getClass().getName(); //切入方法所属类名
//        String methodName = joinPoint.getSignature().getName(); //切入的方法名
        Object[] params = joinPoint.getArgs(); //目标方法传入的参数
//        logger.error("[Exception]:["+className+"]"+methodName+":" + ex.getMessage()+"[params]:"+param);
        ControllerAspect.LogContent content = new ControllerAspect.LogContent( params, ex.getMessage() , uri, ip);
        logger.error(mapper.writeValueAsString(content));

    }

    private class LogContent implements Serializable {
        private Object params;
        private String error;
        private String url;
        private String ip;
        LogContent(Object[] params, String error, String url, String ip) {
            this.params = this.ignoreServletObjectInArgs(params);
            this.error = error;
            this.url = url;
            this.ip = ip;
        }
        public Object getParams() {
            return params;
        }
        public String getError() {
            return error;
        }
        public String getUrl() {
            return url;
        }
        public String getIp() {
            return ip;
        }
        private Object ignoreServletObjectInArgs(Object[] params) {
            if (params == null || params.length == 0) {
                return null;
            } else {
                List args = new ArrayList();
                int i = 0;
                for(int l = params.length; i < l; ++i) {
                    Object arg = params[i];
                    if (!(arg instanceof ServletRequest) && !(arg instanceof ServletResponse)) {
                        args.add(arg);
                    }
                }
                return args.size() == 1 ? args.get(0) : args;
            }
        }
    }
}
