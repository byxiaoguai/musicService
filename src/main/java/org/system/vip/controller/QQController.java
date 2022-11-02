package org.system.vip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.system.vip.common.NoLog;
import org.system.vip.common.ResponseEnum;
import org.system.vip.common.ResponseMessage;
import org.system.vip.dto.PageHelp;
import org.system.vip.dto.Play;
import org.system.vip.entity.QQ.QQQuery;
import org.system.vip.service.QQService;

import java.util.HashMap;
import java.util.Map;

/**
 * qq音乐接口
 *
 * @Author lz
 * @Date 2022/2/27 22:18
 * @Version 1.0
 */
@RequestMapping("/v1/qq")
@RestController
public class QQController {

    @Autowired
    private QQService qqService;
    /**
     * 测试限流注解，下面配置说明该接口 60秒内最多只能访问 10次，保存到redis的键名为 limit_test，
     */
    /**
     * QQ搜索
     *
     * @param pageHelp
     * @return
     */
    @NoLog
    @GetMapping("/getQQSearch")
    public ResponseMessage getSearch(PageHelp pageHelp) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
            final QQQuery search = qqService.getSearchV2(pageHelp);
            if (search == null  || search.getSongList() == null) {
                responseMessage.setData(new HashMap<String, Object>() {{
                    put("total", null);
                    put("items", null);
                }});
                return responseMessage;
            }

            Map<String, Object> params = new HashMap<>();
            params.put("total", search.getTotal());
            params.put("items", search.getSongList());
            responseMessage.setData(params);


        return responseMessage;
    }


    /**
     * 获取歌词
     *
     * @param id
     * @return
     */
    @NoLog
    @GetMapping("/getQQLyric")
    public ResponseMessage getLyric(String id) {
        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
        responseMessage.setData(qqService.getLyric(id));
        return responseMessage;
    }


    /**
     * 获取歌曲播放
     *
     * @param id
     * @return
     */
    @NoLog
    @GetMapping("/getQQSong")
    public ResponseMessage getSong( String id,  String code) {
        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
        final Play play = qqService.getPlay(id, code);
        responseMessage.setData(play);
        return responseMessage;
    }

//    @NoLog
//    @GetMapping("/testHttp")
//    public ResponseMessage getSong( String url) throws Exception {
//        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
//        responseMessage.setData(qqService.testHttp(url));
//        return responseMessage;
//    }
}
