package com.weixin.template;

import com.weixin.constant.MessageConstant;
import com.weixin.entity.*;
import com.weixin.util.ConvertUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by xing on 2018/7/18.
 * 自动回复消息模板
 */
public class MessageTemplate {

    static Date date = new Date();

    /**
     * 默认的消息提示
     */
    public static String menuText(){

        StringBuffer sbf = new StringBuffer();
        sbf.append("欢迎您的关注，请按照菜单提示进行操作:\n\n");
        sbf.append("1、xyy介绍\n");
        sbf.append("2、zxh介绍\n");
        sbf.append("3、图文\n");
        sbf.append("4、图片\n");
        sbf.append("5、语音\n");
        sbf.append("6、音乐\n");
        sbf.append("7、视频\n");
        sbf.append("8、翻译\n\n");
        sbf.append("回复'?'调出此菜单");
        return sbf.toString();
    }

    /**
     * 组装文本消息,把需要反馈的消息转换成xml格式
     */
    public static String initText(String toUserName, String fromUserName, String content){

        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(MessageConstant.MESSAGE_TEXT);
        textMessage.setCreateTime(date.getTime());
        textMessage.setContent(content);
        return ConvertUtil.textMessageToXml(textMessage);

    }

    /**
     * 组装图片消息，把需要反馈的消息转换成xml格式
     */
    public static String initImage(String toUserName, String fromUserName, Image image){

        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MessageConstant.MESSAGE_IMAGE);
        imageMessage.setImage(image);
        imageMessage.setCreateTime(date.getTime());
        return ConvertUtil.imageMessageToXml(imageMessage);
    }

    /**
     * 组装语音消息
     */
    public static String initVoice(String toUserName, String fromUserName, Voice voice){

        VoiceMessage voiceMessage = new VoiceMessage();
        voiceMessage.setFromUserName(toUserName);
        voiceMessage.setToUserName(fromUserName);
        voiceMessage.setMsgType(MessageConstant.MESSAGE_VOICE);
        voiceMessage.setVoice(voice);
        voiceMessage.setCreateTime(date.getTime());
        return ConvertUtil.voiceMessageToXml(voiceMessage);
    }

    /**
     * 组装音乐消息
     */
    public static String initMusic(String toUserName, String fromUserName, Music music){

        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setMsgType(MessageConstant.MESSAGE_MUSIC);
        musicMessage.setMusic(music);
        musicMessage.setCreateTime(date.getTime());
        return ConvertUtil.musicMessageToXml(musicMessage);
    }

    /**
     * 组装视频消息
     */
    public static String initVideo(String toUserName, String fromUserName, Video video){

        VideoMessage videoMessage = new VideoMessage();
        videoMessage.setFromUserName(toUserName);
        videoMessage.setToUserName(fromUserName);
        videoMessage.setMsgType(MessageConstant.MESSAGE_VIDEO);
        videoMessage.setVideo(video);
        videoMessage.setCreateTime(date.getTime());
        return ConvertUtil.videoMessageToXml(videoMessage);
    }

    /**
     * 组装图文消息，把需要反馈的消息转换成xml格式
     */
    public static String initTextPic(String toUserName, String fromUserName, List<ItemMessage> articles){

        TextPicMessage textPicMessage = new TextPicMessage();
        textPicMessage.setFromUserName(toUserName);
        textPicMessage.setToUserName(fromUserName);
        textPicMessage.setCreateTime(date.getTime());
        textPicMessage.setMsgType(MessageConstant.MESSAGE_NEWS);
        textPicMessage.setArticles(articles);
        textPicMessage.setArticleCount(articles.size());
        return ConvertUtil.textPicMessageToXml(textPicMessage);
    }
}
