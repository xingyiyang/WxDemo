package com.weixin.template;

import com.weixin.constant.MessageConstant;
import com.weixin.constant.UrlConstant;
import com.weixin.menu.Button;
import com.weixin.menu.ClickButton;
import com.weixin.menu.Menu;
import com.weixin.menu.ViewButton;
import com.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

/**
 * Created by xing on 2018/7/18.
 * 菜单类型创建模板
 */
public class MenuTemplate {

    /**
     * clickbutton类型菜单,放在左边
     */
    public static ClickButton buttonMenuLeft(){

        ClickButton clickButton = new ClickButton();
        clickButton.setName("click菜单");
        clickButton.setType(MessageConstant.MESSAGE_CLICK);
        clickButton.setKey("11");
        return clickButton;
    }

    /**
     * ViewButton类型菜单，放在中间
     * @return
     */
    public static ViewButton buttonMenuMiddle(){

        ViewButton viewButton = new ViewButton();
        viewButton.setName("view菜单");
        viewButton.setType(MessageConstant.MESSAGE_VIEW);
        viewButton.setUrl("http://www.baidu.com");
        return viewButton;
    }

    /**
     * button类型菜单,放在右边
     * @return
     */
    public static Button buttonMenuRight(){

        Button button = new Button();
        button.setName("菜单");
        button.setSub_button(new Button[]{buttonMenuRight1(),buttonMenuRight2()});
        return button;
    }

    /**
     * ClickButton菜单，作为右边的子菜单
     * @return
     */
    public static ClickButton buttonMenuRight1(){

        ClickButton clickButton = new ClickButton();
        clickButton.setName("扫码");
        clickButton.setType(MessageConstant.MESSAGE_SCANCODE);
        clickButton.setKey("31");
        return clickButton;
    }

    /**
     * ClickButton菜单，作为右边的子菜单
     * @return
     */
    public static ClickButton buttonMenuRight2(){

        ClickButton clickButton = new ClickButton();
        clickButton.setName("地理位置");
        clickButton.setType(MessageConstant.MESSAGE_LOCSELECT);
        clickButton.setKey("32");
        return clickButton;
    }

}
