package org.system.vip.entity.MiGu;

import lombok.Data;

/**
 * 播放歌曲url
 * @Author lz
 * @Date 2021/12/25 19:36
 * @Version 1.0
 */
@Data
public class MiGuSong {
    private String code;
    private String msg;
    private MiGuSongPlay data;

}
