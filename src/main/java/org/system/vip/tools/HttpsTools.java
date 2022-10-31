package org.system.vip.tools;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lz
 * @Date : 2019/7/23
 */
@Slf4j
@Component
public class HttpsTools {
    @Resource(name = "httpClientFactoryBean")
    CloseableHttpClient httpClient;


    /**
     * qq音乐
     *
     * @param url
     * @return 搜索列表
     * @throws IOException
     */
    public String sendGetQService(String url) throws IOException {

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Referer", "http://y.qq.com");
        httpGet.setHeader("Cookie", "RK=+HYN4p37F1; ptcz=5e6687b0a517dc90f0a5f99609cb03c82810e4bc0c2f8e6986ef7ac0f923e3ae; pvid=699630775; pgv_pvid=6861393140; fqm_pvqid=b85b860d-7454-4bd6-bbad-ceca480625da; ts_uid=3862413550; tvfe_boss_uuid=e8bb92ed24dc77c3; eas_sid=a1p6X3w1A5f08070i766E6s7T5; LW_uid=U1M6m3816570E0N0j8M8U1Y4j3; LW_sid=l1t6n3j105p0y0F2C4t5l5x9q2; pac_uid=1_877059905; yq_index=2; ptui_loginuin=565219784; tmeLoginType=2; ts_refer=ADTAGmyqq.playsong.svr_err; o_cookie=565219784; euin=NeSloe4qNKnk; uid=939661852; _qpsvr_localtk=0.4248995095901984; fqm_sessionid=76833972-9ef9-41a2-bccc-5076400db385; pgv_info=ssid=s2585095468; login_type=1; psrf_qqunionid=4AF1CB565882B8632377891522BDA6D8; qm_keyst=Q_H_L_2WlbD560eKQAgj_3CJUeo3Ev8ucmYUi3f0bVFGUzhKeASfpxKcL4m0Kse1pvRTF; qm_keyst=Q_H_L_2WlbD560eKQAgj_3CJUeo3Ev8ucmYUi3f0bVFGUzhKeASfpxKcL4m0Kse1pvRTF; psrf_musickey_createtime=1632474872; psrf_qqaccess_token=7E8CC3C9BA922E3E654271C51ED7A13E; wxrefresh_token=; psrf_qqopenid=0C6C678CBD44A7F48641D135AD3E3917; wxopenid=; psrf_access_token_expiresAt=1640250872; qqmusic_key=Q_H_L_2WlbD560eKQAgj_3CJUeo3Ev8ucmYUi3f0bVFGUzhKeASfpxKcL4m0Kse1pvRTF; psrf_qqrefresh_token=FFDEFE74A67DAC7C80ABA1FAAFFE6F87; wxunionid=; uin=877059905; ts_last=y.qq.com/n/ryqq/player");
        httpGet.setHeader("User-Agent", "QQ%E9%9F%B3%E4%B9%90/54409 CFNetwork/901.1 Darwin/17.6.0 (x86_64)");
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,gl;q=0.6,zh-TW;q=0.4");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        String result = httpClient.execute(httpGet, new BasicResponseHandler());
        return result;
    }


    /**
     * https请求(http4)
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String sendPostMapDomain(String url, Map<String, Object> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(":authority", "hk.ourui.com");
        httpPost.setHeader(":path", "/domain/search");
        httpPost.setHeader(":method", "POST");
        httpPost.setHeader(":scheme", "https");
//        httpPost.setHeader("content-length","629");
        httpPost.setHeader("sec-fetch-dest", "empty");
        httpPost.setHeader("sec-fetch-mode", "cors");
        httpPost.setHeader("sec-fetch-site", "same-origin");
        httpPost.setHeader("origin", "https://hk.ourui.com");
        httpPost.setHeader("Referer", "https://hk.ourui.com/domain/search");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36 SE 2.X MetaSr 1.0");
        httpPost.setHeader("Cookie", "ourui##ouruiid=QoP0xwpvUREUjzMo%2FCxZeSILIktRFlz26sfSgCtPYpvWXO95kCkCOjs12dhsm%2BEC");
        httpPost.addHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryJZFGUMWZeKASBV4N");
//        httpPost.addHeader("Accept-Language", "zh-cn");
//        httpPost.addHeader("Accept-Encoding", "gzip, deflate");
        if (params != null) {
            List<NameValuePair> nvps = getNameValuePairArr(params);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }
        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }

    public String sendPostMapjz(String url, Map<String, Object> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
//        httpPost.setHeader(":authority", "hk.ourui.com");

        httpPost.setHeader("Host", " webapi.jiazhengye.cn");
        httpPost.setHeader("User-Agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:105.0) Gecko/20100101 Firefox/105.0");
        httpPost.setHeader("Accept", " application/json, text/plain, */*");
        httpPost.setHeader("Accept-Language", " zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        httpPost.setHeader("Accept-Encoding", " gzip, deflate, br");
        httpPost.setHeader("Content-Type", " application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader("token", " f723e63f0eb3dc0563503dd40bff7898");
        httpPost.setHeader("Origin", "https://www.jiazhengye.cn");
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("Referer", " https://www.jiazhengye.cn/");
        httpPost.setHeader("Sec-Fetch-Dest", " empty");
        httpPost.setHeader("Sec-Fetch-Mode", " cors");
        httpPost.setHeader("Sec-Fetch-Site", " same-site");
        httpPost.setHeader("TE", " trailers");

        //也可以设置超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(10000) // 设置连接超时时间
//                .setSocketTimeout(10000) // 设置读取超时时间
//                .setExpectContinueEnabled(false).setProxy(new HttpHost("110.85.30.52", 4212, "http"))//设置代理IP、端口
//                .setCircularRedirectsAllowed(true) // 允许多次重定向
//                .build();
//
//        httpPost.setConfig(requestConfig);


        if (params != null) {
            List<NameValuePair> nvps = getNameValuePairArr(params);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }


        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }


    public String sendPostMapsfj(String url, Map<String, Object> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);


        httpPost.setHeader("Host", "webapi.jiazhengye.cn");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:105.0) Gecko/20100101 Firefox/105.0");
        httpPost.setHeader("Accept", "application/json, text/plain, */*");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        httpPost.setHeader("Accept-Encoding", " gzip, deflate, br");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader("token", "f723e63f0eb3dc0563503dd40bff7898");

        httpPost.setHeader("Origin", " https://www.jiazhengye.cn");
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("Referer", " https://www.jiazhengye.cn/");
        httpPost.setHeader("Sec-Fetch-Dest", " empty");
        httpPost.setHeader("Sec-Fetch-Mode", "cors");
        httpPost.setHeader("Sec-Fetch-Site", " same-site");
        httpPost.setHeader("TE", "trailers");
        //也可以设置超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(10000) // 设置连接超时时间
//                .setSocketTimeout(10000) // 设置读取超时时间
//                .setExpectContinueEnabled(false).setProxy(new HttpHost("117.28.32.144", 4212, "http"))//设置代理IP、端口
//                .setCircularRedirectsAllowed(true) // 允许多次重定向
//                .build();
//
//        httpPost.setConfig(requestConfig);


        if (params != null) {
            List<NameValuePair> nvps = getNameValuePairArr(params);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }
        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }



    public String sendPostMapUpload(String url, Map<String, Object> params, File file) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("HOST", "upload.ctfile.com");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:105.0) Gecko/20100101 Firefox/105.0");
        httpPost.setHeader("Content-Type", "multipart/form-data");
        httpPost.setHeader("Content-Disposition", "form-data;filesize="+params.get("filesize")+";name="+params.get("name"));
//        if (params != null) {
//            List<NameValuePair> nvps = getNameValuePairArr(params);
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//        }

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        //设置浏览器兼容模式
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //设置请求的编码格式
        multipartEntityBuilder.setCharset(Consts.UTF_8);
        multipartEntityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA);

        multipartEntityBuilder.addBinaryBody("file",file);
//
//        ContentType strContent = ContentType.create("text/plain", Charset.forName("UTF-8"));
//        params.forEach((s, o) -> {
//            multipartEntityBuilder.addTextBody(s,o,strContent);
//        });

        httpPost.setEntity(multipartEntityBuilder.build());
        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }

    public String sendPostMap(String url, Map<String, Object> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        //httpPost.addHeader("Host","dy.mkxiu.cn");
        //httpPost.addHeader("Upgrade-Insecure-Requests","1");
        httpPost.addHeader("Connection", "keep-alive");
        //httpPost.addHeader("Referer","http://sp.6080jx.com/?url=https://v.youku.com/v_show/id_XNDI3OTc1MzQyNA==.html");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpPost.addHeader("Accept-Language", "zh-cn");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate");
        if (params != null) {
            List<NameValuePair> nvps = getNameValuePairArr(params);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }
        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }


    public HttpResult sendPostSteamLoginMap(String url, Map<String, Object> params, String cookies) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Host", "store.steampowered.com");
        httpPost.addHeader("Origin", "https://store.steampowered.com");
        httpPost.addHeader("Cookie", cookies);
        httpPost.addHeader("Referer", "https://store.steampowered.com/login/?redir=&redir_ssl=1&snr=1_4_4__global-header");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        if (httpPost.getFirstHeader("Set-Cookie") != null) {
            String cookie = httpPost.getFirstHeader("Set-Cookie").getValue();
            httpPost.addHeader("cookie", cookie);
        }
        if (params != null) {
            List<NameValuePair> nvps = getNameValuePairArr(params);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }
        HttpContext localContext = new BasicHttpContext();
        BasicCookieStore basicCookieStore = new BasicCookieStore();
        // Cookie存储
        localContext.setAttribute("http.cookie-store", basicCookieStore);
        HttpResponse httpResponse = httpClient.execute(httpPost, localContext);
        HttpResult httpResult = new HttpResult(localContext, httpResponse);
        return httpResult;


    }


    public String sendPostSteamLoginKey(String url, Map<String, Object> params, String cookies) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Host", "store.steampowered.com");
        httpPost.addHeader("Origin", "https://store.steampowered.com");
        httpPost.addHeader("Cookie", cookies);
        httpPost.addHeader("Referer", "https://store.steampowered.com/login/?redir=&redir_ssl=1&snr=1_4_4__global-header");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        if (params != null) {
            List<NameValuePair> nvps = getNameValuePairArr(params);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }
        // Cookie存储
        String execute = httpClient.execute(httpPost, new BasicResponseHandler());
        return execute;

    }


    public String sendGetSteamCookie(String url) throws IOException {

        HttpGet httpGet = new HttpGet(url);
        HttpContext localContext = new BasicHttpContext();
        BasicCookieStore basicCookieStore = new BasicCookieStore();
        localContext.setAttribute("http.cookie-store", basicCookieStore);
        HttpResponse httpResponse = httpClient.execute(httpGet, localContext);
        String cookies = "";
        for (Cookie cookie : basicCookieStore.getCookies()) {
            cookies += cookie.getName() + "=" + cookie.getValue() + ";";
        }
        HttpResult httpResult = new HttpResult(localContext, httpResponse);
        return cookies;
    }

    public String sendGetIeltsNeeaCookie(String url) throws IOException {

        HttpGet httpGet = new HttpGet(url);
        HttpContext localContext = new BasicHttpContext();
        BasicCookieStore basicCookieStore = new BasicCookieStore();
        localContext.setAttribute("http.cookie-store", basicCookieStore);
        HttpResponse httpResponse = httpClient.execute(httpGet, localContext);
        System.out.println(basicCookieStore.getCookies());
        String cookies = "";
        for (Cookie cookie : basicCookieStore.getCookies()) {
            cookies += cookie.getName() + "=" + cookie.getValue() + ";";
        }
        HttpResult httpResult = new HttpResult(localContext, httpResponse);
        return cookies;
    }

    public String sendGet(String url) throws IOException {

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Upgrade-Insecure-Requests", "1");
        httpGet.addHeader("Connection", "keep-alive");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0");
        String result = httpClient.execute(httpGet, new BasicResponseHandler());
        return result;
    }


    /**
     * qq音乐搜索 音乐(pc)
     *
     * @param url
     * @return 搜索列表
     * @throws IOException
     */
    public String sendGetQqMusic(String url) throws IOException {

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(":authority", "c.y.qq.com");
        httpGet.setHeader(":method", "GET");
        httpGet.setHeader(":path", "/splcloud/fcgi-bin/smartbox_new.fcg?_=1630472945503&cv=4747474&ct=24&format=json&inCharset=utf-8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=1&uin=0&g_tk_new_20200303=134799000&g_tk=134799000&hostUin=0&is_xml=0&key=%E6%97%B6%E9%97%B4%E6%B2%A6%E9%99%B7");
        httpGet.setHeader(":scheme", "https");
        httpGet.setHeader("accept", "application/json");
        httpGet.setHeader("accept-encoding", "gzip, deflate, br");
        httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("cookie", "RK=eGYFpJ3KSX; ptcz=77ae33f4139de351dd880f0f8445729a1cbe5b586e93d37b19d77582e177dbe1; tvfe_boss_uuid=a60b825140534460; pgv_pvid=6904562843; o_cookie=877059905; ptui_loginuin=2894211101; fqm_pvqid=cdf348b0-39f6-41ea-8323-170765c2f24d; ts_uid=9413573956; pgv_info=ssid=s8430532506; verifysession=h0132c8d5d23d957a83d059ad3c65d4ca577ae5d38c49c6396f4567ad48048e709dbd9d69bc69724b36; midas_openid=294145355716050C52B622D939BDB076; midas_openkey=FFDEE31728B97C13F2504C6306C10009; _qpsvr_localtk=0.1313248094534165; qqmusic_key=@gWNK6y7kk; qqmusic_fromtag=6; qqmusic_uin=0877059905; rv2=80EFA1322C5838D25030D8E60E4236B1937F1B5A6DAF81E724; property20=436FDC0F820A89D39DF2108BBDBD1209B3F4FF494E08159668C20BF3799C32487B8637218EB9F71A; fqm_sessionid=5d684810-df54-4948-8a2e-549a1a469da7; ts_last=y.qq.com/n/ryqq/search");
        httpGet.setHeader("origin", "https://y.qq.com");
        httpGet.setHeader(" referer", "https://y.qq.com/n/ryqq/search?w=%E6%97%B6%E9%97%B4%E6%B2%A6%E9%99%B7&t=song");
        httpGet.setHeader("sec-fetch-dest", "empty");
        httpGet.setHeader("sec-fetch-mode", "cors");
        httpGet.setHeader("sec-fetch-site", "same-site");
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36 SE 2.X MetaSr 1.0");
        String result = httpClient.execute(httpGet, new BasicResponseHandler());
        return result;
    }

    /**
     * 获取QQ音乐播放地址(pc)
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String sendPostMapQqMusic(String url, String params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(":authority", "u.y.qq.com");
        httpPost.addHeader(":method", "POST");
        httpPost.addHeader(":path", "/cgi-bin/musics.fcg?_=1630476303319&sign=zzbbda2fea2qngdmmlk6psohxbcgxjkqedc56474");
        httpPost.addHeader(":scheme", "https");
        httpPost.addHeader("accept", "application/json");
        httpPost.addHeader("accept-encoding", "gzip, deflate, br");
        httpPost.addHeader("accept-language", "zh-CN,zh;q=0.9");
        httpPost.addHeader("content-length", " 986");
        httpPost.addHeader("content-type", " application/x-www-form-urlencoded");
        httpPost.addHeader("cookie", "RK=eGYFpJ3KSX; ptcz=77ae33f4139de351dd880f0f8445729a1cbe5b586e93d37b19d77582e177dbe1; tvfe_boss_uuid=a60b825140534460; pgv_pvid=6904562843; o_cookie=877059905; ptui_loginuin=2894211101; fqm_pvqid=cdf348b0-39f6-41ea-8323-170765c2f24d; ts_uid=9413573956; pgv_info=ssid=s8430532506; verifysession=h0132c8d5d23d957a83d059ad3c65d4ca577ae5d38c49c6396f4567ad48048e709dbd9d69bc69724b36; midas_openid=294145355716050C52B622D939BDB076; midas_openkey=FFDEE31728B97C13F2504C6306C10009; _qpsvr_localtk=0.1313248094534165; qqmusic_uin=0877059905; qqmusic_fromtag=6; rv2=80EFA1322C5838D25030D8E60E4236B1937F1B5A6DAF81E724; property20=436FDC0F820A89D39DF2108BBDBD1209B3F4FF494E08159668C20BF3799C32487B8637218EB9F71A; fqm_sessionid=5d684810-df54-4948-8a2e-549a1a469da7; tmeLoginType=2; euin=NeSloe4qNKnk; psrf_access_token_expiresAt=1638251253; psrf_musickey_createtime=1630475253; qqmusic_key=Q_H_L_2K-4l460eWUfM-Ib8A3uGrCS2ZWqpsmhPUkBUx7NOjXOP0FuJUhby3uhtKxyEbE; ts_last=y.qq.com/n/ryqq/songDetail/001C9F503mB1X9");
        httpPost.addHeader("origin", " https://y.qq.com");
        httpPost.addHeader("referer", " https://y.qq.com/n/ryqq/player");
        httpPost.addHeader("sec-fetch-dest", " empty");
        httpPost.addHeader("sec-fetch-mode", " cors");
        httpPost.addHeader("sec-fetch-site", " same-site");
        httpPost.addHeader("user-agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36 SE 2.X MetaSr 1.0");
//        if (params != null) {
//            List<NameValuePair> nvps = getNameValuePairArr(params);
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//        }
        httpPost.setEntity(new StringEntity(params, Consts.UTF_8));
        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }

    /**
     * 获取QQ音乐播放地址(手机版)
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String sendPostMapQqMusicM(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(":authority", "i.y.qq.com");
        httpGet.setHeader(":method", " GET");
        httpGet.setHeader(" :path", " /v8/playsong.html?songmid=003nmVg11v6GCc&ADTAG=myqq&from=myqq&channel=10007100");
        httpGet.setHeader(":scheme", " https");
        httpGet.setHeader(" accept", " text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpGet.setHeader("accept-encoding", " gzip, deflate, br");
        httpGet.setHeader("accept-language", " zh-CN,zh;q=0.9");
        httpGet.setHeader("cache-control", " max-age=0");
        httpGet.setHeader("cookie", " RK=eGYFpJ3KSX; ptcz=77ae33f4139de351dd880f0f8445729a1cbe5b586e93d37b19d77582e177dbe1; tvfe_boss_uuid=a60b825140534460; pgv_pvid=6904562843; o_cookie=877059905; ptui_loginuin=2894211101; fqm_pvqid=cdf348b0-39f6-41ea-8323-170765c2f24d; ts_uid=9413573956; pgv_info=ssid=s8430532506; verifysession=h0132c8d5d23d957a83d059ad3c65d4ca577ae5d38c49c6396f4567ad48048e709dbd9d69bc69724b36; midas_openid=294145355716050C52B622D939BDB076; midas_openkey=FFDEE31728B97C13F2504C6306C10009; _qpsvr_localtk=0.1313248094534165; qqmusic_fromtag=6; qqmusic_uin=0877059905; rv2=80EFA1322C5838D25030D8E60E4236B1937F1B5A6DAF81E724; property20=436FDC0F820A89D39DF2108BBDBD1209B3F4FF494E08159668C20BF3799C32487B8637218EB9F71A; fqm_sessionid=5d684810-df54-4948-8a2e-549a1a469da7; tmeLoginType=2; euin=NeSloe4qNKnk; psrf_access_token_expiresAt=1638251253; psrf_musickey_createtime=1630475253; qqmusic_key=Q_H_L_2K-4l460eWUfM-Ib8A3uGrCS2ZWqpsmhPUkBUx7NOjXOP0FuJUhby3uhtKxyEbE; ts_last=y.qq.com/; ts_refer=ADTAGmyqq; ts_uid=9413573956; ts_refer=ADTAGmyqq; ts_last=i.y.qq.com/v8/playsong.html");
        httpGet.setHeader("sec-fetch-dest", " document");
        httpGet.setHeader("sec-fetch-mode", " navigate");
        httpGet.setHeader("sec-fetch-site", " none");
        httpGet.setHeader("sec-fetch-user", " ?1");
        httpGet.setHeader("upgrade-insecure-requests", " 1");
        httpGet.setHeader("user-agent", " Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        String result = httpClient.execute(httpGet, new BasicResponseHandler());
        return result;
    }

//    public String sendGetSteamHistory(String url, SteamUser steamUser) throws IOException {
//
//        HttpGet httpGet = new HttpGet(url);
//        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
//        httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
//        httpGet.addHeader("Cache-Control", "max-age=0");
//        httpGet.addHeader("Connection", "keep-alive");
//        httpGet.addHeader("Cookie", steamUser.getCookie() + "steamRememberLogin=" + steamUser.getSteamRememberLogin() + ";steamLoginSecure=" + steamUser.getSteamLoginSecure() + ";");
//        httpGet.addHeader("Host", "store.steampowered.com");
//        httpGet.addHeader("Referer", "https://store.steampowered.com/account/");
//        httpGet.addHeader("Upgrade-Insecure-Requests", "1");
//        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
//        String result = httpClient.execute(httpGet, new BasicResponseHandler());
//        System.out.println(result);
//        return result;
//    }

    public String sendPostEooMap(String url, Map<String, Object> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(":authority", "www.eeo.cn");
        httpPost.addHeader(":method", "POST");
        httpPost.addHeader(":path", "/saasajax/course.ajax.php?action=getCourseList");
        httpPost.addHeader(":scheme", "https");

        httpPost.addHeader("accept", "application/json, text/plain, */*");
        httpPost.addHeader("accept-encoding", "gzip, deflate, br");
        httpPost.addHeader("accept-language", "zh-CN,zh;q=0.9");
        httpPost.addHeader("cache-control", "no-cache");
        //httpPost.addHeader("content-length","17");

        httpPost.addHeader("cookie", "Hm_lvt_f1d3b48491a1ba98688c81e330d19117=1602485611; _eeos_uid=27280962; _eeos_username=27280962; _eeos_useraccount=16607116404; _eeos_userlogo=%2Fimages%2Fuser.png; _eeos_traffic=8NmZJImkx4u9B8i6ISGBFS66tn113Lu6; _eeos_sub=1; _eeos_sid=21194154; _eeos_sub=1; _eeos_sid=21194154; PHPSESSID=7uchkio4usfhvt6lnub7q4m2o5");
        httpPost.addHeader("dnt", "1");
        httpPost.addHeader("origin", "https://console.eeo.cn");
        httpPost.addHeader("pragma", "no-cache");
        httpPost.addHeader("referer", "https://console.eeo.cn/saas/school/index.html");

        httpPost.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36 SE 2.X MetaSr 1.0");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpPost.addHeader("Accept-Language", "zh-cn");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate");
        if (params != null) {
            List<NameValuePair> nvps = getNameValuePairArr(params);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }
        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }

    public String sendPostNgMap(String url, Map<String, Object> params, String type) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(":authority", "o-api.vcinema.cn");
        httpPost.addHeader(":method", "POST");
        httpPost.addHeader(":path", "/v5.0/partner_activity/create_order?is_wap=1");
        httpPost.addHeader(":scheme", "https");

        httpPost.addHeader("accept", "application/json, text/plain, */*");
        httpPost.addHeader("accept-encoding", "gzip, deflate, br");
        httpPost.addHeader("accept-language", "zh-CN,zh;q=0.9");
        httpPost.addHeader("cache-control", "no-cache");
        //httpPost.addHeader("content-length","17");

        //httpPost.addHeader("cookie","Hm_lvt_f1d3b48491a1ba98688c81e330d19117=1602485611; _eeos_uid=27280962; _eeos_username=27280962; _eeos_useraccount=16607116404; _eeos_userlogo=%2Fimages%2Fuser.png; _eeos_traffic=8NmZJImkx4u9B8i6ISGBFS66tn113Lu6; _eeos_sub=1; _eeos_sid=21194154; _eeos_sub=1; _eeos_sid=21194154; PHPSESSID=7uchkio4usfhvt6lnub7q4m2o5");
        httpPost.addHeader("dnt", "1");
        httpPost.addHeader("origin", "https://h5.vcinema.cn");
        httpPost.addHeader("pragma", "no-cache");
        httpPost.addHeader("referer", "https://h5.vcinema.cn/h5/partner/meituan/index.html?type=" + type);

        httpPost.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36 SE 2.X MetaSr 1.0");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpPost.addHeader("Accept-Language", "zh-cn");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate");
        if (params != null) {
            List<NameValuePair> nvps = getNameValuePairArr(params);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }
        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }


    private List<NameValuePair> getNameValuePairArr(Map<String, Object> parasMap) {
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, Object> parasEntry : parasMap.entrySet()) {
            String parasName = parasEntry.getKey();
            Object value = parasEntry.getValue();
            if (value instanceof List) {
                List dataList = (List) value;
                for (Object listEle : dataList) {
                    nvps.add(new BasicNameValuePair(parasName, listEle.toString()));
                }
            } else {
                nvps.add(new BasicNameValuePair(parasName, parasEntry.getValue() != null ? parasEntry.getValue().toString() : null));
                if (parasEntry.getValue() == null) {
                    log.warn("发送http请求 参数:{} value为空", parasName);
                }
            }
        }
        return nvps;
    }

    private List<NameValuePair> getNameValuePairArr2(Map<String, String> parasMap) {
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> parasEntry : parasMap.entrySet()) {
            String parasName = parasEntry.getKey();
            Object value = parasEntry.getValue();
            if (value instanceof List) {
                List dataList = (List) value;
                for (Object listEle : dataList) {
                    nvps.add(new BasicNameValuePair(parasName, listEle.toString()));
                }
            } else {
                nvps.add(new BasicNameValuePair(parasName, parasEntry.getValue() != null ? parasEntry.getValue().toString() : null));
                if (parasEntry.getValue() == null) {
                    log.warn("发送http请求 参数:{} value为空", parasName);
                }
            }
        }
        return nvps;
    }


    /**
     * 网易云搜索
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String sendPostCloudSearch(String url, Map<String, String> params) throws IOException {

        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("Referer", "https://music.163.com/");
        httpPost.addHeader("Cookie", "appver=8.2.30; os=iPhone OS; osver=15.0; EVNSM=1.0.0; buildver=2206; channel=distribution; machineid=iPhone13.3");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 CloudMusic/0.1.1 NeteaseMusic/8.2.30");
        // httpPost.addHeader('X-Real-IP', );
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8,gl;q=0.6,zh-TW;q=0.4");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        if (params != null) {
            List<NameValuePair> nvps = getNameValuePairArr2(params);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }
        String result = httpClient.execute(httpPost, new BasicResponseHandler());
        return result;
    }
}



