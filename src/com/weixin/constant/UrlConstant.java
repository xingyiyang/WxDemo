package com.weixin.constant;

/**
 * Created by xing on 2018/7/18.
 */
public interface UrlConstant {

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
}
