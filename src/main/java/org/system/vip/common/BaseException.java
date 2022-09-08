package org.system.vip.common;

/**
 * @author 刘政
 * @date 2020-10-28 16:36
 */
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = -3778081859201642996L;

    private String code;

    private String msg;

    protected BaseException() {
    }

    protected BaseException(ResponseEnum code, String msg) {
        super(code+"="+msg);
        this.code = code.getCode();
        this.msg = msg;
    }

    protected BaseException(String code, String msg) {
        super(code+"="+msg);
        this.code = code;
        this.msg = msg;
    }

    protected BaseException(String code, String msg,Throwable e) {
        super(msg,e);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
