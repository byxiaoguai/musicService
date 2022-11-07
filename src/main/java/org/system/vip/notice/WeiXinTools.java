package org.system.vip.notice;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;

/**
 * 微信通知
 */
public class WeiXinTools {


    /**
     * 获取Access_Token
     * @return
     */
    private static String getAccess_Token(){
        //获取token
        String s = HttpUtil.get(" https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww58672c6ebb59ee96&corpsecret=hdeuq_wEvqT8f_7trV7LZ8RQ_KpSj1cliGUdw67tdOg");
        //errcode 0  errmsg ok
//        System.out.println(JSON.parseObject(s).getString("access_token"));
        return JSON.parseObject(s).getString("access_token");
    }

    /**
     * 发送消息
     * @param msg 消息
     */
    public static void sendMsgText(String msg){
        String access_token = getAccess_Token();

        //发送消息
       HttpUtil.post("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + access_token,
                "{\n" +
                        "   \"touser\" : \"LiuZheng\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"老大有消息来了!\n通知内容:"+msg+"\n通知时间:"+DateUtil.date()+"\"\n" +
                        "   },\n" +
                        "   \"safe\":0,\n" +
                        "   \"enable_id_trans\": 0,\n" +
                        "   \"enable_duplicate_check\": 0,\n" +
                        "   \"duplicate_check_interval\": 1800\n" +
                        "}");
    }


    public static void main(String[] args) {
        //获取token
//        String s = HttpUtil.get(" https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww58672c6ebb59ee96&corpsecret=hdeuq_wEvqT8f_7trV7LZ8RQ_KpSj1cliGUdw67tdOg");
//       //errcode 0  errmsg ok
//        System.out.println(JSON.parseObject(s).getString("access_token"));
//
//        //发送消息
//        String access_token = HttpUtil.post("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + JSON.parseObject(s).getString("access_token"),
//                "{\n" +
//                        "   \"touser\" : \"LiuZheng\",\n" +
//                        "   \"msgtype\" : \"text\",\n" +
//                        "   \"agentid\" : 1000002,\n" +
//                        "   \"text\" : {\n" +
//                        "       \"content\" : \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
//                        "   },\n" +
//                        "   \"safe\":0,\n" +
//                        "   \"enable_id_trans\": 0,\n" +
//                        "   \"enable_duplicate_check\": 0,\n" +
//                        "   \"duplicate_check_interval\": 1800\n" +
//                        "}");

        sendMsgText("123123123");
    }
}
