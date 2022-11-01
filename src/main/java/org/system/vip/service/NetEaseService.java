package org.system.vip.service;

import org.system.vip.dto.PageHelp;

/**
 * 网易云音乐
 * @Author lz
 * @Date 2022/11/1 23:34
 * @Version 1.0
 */
public interface NetEaseService {


    /**
     * 获取音乐
     * @param pageHelp
     */
    void getSearch(PageHelp pageHelp);


    /**
     * 获取歌曲
     * @param id

     */
    String getSong(String id);

    /**
     * 获取歌词
     * @param id 歌曲id
     *
     */
    String getLyric(String id);
}
