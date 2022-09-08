package org.system.vip.entity.MiGu;

import lombok.Data;

import java.util.List;

/**
 * 咪咕
 *
 * @Author lz
 * @Date 2021/12/25 21:26
 * @Version 1.0
 */
@Data
public class SongInfo {

    private String songId;
    private String songName;
    private List<Singers> singers;
    private List<Albums> albums;
    private String copyrightId;
    private String length;
    private int crbt;
    private int ringtone;
    private int fullsong;
    private int walkman;
    private int inDigitalAlbum;
    private int vipFlag;
    private int auditionsFlag;
    private int auditionsLength;
    private int allowDownload;
    private List<String> formats;
    private List<String> mvList;
}
