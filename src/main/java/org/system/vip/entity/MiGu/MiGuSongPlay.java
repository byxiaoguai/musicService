package org.system.vip.entity.MiGu;

import lombok.Data;

/**
 * 咪咕歌曲
 * @Author lz
 * @Date 2021/12/25 19:37
 * @Version 1.0
 */
@Data
public class MiGuSongPlay {

    private String playUrl;
    private String formatId;
    private String salePrice;
    private String bizType;
    private String bizCode;
    private int auditionsLength;
}
