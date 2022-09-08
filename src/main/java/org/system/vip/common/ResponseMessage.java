package org.system.vip.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 刘政
 * @date 2020-10-28 16:15
 */
@Data
public class ResponseMessage<T> implements Serializable {

    /**
     * 1:成功 -1:失败
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


    public ResponseMessage(String code, String errorMsg, T data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public ResponseMessage(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public ResponseMessage() {
    }

    public ResponseMessage(ResponseEnum responseEnum){
        this.code= responseEnum.getCode();
        this.errorMsg= responseEnum.getMessage();
    }
}
