package org.system.vip.task;

import cn.hutool.core.date.DateTime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.system.vip.common.RedisUtils;
import org.system.vip.dto.Play;
import org.system.vip.entity.MiGu.MiGuSong;
import org.system.vip.service.MiGuService;
import org.system.vip.service.QQService;
import org.system.vip.tools.HttpsTools;

import javax.annotation.Resource;

/**
 * 心跳定时
 *
 * @Author lz
 * @Date 2021/12/26 18:28
 * @Version 1.0
 */
@Slf4j
@Component
public class MiGuTask {

    @Resource
    private MiGuService miGuService;
    @Resource
    private QQService qqService;
    @Resource
    private HttpsTools httpsTools;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 咪咕音乐心跳
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void heartbeat() {
        MiGuSong song = miGuService.getSong("60054704083", "1");
        log.info("咪咕心跳时间:" + DateTime.now().toMsStr() + ":" + song);
    }

    /**
     * QQ音乐心跳
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void heartbeatQQ() {
        final Play size_flac = qqService.getPlay("0039MnYb0qxYhV", "size_flac");
        log.info("QQ心跳时间:" + DateTime.now().toMsStr() + ":" + size_flac);
    }


    /**
     * 定时更换代理IP
     * 每30分钟换一次IP
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void settingProxyIp() throws Exception {
//         String url="http://webapi.http.zhimacangku.com/getip?num=1&type=2&pro=0&city=0&yys=0&port=1&time=4&ts=1&ys=1&cs=1&lb=1&sb=0&pb=45&mr=1&regions=";
//         String s = httpsTools.sendGet(url);
//        final IP ip = JSON.parseObject(s, new TypeReference<IP>() {
//        });
//        final Data data = ip.getData().get(0);
//
//        redisUtils.set("ip",data.getIp(),0);
//        redisUtils.set("port",data.getPort(),0);
      //  log.info("定时更换代理IP:" +data+"时间:" + DateTime.now().toMsStr());

        redisUtils.set("load", !(boolean) redisUtils.get("load"), 0);
        log.info("定时更换接口时间:" + DateTime.now().toMsStr());
    }
}
