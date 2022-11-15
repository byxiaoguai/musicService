package org.system.vip;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.github.crab2died.ExcelUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.system.vip.tools.HttpsTools;
import org.system.vip.tools.JsonRootBean;

import javax.xml.ws.soap.Addressing;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class VipApplicationTests {

    @Autowired
    private HttpsTools httpsTools;
    @Test
    void contextLoads() throws Exception{

        HttpRequest get = HttpUtil.createGet("https://t.me/al_cloud/904?embed=1&mode=true");
        get.setHttpProxy("127.0.0.1",10809);
        HttpResponse execute = get.execute();
        String body = execute.body();
        System.out.println(body);

        Document parse = Jsoup.parse(body);
        Element body1 = parse.body();

        //内容
        Elements elementsByClass = body1.getElementsByClass("tgme_widget_message_text js-message_text");

        System.out.println(elementsByClass.text());


    }

}
