package com.weixin.util;

import com.weixin.constant.MessageConstant;
import com.weixin.constant.UrlConstant;
import com.weixin.menu.Button;
import com.weixin.menu.ClickButton;
import com.weixin.menu.Menu;
import com.weixin.menu.ViewButton;
import com.weixin.template.MenuTemplate;
import net.sf.json.JSONObject;

/**
 * Created by xing on 2018/7/18.
 */
public class MenuUtil {

    /**
     * 组装菜单
     */
    public static Menu initMenu(){

        Menu menu = new Menu();
        menu.setButton(new Button[]{MenuTemplate.buttonMenuLeft(),MenuTemplate.buttonMenuMiddle(),MenuTemplate.buttonMenuRight()});
        return menu;
    }

    /**
     * 调用微信菜单接口创建菜单
     */
    public static int createMenu(String token, String menu){

        int result = 0;
        String url = UrlConstant.CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = WeixinUtil.doPostStr(url, menu);
        if(jsonObject != null){
            result = jsonObject.getInt("errcode");
        }
        return result;
    }

    /**
     * 查询菜单
     */
    public static JSONObject queryMenu(String token) {

        String url = UrlConstant.QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = WeixinUtil.doGetStr(url);
        return jsonObject;
    }

    /**
     * 删除菜单
     */
    public static int deleteMenu(String token) {

        String url = UrlConstant.DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = WeixinUtil.doGetStr(url);
        int result = 0;
        if(jsonObject!=null){
            result = jsonObject.getInt("errcode");
        }
        return result;
    }
}
