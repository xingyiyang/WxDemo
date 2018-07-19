package com.weixin.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 校验signature
 * Created by xing on 2018/7/18.
 */
public class CheckServerUtil {
    //开发者自定义的token
    private static final String TOKEN = "xyyzxh";

    /**
     * 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *  signature微信加密签名
     *  timestamp时间戳
     *  nonce随机数
     */
    public static boolean checkSignature(String signature,String timestamp,String nonce){

        //把3个参数放入一个数组中，用于排序
        String[] arr = new String[]{TOKEN,timestamp,nonce};

        //对3个字符串排序
        Arrays.sort(arr);

        //拼接3个参数成一个字符串
        StringBuffer content = new StringBuffer();
        for(int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }

        //对拼接的新字符串使用sha1加密
        String contentSha1 = getSha1(content.toString());

        //加密后的字符串与signature对比
        boolean isSignature = contentSha1.equals(signature);

        return isSignature;
    }

    /**
     * sha1加密算法
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
