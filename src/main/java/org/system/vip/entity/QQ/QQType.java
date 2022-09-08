package org.system.vip.entity.QQ;

import lombok.Data;

/**
 * @Author lz
 * @Date 2022/2/28 21:09
 * @Version 1.0
 */
@Data
public class QQType {


    //音乐格式
    private String code;
     //码率
    private String kbps;
    //头
    private String head;
    //尾
    private String footer;

    public QQType setCode(String code) {
        this.code = code;
        return this;
    }

    public QQType setKbps(String kbps) {
        this.kbps = kbps;
        return this;
    }

    public QQType setHead(String head) {
        this.head = head;
        return this;
    }

    public QQType setFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public QQType(String code, String kbps, String head, String footer) {
        this.code = code;
        this.kbps = kbps;
        this.head = head;
        this.footer = footer;
    }
}
