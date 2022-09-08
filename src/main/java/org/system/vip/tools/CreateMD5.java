package org.system.vip.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author lz
 * @Date 2021/12/27 23:10
 * @Version 1.0
 */
public class CreateMD5 {

    public static String getMd5(String plainText , boolean rawOutPut) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            if(rawOutPut){
                return buf.toString().substring(8, 24);
            }


            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
}
