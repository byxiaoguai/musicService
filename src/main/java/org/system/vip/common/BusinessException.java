package org.system.vip.common;

/**
 * @author 刘政
 * @date 2020-10-28 16:37
 */
public class BusinessException extends BaseException{
    public BusinessException(ResponseEnum code, String msg) {
        super(code, msg);
    }
}
