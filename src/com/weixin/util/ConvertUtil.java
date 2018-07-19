package com.weixin.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.weixin.entity.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xing on 2018/7/18.
 */
public class ConvertUtil {
    /**
     * xml转为map集合
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

        Map<String, String> map = new HashMap<String, String>();
        //用于读取xml文档
        SAXReader reader = new SAXReader();
        //从request中获取输入流并读取
        InputStream insInputStream = request.getInputStream();
        Document document = reader.read(insInputStream);

        //获取xml的根元素节点
        Element rootElement = document.getRootElement();
        //获取根元素下的所有子元素，存入list集合中

        List<Element> list = rootElement.elements();
        //把list集合的数据存入map中
        for(Element e:list){
            map.put(e.getName(), e.getText());
        }
        insInputStream.close();
        return map;
    }

    /**
     * 将文本消息对象转换为xml
     */
    public static String textMessageToXml(TextMessage textMessage){

        XStream xstream = new XStream(new DomDriver("UTF-8"));
        //把根元素替换成<xml>
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 将图片消息转换成xml
     * @param imageMessage
     * @return String
     */
    public static String imageMessageToXml(ImageMessage imageMessage){

        XStream xstream = new XStream(new DomDriver("UTF-8"));
        //把根元素替换成<xml>
        xstream.alias("xml", imageMessage.getClass());
        xstream.alias("Image", new Image().getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * 将语音消息转换成xml
     * @param voiceMessage
     * @return
     */
    public static String voiceMessageToXml(VoiceMessage voiceMessage){

        XStream xstream = new XStream(new DomDriver("UTF-8"));
        xstream.alias("xml", voiceMessage.getClass());
        xstream.alias("Voice", new Voice().getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * 将音乐消息转换成xml
     */
    public static String musicMessageToXml(MusicMessage musicMessage){

        XStream xstream = new XStream(new DomDriver("UTF-8"));
        xstream.alias("xml", musicMessage.getClass());
        xstream.alias("Music", new Music().getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 将视频消息转换成xml
     */
    public static String videoMessageToXml(VideoMessage videoMessage){

        XStream xstream = new XStream(new DomDriver("UTF-8"));
        xstream.alias("xml", videoMessage.getClass());
        xstream.alias("Video", new Video().getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * 将图文消息对象转换为xml
     */
    public static String textPicMessageToXml(TextPicMessage textPicMessage){

        XStream xstream = new XStream(new DomDriver("UTF-8"));
        xstream.alias("xml", textPicMessage.getClass());
        xstream.alias("item", new ItemMessage().getClass());
        return xstream.toXML(textPicMessage);
    }
}
