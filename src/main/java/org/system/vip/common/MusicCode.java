package org.system.vip.common;

import lombok.Data;

/**
 * @Author lz
 * @Date 2021/12/27 21:50
 * @Version 1.0
 */
@Data
public class MusicCode {

    /**
     * 编码
     */
    private String code;
    /**
     * 歌曲类型
     */
    private String type;
    /**
     * 码率
     */
    private String kbps;
    /**
     * 后缀
     */
    private String suffix;

    public MusicCode(String code, String type, String kbps, String suffix) {
        this.code = code;
        this.type = type;
        this.kbps = kbps;
        this.suffix = suffix;
    }
}
