package com.weixin.util;

import com.weixin.constant.AccessTokenConstant;
import com.weixin.constant.UrlConstant;
import com.weixin.entity.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by xing on 2018/7/18.
 */
public class WeixinUtil {
    /**
     * get请求,获取微信服务器端的信息
     */
    public static JSONObject doGetStr(String url){

        DefaultHttpClient httpClient = new DefaultHttpClient();
        //发送get请求
        //GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx33d316d3520f14e4&secret=21a1ff62e7b61370011c986bd05a7367 HTTP/1.1
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            //微信服务后台执行get请求后，获取返回的信息:
            //HTTP/1.1 200 OK [Connection: keep-alive, Content-Type: application/json; encoding=utf-8, Date: Sat, 06 May 2017 03:16:31 GMT, Content-Length: 175]
            HttpResponse response = httpClient.execute(httpGet);
            //获取消息实体
            //org.apache.http.conn.BasicManagedEntity@fe39ebf
            HttpEntity entity = response.getEntity();
            if(entity != null){
                //消息实体被转换成字符串
                //{"access_token":"cJ_-GLXTDk-sAqLvAhl96RYjrLRsGEBrMppGBlfSiWokLf5uU7EWQQDixpYthLLu3pwfw76P9X3SgkvzCUY58FG0tNZeasGr-_DlXo_Ga4QsnF7WrAXFWcAdJxDcs3i0HKPfAJADGL","expires_in":7200}
                String result = EntityUtils.toString(entity,"UTF-8");
                //字符串被转换成json格式
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * post请求，将我们的参数提交到微信后台
     */
    public static JSONObject doPostStr(String url,String outStr){

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils .toString(response.getEntity(),"UTF-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 获取access_token
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token
     * 有效时间：2小时
     */
    public static AccessToken getAccessToken(){

        AccessToken accessToken = new AccessToken();
        //替换url常量里面的字符串“APPID”、“APPSECRET”
        String url = UrlConstant.ACCESS_TOKEN_URL.replace("APPID", AccessTokenConstant.APPID).replace("APPSECRET", AccessTokenConstant.APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject != null){
            accessToken.setToken(jsonObject.getString("access_token"));
            accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
        }
        return accessToken;
    }
}
