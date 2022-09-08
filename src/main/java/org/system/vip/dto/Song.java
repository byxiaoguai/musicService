package org.system.vip.dto;


import lombok.Data;
import org.system.vip.common.MusicCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 歌曲模板
 * @Author lz
 * @Date 2021/12/25 21:48
 * @Version 1.0
 */
@Data
public class Song {

    /**
     * 歌曲id
     */
    private String id;

    /**
     * 歌曲名称
     */
    private String name;
    /**
     * 歌手作者名称
     */
    private String singer;


    private List<MusicCode> kbpsList=new ArrayList<>();

    /**
     * 歌曲图片
     */
    private String pic;
}
