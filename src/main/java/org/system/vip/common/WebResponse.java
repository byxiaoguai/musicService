package org.system.vip.common;

import lombok.Data;

/**
 * @author 刘政
 * @date 2020-10-28 16:34
 */
@Data
public class WebResponse<T> {
    /**
     *
     */
    private String code;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 返回数据
     */
    private T data;

    public WebResponse(String code, String errorMsg, T data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public WebResponse(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public WebResponse() {
    }

    public WebResponse(ResponseEnum responseEnum){
        this.code= responseEnum.getCode();
        this.errorMsg= responseEnum.getMessage();
    }
    public WebResponse(ResponseEnum responseEnum,T data){
        this.code= responseEnum.getCode();
        this.errorMsg= responseEnum.getMessage();
        this.data=data;
    }
}
