package org.system.vip.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;
import org.system.vip.dto.PageHelp;
import org.system.vip.service.NetEaseService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lz
 * @Date 2022/11/1 23:34
 * @Version 1.0
 */
@Service
public class NetEaseServiceImpl implements NetEaseService {

    @Resource(name = "httpClientFactoryBean")
    private CloseableHttpClient httpClient;


    /**
     * QQ
     * 请求header
     *
     * @return
     */
    private Map<String, String> getHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Referer", "https://music.163.com/");
        headers.put("Cookie", "appver=8.2.30; os=iPhone OS; osver=15.0; EVNSM=1.0.0; buildver=2206; channel=distribution; machineid=iPhone13.3");
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 CloudMusic/0.1.1 NeteaseMusic/8.2.30");
        headers.put("Accept", "*/*");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,gl;q=0.6,zh-TW;q=0.4");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

    /**
     * NetEaseget请求方法
     *
     * @param url 请求地址
     * @return 返回
     * @throws Exception
     */
    private String getHttpNetEase(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        Map<String, String> header = this.getHeader();
        header.forEach((k, v) -> {
            httpGet.addHeader(k, v);
        });
        String result = httpClient.execute(httpGet, new BasicResponseHandler());
        return result;
    }

    /**
     * NetEasepost请求方法
     *
     * @param url
     * @param params 参数
     * @return
     * @throws Exception
     */
    private String postHttpNetEase(String url, String params) throws Exception {
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
     * 获取音乐
     *
     * @param pageHelp
     */
    @Override
    public void getSearch(PageHelp pageHelp)  throws Exception{
        String url="http://music.163.com/api/search/pc";
//        类型(歌曲：1、专辑：10、歌手：100、歌单：1000、用户：1002、mv：1004)
        final String httpNetEase = this.getHttpNetEase(url+"?s="+pageHelp.getText()+"&offset="+pageHelp.getPageNo()+"&limit="+pageHelp.getPageSize()+"&type=1");

    }

    /**
     * 获取歌曲
     *
     * @param id
     */
    @Override
    public String getSong(String id,String code) throws Exception {
        String url="http://music.163.com/api/song/enhance/player/url";
//        歌曲码率，可选值有64000,128000,198000,320000
        final String httpNetEase = this.getHttpNetEase(url+"?id="+id+"&ids=["+id+"]&br="+code);
        return null;
    }

    /**
     * 获取歌词
     *
     * @param id 歌曲id
     */
    @Override
    public String getLyric(String id) throws Exception{
        String url = "http://music.163.com/api/song/media";
        final String httpNetEase = this.getHttpNetEase(url + "?id=" + id);
        return JSONObject.parseObject(httpNetEase).getString("lyric");
    }
}
