package com.weixin.util;

import com.weixin.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xing on 2018/7/18.
 */
public class MessageUtil {
    /**
     * 对消息“1”的回复
     * @return 文本
     */
    public static String firstMenu(){

        StringBuffer sbf = new StringBuffer();
        sbf.append("我是xyy,我喜欢zxh");
        return sbf.toString();
    }

    /**
     * 对消息“2”的回复
     * @return 文本
     */
    public static String secondMenu(){

        StringBuffer sbf = new StringBuffer();
        sbf.append("zxh是猪吗？怎么这么能吃");
        return sbf.toString();
    }

    /**
     * 对消息“3”的回复
     * @return 图文
     */
    public static List<ItemMessage> thirdMenu(){

        List<ItemMessage> list = new ArrayList<ItemMessage>();

        ItemMessage itemMessage = new ItemMessage();
        itemMessage.setTitle("海边城市");
        itemMessage.setDescription("我想与你虚度光阴，在海边走走，吹吹海风，踩踩沙滩，有你的日子里生活也变得有趣.");
        itemMessage.setPicUrl("http://v6zveq.natappfree.cc/WxDemo/resources/images/1.jpg");
        itemMessage.setUrl("http://v6zveq.natappfree.cc/WxDemo/index.jsp");
        list.add(itemMessage);

        ItemMessage itemMessage2 = new ItemMessage();
        itemMessage2.setTitle("湖边村庄");
        itemMessage2.setDescription("我想与你虚度光阴，在湖边走走，抚摸绿叶，闻到花香，有你的日子里生活也变得有趣.");
        itemMessage2.setPicUrl("http://v6zveq.natappfree.cc/WxDemo/resources/images/2.jpg");
        itemMessage2.setUrl("http://v6zveq.natappfree.cc/WxDemo/index.jsp");
        list.add(itemMessage2);

        ItemMessage itemMessage3 = new ItemMessage();
        itemMessage3.setTitle("云海之上");
        itemMessage3.setDescription("我想与你虚度光阴，在云端翱翔，感受阳光，鸟瞰大地，有你的日子里生活也变得有趣.");
        itemMessage3.setPicUrl("http://v6zveq.natappfree.cc/WxDemo/resources/images/3.jpg");
        itemMessage3.setUrl("http://v6zveq.natappfree.cc/WxDemo/index.jsp");
        list.add(itemMessage3);
        return list;
    }

    /**
     * 对消息“4”的回复
     * @return 图片
     */
    public static Image fourthMenu(){

        Image image = new Image();
        image.setMediaId("0jedRsIQlvjUlle88YDFP_z_PR61juY-5w6uDwwHmou1rALfBA_9a_AssWNKLRYh");
        return image;
    }

    /**
     * 对消息“5”的回复
     * @return 语音
     */
    public static Voice fifthMenu(){

        Voice voice = new Voice();
        voice.setMediaId("lYtGL6t2zMOq5TCVQw4xzaP36cE_jnovMvy7GKXeqcL0439aEgxzyrNWwljqXHCx");
        return voice;
    }

    /**
     * 对消息“6”的回复
     * @return 音乐
     */
    public static Music sixthMenu(){
        Music music = new Music();
        music.setTitle("see you again");
        music.setDescription("速7片尾曲");
        music.setThumbMediaId("lYtGL6t2zMOq5TCVQw4xzaP36cE_jnovMvy7GKXeqcL0439aEgxzyrNWwljqXHCx");
        music.setMusicUrl("http://v6zveq.natappfree.cc/WxDemo/resources/music/SeeYouAgain.mp3");
        music.setHQMusicUrl("http://v6zveq.natappfree.cc/WxDemo/resources/music/SeeYouAgain.mp3");
        return music;
    }

    /**
     * 对消息“7”的回复
     * @return 视频
     */
    public static Video seventhMenu(){

        Video video = new Video();
        video.setTitle("zxh视频");
        video.setDescription("想看关于zxh的视频");
        //video.setMediaId("UHmoxa96_roed9vA_gBwAU6Ly9vN-aQ1zYYukmQ0ws2CImBpkeOFnhL9Rkoa3YF_");
        video.setMediaId("AzkmztfgQJDahEN-Qfjy_aoAkrt07dxGj3W0uF2vvbYXQrLlhfbrvjqX-6lpAGfn");
        return video;
    }

    /**
     * 对消息“8”的回复
     * @return 翻译
     */
    public static String eighthMenu(){

        StringBuffer sbf = new StringBuffer();
        sbf.append("词组翻译使用指南\n\n");
        sbf.append("使用实例：\n");
        sbf.append("翻译四川大学\n");
        sbf.append("翻译篮球\n");
        return sbf.toString();
    }
}
