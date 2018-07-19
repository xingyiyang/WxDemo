package com.weixin.util;

import com.weixin.constant.TransConstant;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xing on 2018/7/18.
 * 百度翻译工具
 */
public class TransUtil {
    protected static final int SOCKET_TIMEOUT = 10000; // 10S

    /**
     * 把请求的参数q/from/to/appid/salt/sign存入map中
     */
    public static Map<String, String> buildParams(String q, String from, String to)
            throws UnsupportedEncodingException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("q", q);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", TransConstant.APP_ID);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = TransConstant.APP_ID + q + salt + TransConstant.SECURITY_KEY;
        params.put("sign", MD5Util.md5(src));

        return params;
    }

    /**
     * 把参数加入url字符串中，以“？_&_”的方式将请求的参数传到服务器端
     */
    public static String getUrlWithQueryString(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null) { // 过滤空的key
                continue;
            }

            if (i != 0) {
                builder.append('&');
            }

            builder.append(key);
            builder.append('=');
            builder.append(encode(value));

            i++;
        }

        return builder.toString();
    }

    /**
     * 调用百度翻译的接口
     */
    public static String translateConect(String host, Map<String, String> params){

        try {
            // 设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { myX509TrustManager }, null);
            String sendurl = getUrlWithQueryString(host, params);

            //发送请求
            URL url = new URL(sendurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslcontext.getSocketFactory());
            }

            conn.setConnectTimeout(SOCKET_TIMEOUT); // 设置相应超时
            conn.setRequestMethod("GET");
            int statusCode = conn.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Http错误码：" + statusCode);
            }

            //读取服务器数据
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }

            String translateresult = builder.toString();

            close(br); // 关闭数据流
            close(is); // 关闭数据流
            conn.disconnect(); // 断开连接

            return translateresult;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//		String url = getUrlWithQueryString(host, params);
//		JSONObject jsonObject = WeixinUtil.doGetStr(url);
//		String result = null;
//		if(jsonObject!=null){
//			System.out.println(jsonObject);
//			result = jsonObject.toString();
//		}
        return null;
    }

    /**
     * 获取返回的翻译结果
     */
    public static String getTranslateResult(String q, String from, String to){

        StringBuffer sbf = new StringBuffer();
        try {
            Map<String, String> params = buildParams(q, from, to);
            String result = translateConect(TransConstant.BAIDU_TRANS_URL, params);
            JSONObject jsonObject = JSONObject.fromObject(result);
            sbf.append("原文："+q+"\n");
            sbf.append("译文："+jsonObject.getJSONArray("trans_result").getJSONObject(0).get("dst"));
            return sbf.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源
     */
    protected static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置utf-8编码，防止中文乱码
     * @param input
     * @return
     */
    public static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return input;
    }

    private static TrustManager myX509TrustManager = new X509TrustManager() {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };
}
