package org.system.vip.service.impl;

import org.springframework.stereotype.Service;
import org.system.vip.dto.PageHelp;
import org.system.vip.service.NetEaseService;

/**
 * @Author lz
 * @Date 2022/11/1 23:34
 * @Version 1.0
 */
@Service
public class NetEaseServiceImpl implements NetEaseService {




    /**
     * 获取音乐
     *
     * @param pageHelp
     */
    @Override
    public void getSearch(PageHelp pageHelp) {

    }

    /**
     * 获取歌曲
     *
     * @param id
     */
    @Override
    public String getSong(String id) {
        return null;
    }

    /**
     * 获取歌词
     *
     * @param id 歌曲id
     */
    @Override
    public String getLyric(String id) {
        return null;
    }
}
