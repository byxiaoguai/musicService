package org.system.vip.entity.QQ;


import lombok.Data;
import org.system.vip.dto.Song;

import java.util.List;

/**
 * @Author lz
 * @Date 2022/2/27 22:56
 * @Version 1.0
 */
@Data
public class QQQuery {

    //歌曲
    private List<Song> songList;
    //总数
    private  Integer total;
}
