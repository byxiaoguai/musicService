package org.system.vip.common;

/**
 * @author 刘政
 * @date 2020-10-28 16:15
 */
public enum ResponseEnum implements ResponseCode{

    REQUEST_SUCCESS("200","请求成功"),

    /**
     * 4000+请求异常
     */
    REQUEST_BADREQUEST("400","请求参数错误"),


    /**
     * 请求数据异常
     */
    REQUEST_ERROR_PARAM("401","请求数据异常"),


    /**
     *  服务器异常
     */
    SYSTEM_ERROR("500","服务器错误(￢_￢)智商三岁"),

    /**
     *  限流
     */
    LIMIT_ERROR("501","大哥手轻点,我疼!");

    private String code;
    private String message;

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
