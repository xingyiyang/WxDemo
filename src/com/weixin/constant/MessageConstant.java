package com.weixin.constant;

/**
 * Created by xing on 2018/7/18.
 */
public interface MessageConstant {

    //消息类型
    public static final String MESSAGE_TEXT = "text";               //文本
    public static final String MESSAGE_NEWS = "news";               //图文消息
    public static final String MESSAGE_IMAGE = "image";             //图片
    public static final String MESSAGE_VOICE = "voice";             //声音
    public static final String MESSAGE_MUSIC = "music";             //音乐
    public static final String MESSAGE_VIDEO = "video";             //视频
    public static final String MESSAGE_LINK = "link";               //链接
    public static final String MESSAGE_LOCATION = "location";       //位置
    public static final String MESSAGE_EVENT = "event";             //事件
    public static final String MESSAGE_SUBSCRIBE = "subscribe";     // 关注
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe"; // 取消关注
    public static final String MESSAGE_CLICK = "click";             //click类型菜单,大写
    public static final String MESSAGE_VIEW = "view";               //view类型菜单，大写
    public static final String MESSAGE_SCANCODE= "scancode_push";   //扫码
    public static final String MESSAGE_LOCSELECT= "location_select";//地理位置
}
