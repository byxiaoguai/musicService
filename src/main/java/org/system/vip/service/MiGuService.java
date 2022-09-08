package org.system.vip.service;



import org.system.vip.dto.PageHelp;
import org.system.vip.entity.MiGu.*;

import java.util.List;

/**
 * @Author lz
 * @Date 2021/12/25 18:46
 * @Version 1.0
 */

public interface MiGuService {

    /**
     * 咪咕获取搜索结果
     * @param pageHelp
     * @return MiGuQuery
     */
    MiGuQuery getSearch(PageHelp pageHelp);

    /**
     * 咪咕获取歌词
     * @param copyrightId 歌曲id
     * @return MiGuLyric
     */
    MiGuLyric getLyric(String copyrightId);

    /**
     * 咪咕获取图片
     * @param songId
     * @return
     */
    MiGuPic getPic(String songId);

    /**
     * 获取播放歌曲
     * @param copyrightId
     * @return
     */
    MiGuSong getSong(String copyrightId, String type);

    /**
     * 批量获取歌曲信息
     * @param copyrightIds
     * @return
     */
    MiGuSongInfo postSongInfo(List<String> copyrightIds);
}
