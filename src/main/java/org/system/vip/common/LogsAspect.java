package org.system.vip.common;

import com.alibaba.fastjson.JSONObject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.system.vip.tools.Detect;
import org.system.vip.tools.IPUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘政
 * @date 2020-10-28 16:21
 */
@Aspect
@Configuration
public class LogsAspect {

    /**
     * 请求地址
     */
    private ThreadLocal<String> requestPath = new ThreadLocal();
    private ThreadLocal<String> requestMethod = new ThreadLocal();
    /**
     * 传入参数
     */
    private ThreadLocal<Object> inputMap = new ThreadLocal();
    /**
     * 输出结果
     */
    private ThreadLocal<Map<String, Object>> outputMap = new ThreadLocal();
    private ThreadLocal<Long> startTimeMills = new ThreadLocal();
    /**
     * 结束时间
     */
    private ThreadLocal<Long> endTimeMills = new ThreadLocal();
    private ThreadLocal<String> ip = new ThreadLocal();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * // 记录方法开始执行的时间
     */
    @Before("execution(* org.system.vip.controller..*.*(..))")
    public void doBeforeInServiceLayer() {
        startTimeMills.set(System.currentTimeMillis());
    }

    @Around("execution(* org.system.vip.controller..*.*(..))")
    public Object getReqAndResInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        NoLog annotation = method.getAnnotation(NoLog.class);
        requestMethod.set(request.getMethod());
        inputMap.set(getParam(proceedingJoinPoint));
        requestPath.set(request.getRequestURI());
        ip.set(IPUtil.getIpAddr());
        Map<String, Object> outputMaps = new HashMap(16);
        Object res;
        res = proceedingJoinPoint.proceed();
        if (res instanceof ModelAndView) {
        } else {
            if (annotation != null) {
                outputMaps.put("result", "data is too larger and no print log");
            } else {
                outputMaps.put("result", res);
            }
        }
        outputMap.set(outputMaps);
        return res;
    }

    @After("execution(* com.java.vip.controller..*.*(..))")
    public void doAfterInServiceLayer() {
        endTimeMills.set(System.currentTimeMillis());
        pringLog();
        removeThreadLocal();
    }

    /**
     * 回收ThreadLocal变量
     */
    private void removeThreadLocal() {
        requestPath.remove();
        requestMethod.remove();
        inputMap.remove();
        outputMap.remove();
        startTimeMills.remove();
        endTimeMills.remove();
        ip.remove();
    }

    /**
     * 输出日志
     */
    private void pringLog() {
        logger.info("请求IP:[{}],url:[{}],type:[{}],handle_time:{}ms,params:{},response:{}", ip.get(), requestPath.get(),
                requestMethod.get(), (endTimeMills.get() - startTimeMills.get()),
                inputMap.get(), JSONObject.toJSONString(outputMap.get()));
    }

    private String getParam(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：
                //ServletResponse不能序列化 从入参里排除，否则报异常：
                continue;
            }
            arguments[i] = args[i];
        }
        String paramter = "";
        if (Detect.notEmpty(arguments)) {
            try {
                paramter = JSONObject.toJSONString(arguments);
            } catch (Exception e) {
                paramter = arguments.toString();
            }
        }
        return paramter;
    }
}
