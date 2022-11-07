package org.system.vip.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.vip.common.MusicCode;
import org.system.vip.common.RedisUtils;
import org.system.vip.dto.PageHelp;
import org.system.vip.dto.Play;
import org.system.vip.dto.Song;
import org.system.vip.entity.QQ.*;
import org.system.vip.service.QQService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * qq音乐接口
 *
 * @Author lz
 * @Date 2022/2/27 22:19
 * @Version 1.0
 */
@Service
public class QQServiceImpl implements QQService {

    @Resource(name = "httpClientFactoryBean")
    private CloseableHttpClient httpClient;

    @Autowired
    private RedisUtils redisUtils;
    /**
     * QQ
     * 请求header
     *
     * @return
     */
    private Map<String, String> getHeader() {
        Map<String, String> headers = new HashMap<>();

        headers.put("Referer", "http://y.qq.com");
        headers.put("Cookie", "RK=FHdNpp3AHX; ptcz=5c14b40a26368359eff30983d50188e887cc77a60261db66b53032e82bc12296; tvfe_boss_uuid=8bce243ddc545c77; pgv_pvid=1575923751; fqm_pvqid=fcca50c0-c183-4fdb-8553-32a85e58508c; ts_uid=1140315804; tmeLoginType=2; euin=NeSloe4qNKnk; ts_refer=ADTAGmyqq; eas_sid=H1J616Y7Q711s0L0R3f6j2O9l4; livelink_pvid=6596087808; o_cookie=877059905; _clck=3878074388|1|f6d|0; pgv_info=ssid=s2616072308; vversion_name=8.2.95; video_omgid=f3e40a759fad437e; _qpsvr_localtk=0.36387294563059336; ptui_loginuin=877059905; fqm_sessionid=a7dca7af-2a1e-411b-8dd0-4a097345774b; ts_last=y.qq.com/; login_type=1; wxunionid=; wxopenid=; qm_keyst=Q_H_L_58y2YHFDB5BO8Zf9HczAq3TjNoduAK5S68PB8J8VOpIXXlkM2yNQPOA; qqmusic_key=Q_H_L_58y2YHFDB5BO8Zf9HczAq3TjNoduAK5S68PB8J8VOpIXXlkM2yNQPOA; qm_keyst=Q_H_L_58y2YHFDB5BO8Zf9HczAq3TjNoduAK5S68PB8J8VOpIXXlkM2yNQPOA; psrf_qqunionid=4AF1CB565882B8632377891522BDA6D8; psrf_qqaccess_token=E742AC63771C08742140504BD170B498; psrf_musickey_createtime=1667828712; psrf_access_token_expiresAt=1675604712; uin=877059905; psrf_qqopenid=0C6C678CBD44A7F48641D135AD3E3917; wxrefresh_token=; psrf_qqrefresh_token=DAA9645A7F04E83B67CD866188DF7731");
        headers.put("User-Agent", "QQ%E9%9F%B3%E4%B9%90/54409 CFNetwork/901.1 Darwin/17.6.0 (x86_64)");
        headers.put("Accept", "*/*");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,gl;q=0.6,zh-TW;q=0.4");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

    /**
     * QQget请求方法
     *
     * @param url 请求地址
     * @return 返回
     * @throws Exception
     */
    private String getHttpQQ(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        Map<String, String> header = this.getHeader();
        header.forEach((k, v) -> {
            httpGet.addHeader(k, v);
        });
//         String ip = "125.112.159.24";
//        if (redisUtils.hasKey("ip")) {
//            ip = (String) redisUtils.get("ip");
//        }
//         Integer port = 4231;
//        if (redisUtils.hasKey("port")) {
//            port = (Integer) redisUtils.get("port");
//        }
        //也可以设置超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(10000) // 设置连接超时时间
//                .setSocketTimeout(10000) // 设置读取超时时间
//                .setExpectContinueEnabled(false).setProxy(new HttpHost(ip, port, "http"))//设置代理IP、端口
//                .setCircularRedirectsAllowed(true) // 允许多次重定向
//                .build();
//
//        httpGet.setConfig(requestConfig);
        String result = httpClient.execute(httpGet, new BasicResponseHandler());
        return result;
    }

    /**
     * QQpost请求方法
     *
     * @param url
     * @param params 参数
     * @return
     * @throws Exception
     */
    private String postHttpQQ(String url, String params) throws Exception {
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
     * QQ获取搜索结果
     *
     * @param pageHelp
     */
    @Override
    public QQQuery getSearch(PageHelp pageHelp) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("format", "json");
        params.put("aggr", "1");
        params.put("cr", "1");
        params.put("lossless", "1");
        params.put("new_json", "1");
        params.put("p", pageHelp.getPageNo());
        params.put("n", pageHelp.getPageSize());
        params.put("w", pageHelp.getText());

//        params.put("data", JSONObject.toJSONString(qqBean));
        final String s = URLUtil.buildQuery(params, null);
        String url = "https://shc.y.qq.com/soso/fcgi-bin/client_search_cp?" + URLUtil.encode(s);

        try {
            final String result = this.getHttpQQ(url);
            final JSONObject jsonObject = JSONObject.parseObject(result);
            final JSONObject data = jsonObject.getJSONObject("data");
            final JSONObject song = data.getJSONObject("song");
            final JSONArray list = song.getJSONArray("list");
            List<Song> songs = new ArrayList<>();
            for (Object o : list) {
                JSONObject jo = (JSONObject) o;
                Song song1 = new Song();
                song1.setId(jo.getString("mid"));
                song1.setPic("https://y.gtimg.cn/music/photo_new/T002R300x300M000" + jo.getJSONObject("album").getString("mid") + ".jpg?max_age=2592000");
                song1.setName(jo.getString("name"));
                final JSONArray singer = jo.getJSONArray("singer");
                List<String> singers = new ArrayList<>();
                for (Object o1 : singer) {
                    JSONObject jo1 = (JSONObject) o1;
                    singers.add(jo1.getString("name"));
                }
                song1.setSinger(String.join(",", singers));

                final JSONObject file = jo.getJSONObject("file");
//                array( 'size_flac', 999, 'F000', 'flac' ),
//                        array( 'size_320mp3', 320, 'M800', 'mp3' ),
//                        array( 'size_192aac', 192, 'C600', 'm4a' ),
//                        array( 'size_128mp3', 128, 'M500', 'mp3' ),
//                        array( 'size_96aac', 96, 'C400', 'm4a' ),
//                        array( 'size_48aac', 48, 'C200', 'm4a' ),
//                        array( 'size_24aac', 24, 'C100', 'm4a' ),
                if (file.getString("size_flac") != null) {
                    song1.getKbpsList().add(new MusicCode("size_flac", "size_flac", "999", "flac"));
                }
                if (file.getString("size_320mp3") != null) {
                    song1.getKbpsList().add(new MusicCode("size_320mp3", "size_320mp3", "320", "mp3"));
                }
//                if (file.getString("size_192aac") != null) {
//                    song1.getKbpsList().add(new MusicCode("size_192aac", "size_192aac", "192", "m4a"));
//                }
                if (file.getString("size_128mp3") != null) {
                    song1.getKbpsList().add(new MusicCode("size_128mp3", "size_128mp3", "128", "mp3"));
                }
//                if (file.getString("size_96aac") != null) {
//                    song1.getKbpsList().add(new MusicCode("size_96aac", "size_96aac", "96", "m4a"));
//                }
//                if (file.getString("size_48aac") != null) {
//                    song1.getKbpsList().add(new MusicCode("size_48aac", "size_48aac", "48", "m4a"));
//                }
//                if (file.getString("size_24aac") != null) {
//                    song1.getKbpsList().add(new MusicCode("size_24aac", "size_24aac", "24", "m4a"));
//                }
                //song1.setKbpsList();


                songs.add(song1);
            }
            QQQuery qqQuery = new QQQuery();
            qqQuery.setSongList(songs);
            qqQuery.setTotal(song.getInteger("totalnum"));
            return qqQuery;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * v2 搜索接口
     *
     * @param pageHelp
     * @return
     */
    @Override
    public QQQuery getSearchV2(PageHelp pageHelp) {
        String url="https://u.y.qq.com/cgi-bin/musicu.fcg?_webcgikey=DoSearchForQQMusicDesktop&_="+System.currentTimeMillis();
        int max = 999999999, min = 100000000;
        long randomNum = System.currentTimeMillis();
        int ran3 = (int) (randomNum % (max - min) + min);
        String parm="{\"comm\":{\"g_tk\":"+ran3+",\"uin\":0,\"format\":\"json\",\"inCharset\":\"utf-8\",\"outCharset\":\"utf-8\",\"notice\":0,\"platform\":\"h5\",\"needNewCode\":1,\"ct\":23,\"cv\":0},\"req_0\":{\"method\":\"DoSearchForQQMusicDesktop\",\"module\":\"music.search.SearchCgiService\",\"param\":{\"remoteplace\":\"txt.mqq.all\",\"searchid\":\""+System.currentTimeMillis()+"\",\"search_type\":0,\"query\":\""+ pageHelp.getText()+"\",\"page_num\":"+pageHelp.getPageNo()+",\"num_per_page\":"+pageHelp.getPageSize()+"}}}";


        try {
            String result = HttpUtil.post(url, parm);
             JSONObject jsonObject = JSONObject.parseObject(result);
             JSONObject req_0 = jsonObject.getJSONObject("req_0");
             JSONObject data = req_0.getJSONObject("data");
            JSONObject body = data.getJSONObject("body");
            JSONObject song = body.getJSONObject("song");
             JSONArray list = song.getJSONArray("list");
            List<JSONObject> jsonObjects = list.toJavaList(JSONObject.class);
            List<Song> songs = new ArrayList<>();
            for (JSONObject jo : jsonObjects) {

                Song song1 = new Song();
                song1.setId(jo.getString("mid"));
                song1.setPic("https://y.gtimg.cn/music/photo_new/T002R300x300M000" + jo.getJSONObject("album").getString("mid") + ".jpg");//?max_age=2592000
                song1.setName(jo.getString("name"));
                final JSONArray singer = jo.getJSONArray("singer");
                List<String> singers = new ArrayList<>();
                for (Object o1 : singer) {
                    JSONObject jo1 = (JSONObject) o1;
                    singers.add(jo1.getString("name"));
                }
                JSONObject album = jo.getJSONObject("album");
                String name = album.getString("name");
                singers.add("《"+name+"》");

                song1.setSinger(String.join(" - ", singers));

                final JSONObject file = jo.getJSONObject("file");
//                array( 'size_flac', 999, 'F000', 'flac' ),
//                        array( 'size_320mp3', 320, 'M800', 'mp3' ),
//                        array( 'size_192aac', 192, 'C600', 'm4a' ),
//                        array( 'size_128mp3', 128, 'M500', 'mp3' ),
//                        array( 'size_96aac', 96, 'C400', 'm4a' ),
//                        array( 'size_48aac', 48, 'C200', 'm4a' ),
//                        array( 'size_24aac', 24, 'C100', 'm4a' ),
                if (file.getString("size_flac") != null) {
                    song1.getKbpsList().add(new MusicCode("size_flac", "size_flac", "999", "flac"));
                }
                if (file.getString("size_320mp3") != null) {
                    song1.getKbpsList().add(new MusicCode("size_320mp3", "size_320mp3", "320", "mp3"));
                }
                if (file.getString("size_192aac") != null) {
                    song1.getKbpsList().add(new MusicCode("size_192aac", "size_192aac", "192", "m4a"));
                }
                if (file.getString("size_128mp3") != null) {
                    song1.getKbpsList().add(new MusicCode("size_128mp3", "size_128mp3", "128", "mp3"));
                }
                if (file.getString("size_96aac") != null) {
                    song1.getKbpsList().add(new MusicCode("size_96aac", "size_96aac", "96", "m4a"));
                }
                if (file.getString("size_48aac") != null) {
                    song1.getKbpsList().add(new MusicCode("size_48aac", "size_48aac", "48", "m4a"));
                }
                if (file.getString("size_24aac") != null) {
                    song1.getKbpsList().add(new MusicCode("size_24aac", "size_24aac", "24", "m4a"));
                }
                //song1.setKbpsList();


                songs.add(song1);
            }
            QQQuery qqQuery = new QQQuery();
            qqQuery.setSongList(songs);
//            qqQuery.setTotal(data.getJSONObject("meta").getInteger("curpage"));
            return qqQuery;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * 获取歌曲
     *
     * @param mid
     */
    @Override
    public String getSong(String mid) {
        String url = "https://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg?format=json&platform=yqq&songmid=" + mid;
        try {
            final String httpQQ = this.getHttpQQ(url);
            final JSONObject jsonObject = JSONObject.parseObject(httpQQ);
            final JSONArray data1 = jsonObject.getJSONArray("data");
            JSONObject file = data1.getJSONObject(0).getJSONObject("file");
            return file.getString("media_mid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 获取播放
     *
     * @param mid
     */
    @Override
    public Play getPlay(String mid, String code) {
        HashMap<String, QQType> type = new HashMap<>();

        type.put("size_flac", new QQType("size_flac", "999", "F000", "flac"));
        type.put("size_320mp3", new QQType("size_320mp3", "320", "M800", "mp3"));
        type.put("size_192aac", new QQType("size_192aac", "192", "C600", "m4a"));
        type.put("size_128mp3", new QQType("size_128mp3", "128", "M500", "mp3"));
        type.put("size_96aac", new QQType("size_96aac", "96", "C400", "m4a"));
        type.put("size_48aac", new QQType("size_48aac", "48", "C200", "m4a"));
        type.put("size_24aac", new QQType("size_24aac", "24", "C100", "m4a"));

        //随机数
        int max = 999999999, min = 100000000;
        long randomNum = System.currentTimeMillis();
        int ran3 = (int) (randomNum % (max - min) + min);

        Integer guid = ran3;
        String uid = "0";
        final String song = this.getSong(mid);

        QQSongBean qqBean = new QQSongBean();
        Req_0 req_0 = new Req_0();
        req_0.setMethod("CgiGetVkey");
        req_0.setModule("vkey.GetVkeyServer");
//        req_0.setMethod("GetCdnDispatch");
//        req_0.setModule("CDN.SrfCdnDispatchServer");

        Param param = new Param();
        param.setGuid(guid + "");
//        param.setGuid( "xiaoguai");
        param.setUin(uid);
        param.setLoginflag(1);
        param.setPlatform(20 + "");
        final ArrayList<String> strings = new ArrayList<>();
        strings.add(mid);
        param.setSongmid(strings);

        final ArrayList<String> strings1 = new ArrayList<>();

        final QQType qqType = type.get(code);
        strings1.add(qqType.getHead() + song + "." + qqType.getFooter());
        param.setFilename(strings1);
        final ArrayList<Integer> objects = new ArrayList<>();
        objects.add(0);
        param.setSongtype(objects);
        req_0.setParam(param);
        qqBean.setReq_0(req_0);

        String data = "{\n" +
                "    \"req_0\": {\n" +
                "        \"module\": \"vkey.GetVkeyServer\",\n" +
                "        \"method\": \"CgiGetVkey\",\n" +
                "        \"param\": {\n" +
                "            \"guid\": \"" + guid + "\",\n" +
                "            \"songmid\": [\n" +
                "                \"" + mid + "\",\n" +
                "                \"" + mid + "\",\n" +
                "                \"" + mid + "\",\n" +
                "                \"" + mid + "\",\n" +
                "                \"" + mid + "\",\n" +
                "                \"" + mid + "\",\n" +
                "                \"" + mid + "\"\n" +
                "            ],\n" +
                "            \"filename\": [\n" +
                "                \"F000" + song + ".flac\",\n" +
                "                \"M800" + song + ".mp3\",\n" +
                "                \"C600" + song + ".m4a\",\n" +
                "                \"M500" + song + ".mp3\",\n" +
                "                \"C400" + song + ".m4a\",\n" +
                "                \"C200" + song + ".m4a\",\n" +
                "                \"C100" + song + ".m4a\"\n" +
                "            ],\n" +
                "            \"songtype\": [\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0\n" +
                "            ],\n" +
                "            \"uin\": \"" + uid + "\",\n" +
                "            \"loginflag\": 1,\n" +
                "            \"platform\": \"20\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("format", "json");
        params.put("platform", "yqq.json");
        params.put("needNewCode", "0");
        params.put("data", JSONObject.toJSONString(qqBean));
        final String s = URLUtil.buildQuery(params, null);
        //data参数
        // {"comm" : {"_channelid" : "0","_os_version" : "6.2.9200-2","authst" : "","ct" : "19","cv" : "1873","guid" : "0","patch" : "118","psrf_access_token_expiresAt" : 0,"psrf_qqaccess_token" : "","psrf_qqopenid" : "","psrf_qqunionid" : "","tmeAppID" : "qqmusic","tmeLoginType" : 2,"uin" : "0","wid" : "0"},"queryvkey" : {"method" : "UrlGetVkey","module" : "music.vkey.GetVkey","param" : {"checklimit" : 0,"ctx" : 1,"downloadfrom" : 0,"filename" : ["RS01003LnSNo1ecdc3.flac"],"guid" : "0","nettype" : "","referer" : "y.qq.com","scene" : 0,"songmid" : ["002Y0Owp11JozK"],"songtype" : [ 1 ],"uin" : "0"}}}
        //data参数
        //{"modulevkey":{"method":"CgiGetVkey","module":"vkey.GetVkeyServer","param":{"uin":"0","filename":["F000003oKu203YsfZA.flac"],"guid":"0","songmid":["001qvvgF38HVc4"]}},"comm":{"authst":"0","qq":"0"}}

        String url = "https://u6.y.qq.com/cgi-bin/musicu.fcg?" + URLUtil.encode(s);
        try {
            final String httpQQ = this.getHttpQQ(url);
            Play play = new Play();
            final JSONObject jsonObject = JSONObject.parseObject(httpQQ);
            final JSONObject req_01 = jsonObject.getJSONObject("req_0");
            final JSONObject data1 = req_01.getJSONObject("data");

            final JSONArray midurlinfo = data1.getJSONArray("midurlinfo");
            final JSONObject jsonObject1 = midurlinfo.getJSONObject(0);

            play.setUrl(URLUtil.decode("https://aqqmusic.tc.qq.com/amobile.music.tc.qq.com/" + jsonObject1.getString("purl")));

            return play;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * qq获取歌词
     *
     * @param mid 歌曲id
     */
    @Override
    public String getLyric(String mid) {
        String url = "https://c.y.qq.com/lyric/fcgi-bin/fcg_query_lyric_new.fcg?g_tk=5381&songmid=" + mid;
        try {
            final String httpQQ = this.getHttpQQ(url);
            JSONObject jsonObject = JSONObject.parseObject(httpQQ.substring(18, httpQQ.length() - 1));
            return Base64.decodeStr(jsonObject.getString("lyric"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试http
     *
     * @param url
     * @return
     */
    @Override
    public String testHttp(String url) throws Exception {
        return this.getHttpQQ(url);
    }

}
