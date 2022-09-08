package org.system.vip.common;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lz
 * @Date 2021/12/27 20:47
 * @Version 1.0
 */

public enum MiGuTypeEnum {
    FULL_SONG("fullSong", "1", "40kbps", "mp3"),
    HQ("hq", "2", "320kbps", "mp3"),
    SQ("sq", "3", "997kbps", "flac"),
    BIT24("bit24", "4", "1813kbps", "flac"),
    ;


    /**
     * crbt 000004
     * ring  000018
     * <p>
     * fullSong 000009       1  MP3  40kbps
     * walkMan 000019
     * hq 020010         2  MP3  320kbps
     * sq 011002     3  flac   无损997kbps
     * bit24 011005 4  flac    无损 1813kbps
     * d3 020024  5   wav
     */

    MiGuTypeEnum(String code, String type, String kbps, String suffix) {
        this.code = code;
        this.type = type;
        this.kbps = kbps;
        this.suffix = suffix;
    }

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

    /**
     * 获取所有歌曲码率
     * @param miGuTypeEnums 集合
     * @return
     */
    public static List<MusicCode> getFull(List<MiGuTypeEnum>  miGuTypeEnums){
       return miGuTypeEnums.stream().map(miGuTypeEnum -> {
            return new MusicCode(miGuTypeEnum.getCode(), miGuTypeEnum.getType(),miGuTypeEnum.getKbps(), miGuTypeEnum.getSuffix());
        }).collect(Collectors.toList());
    }

    public String getCode() {
        return code;
    }

    public MiGuTypeEnum setCode(String code) {
        this.code = code;
        return this;
    }

    public String getType() {
        return type;
    }

    public MiGuTypeEnum setType(String type) {
        this.type = type;
        return this;
    }

    public String getKbps() {
        return kbps;
    }

    public MiGuTypeEnum setKbps(String kbps) {
        this.kbps = kbps;
        return this;
    }

    public String getSuffix() {
        return suffix;
    }

    public MiGuTypeEnum setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }
}
