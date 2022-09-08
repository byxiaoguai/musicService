package org.system.vip.service.impl;

import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;

import org.apache.http.Consts;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.vip.common.RedisUtils;
import org.system.vip.dto.PageHelp;
import org.system.vip.entity.MiGu.*;
import org.system.vip.service.MiGuService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lz
 * @Date 2021/12/25 18:51
 * @Version 1.0
 */
@Service
public class MiGuServiceImpl implements MiGuService {


    @Resource(name = "httpClientFactoryBean")
    private CloseableHttpClient httpClient;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 咪咕
     * 请求header
     *
     * @return
     */
    private Map<String, String> getHeader() {
        Map<String, String> headers = new HashMap<>();

        headers.put("Accept", " application/json, text/plain, */*");
        headers.put("Accept-Encoding", " gzip, deflate, br");
        headers.put("Accept-Language", " zh-CN,zh;q=0.9");
        headers.put("By", (String) redisUtils.get("migubycookie"));
        headers.put("Connection", " keep-alive");
        headers.put("Cookie", (String) redisUtils.get("migucookie"));
        headers.put("DNT", " 1");
        headers.put("Host", " m.music.migu.cn");
        headers.put("Referer", " https://m.music.migu.cn/v4/search");
        headers.put("Sec-Fetch-Dest", " empty");
        headers.put("Sec-Fetch-Mode", " cors");
        headers.put("Sec-Fetch-Site", " same-origin");
        headers.put("User-Agent", "  Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36 SE 2.X MetaSr 1.0");
        return headers;
    }


    /**
     * 咪咕get请求方法
     *
     * @param url 请求地址
     * @return 返回
     * @throws Exception
     */
    private String getHttpMiGu(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        Map<String, String> header = this.getHeader();
        header.forEach((k, v) -> {
            httpGet.addHeader(k, v);
        });
        String result = httpClient.execute(httpGet, new BasicResponseHandler());
        return result;
    }

    /**
     * 咪咕post请求方法
     *
     * @param url
     * @param params 参数
     * @return
     * @throws Exception
     */
    private String postHttpMiGu(String url, String params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        Map<String, String> header = this.getHeader();
        header.forEach((k, v) -> {
            httpPost.setHeader(k, v);
        });
        httpPost.setEntity(new StringEntity(params, Consts.UTF_8));
        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }

    /**
     * 咪咕搜索
     *
     * @param pageHelp
     * @return
     */
    @Override
    public MiGuQuery getSearch(PageHelp pageHelp) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("text", pageHelp.getText());
        params.put("pageNo", pageHelp.getPageNo());
        params.put("pageSize", pageHelp.getPageSize());
//        String url = "https://m.music.migu.cn/migumusic/h5/search/song?text=" + pageHelp.getText() + "&pageNo=" + pageHelp.getPageNo() + "&pageSize=" + pageHelp.getPageSize();
        final String s = URLUtil.buildQuery(params, null);
        String url = "https://m.music.migu.cn/migumusic/h5/search/song?"+ URLUtil.encode(s);
        try {
            final String result = getHttpMiGu(url);
            return JSONUtil.toBean(result, MiGuQuery.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 咪咕歌词
     *
     * @param copyrightId 歌曲id
     * @return
     */
    @Override
    public MiGuLyric getLyric(String copyrightId) {
        String url = "https://m.music.migu.cn/migumusic/h5/song/lyric?copyrightId=" + copyrightId;
        try {
            final String result = getHttpMiGu(url);
            return JSONUtil.toBean(result, MiGuLyric.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 咪咕获取图片
     *
     * @param songId
     * @return
     */
    @Override
    public MiGuPic getPic(String songId) {
        String url = "https://m.music.migu.cn/migumusic/h5/song/pic?songId=" + songId;
        try {
            final String result = getHttpMiGu(url);
            return JSONUtil.toBean(result, MiGuPic.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取播放歌曲
     *
     * @param copyrightId
     * @return
     */
    @Override
    public MiGuSong getSong(String copyrightId, String type) {
        String url = "https://m.music.migu.cn/migumusic/h5/play/auth/getSongPlayInfo?copyrightId=" + copyrightId + "&type=" + type;
        try {
            final String result = getHttpMiGu(url);
            return JSONUtil.toBean(result, MiGuSong.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量获取歌曲信息
     *
     * @param copyrightIds
     * @return
     */
    @Override
    public MiGuSongInfo postSongInfo(List<String> copyrightIds) {
        String url = "https://m.music.migu.cn/migumusic/h5/audioplayer/auth/songs";
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("type", 1);
            params.put("copyrightId", copyrightIds);
            final String result = postHttpMiGu(url, JSONUtil.toJsonStr(params));
            return JSONUtil.toBean(result, MiGuSongInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
