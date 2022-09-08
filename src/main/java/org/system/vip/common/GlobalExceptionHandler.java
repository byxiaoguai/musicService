package org.system.vip.common;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.system.vip.tools.DateUtil;

import javax.servlet.http.HttpServletRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Set;

/**
 *   全局异常捕获处理
 *   不允许aop和controller捕获异常,所有异常都不允许捕获,统一至这里处理
 * @author 刘政
 * @date 2020-10-28 16:09
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

   /* *
     * 所有验证异常捕获处理
     *
     * @param request
     * @return*/
//    @ResponseBody
//    @ExceptionHandler(value = {BindException.class, ConstraintViolationException.class})
//    public Object validationExceptionHandler(HttpServletRequest request, Exception e) {
//        ResponseMessage resultBean = new ResponseMessage(ResponseEnum.REQUEST_BADREQUEST);
//        ConstraintViolationException exs = (ConstraintViolationException) e;
//
//        Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (ConstraintViolation<?> item : violations) {
//            stringBuilder.append(item.getMessage()+", ");
//        }
//        resultBean.setErrorMsg(stringBuilder.toString());
//        return resultBean;
//    }
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BadRequestException.class)
    public Object badRequestException(BadRequestException e) {

        return new ResponseMessage(ResponseEnum.LIMIT_ERROR);
    }
   /* *
     * 未知的异常捕获处理
     *
     * @param request
     * @param exception
     * @return*/

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object allUnknowExceptionHandler(HttpServletRequest request, Exception exception) {
        String error = logError(request, exception);
        return new ResponseMessage(ResponseEnum.SYSTEM_ERROR);

    }



    private String logError(HttpServletRequest request, Exception exception) {
        logger.error("发生未知异常:", exception);
        StringWriter sw = new StringWriter();
        sw.append(String.format("Date:{%s};\n", DateUtil.dateToString(new Date())));
        sw.append(String.format("url:{%s}产生错误;\n", request.getRequestURI()));
        sw.append(String.format("请求IP:{%s};\n", request.getRemoteAddr()));
        sw.append(String.format("type:{%s};\n", request.getMethod()));
        sw.append(String.format("请求参数:{%s};\n", JSONObject.toJSONString(request.getParameterMap())));
        //原始错误信息
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    /**
     * 所有验证异常捕获处理
     *
     * @param request
     * @return

    @ExceptionHandler(value = {BindException.class, ConstraintViolationException.class})
    @ResponseBody
    public Object validationExceptionHandler(HttpServletRequest request, Exception e) {
        ResponseMessage resultBean = new ResponseMessage(ResponseEnum.REQUEST_BADREQUEST);
        if(e instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            StringBuilder stringBuilder = new StringBuilder();
            for (ConstraintViolation<?> item : violations) {
                stringBuilder.append(item.getMessage()+", ");
            }
            resultBean.setErrorMsg(stringBuilder.toString());
            return resultBean;
        }if(e instanceof  BindException){
            BindException exs = (BindException) e;
            BindingResult bindingResult = exs.getBindingResult();
            resultBean.setErrorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return resultBean;
        }
        return new Exception("参数校验异常...请稍候重新尝试");
    }

    *
     * 未知的异常捕获处理
     *
     * @param request
     * @param exception
     * @return


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object allUnknowExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        logError(request, exception);
        if(exception instanceof BusinessException){
            BusinessException businessException =    (BusinessException)exception;
            return   new WebResponse<>(businessException.getCode(),businessException.getMsg());
        }
        return   new WebResponse(ResponseEnum.SYSTEM_ERROR);
    }*/






}
