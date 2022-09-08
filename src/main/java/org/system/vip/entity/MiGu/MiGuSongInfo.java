package org.system.vip.entity.MiGu;

import lombok.Data;

import java.util.List;

/**
 * 歌曲信息
 * @Author lz
 * @Date 2021/12/25 21:25
 * @Version 1.0
 */
@Data
public class MiGuSongInfo {

    private String code;
    private String msg;
    private List<SongInfo> data;
}
