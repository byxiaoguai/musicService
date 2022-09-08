package org.system.vip.service;


import org.system.vip.dto.PageHelp;
import org.system.vip.dto.Play;
import org.system.vip.entity.QQ.QQQuery;

/**
 * qq音乐
 * @Author lz
 * @Date 2022/2/27 22:19
 * @Version 1.0
 */
public interface QQService {


    /**
     * QQ获取搜索结果
     * @param pageHelp
     */
    QQQuery getSearch(PageHelp pageHelp);

    /**
     * v2 搜索接口
     * @param pageHelp
     * @return
     */
    QQQuery getSearchV2(PageHelp pageHelp);
    /**
     * 获取歌曲
     * @param mid

     */
    String getSong(String mid);


    /**
     * 获取播放
     * @param mid
     */
    Play getPlay(String mid, String code);
    /**
     * qq获取歌词
     * @param mid 歌曲id
     *
     */
    String getLyric(String mid);

    /**
     * 测试http
     * @param url
     * @return
     */
    String testHttp(String url) throws Exception;
}
