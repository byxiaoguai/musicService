package org.system.vip.tools;

import lombok.val;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author lz
 * @date 2022/11/3 13:40
 */
public class QQEncrypt {
    private static   String encNonce = "CJBPACrRuNy7";
    private static String signPrxfix = "zza";
    private static char[] dir = "0234567890abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     *
     * @param encParams 需要加密的参数，这是一段请求体数据，为json字符串格式，例如下面的格式，可以抓包获取
     * {"comm":{"ct":24,"cv":0},"vip":{"module":"userInfo…baseinfo_v2","param":{"vec_uin":[""]}}}
     *
     * @return 加密的方式为固定字串 zza加上一个10-16位的随机字符串再加上 固定字串 CJBPACrRuNy7加上请求数据拼接的 MD5值
     */
//    public  static String getSign(String encParams){
//        return signPrxfix + uuidGenerate() +  DigestUtils.md5DigestAsHex((encNonce + encParams).toByteArray(
//                StandardCharsets.UTF_8));
//    }
//
//    private static String uuidGenerate() {
//        int minLen = 10;
//        int maxLen = 16;
//        int ran = System.currentTimeMillis();
//        int ranLen = ran.nextInt(maxLen - minLen) + minLen
//        int sb = StringBuilder(ranLen)
//        for (i in 0 until ranLen) {
//            sb.append(dir[ran.nextInt(dir.size)])
//        }
//        return sb.toString()
//    }
}
