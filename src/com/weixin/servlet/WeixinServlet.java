package com.weixin.servlet;

import com.weixin.constant.MessageConstant;
import com.weixin.constant.TransConstant;
import com.weixin.entity.TextMessage;
import com.weixin.template.MessageTemplate;
import com.weixin.util.CheckServerUtil;
import com.weixin.util.ConvertUtil;
import com.weixin.util.MessageUtil;
import com.weixin.util.TransUtil;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by xing on 2018/7/18.
 */
public class WeixinServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /*
	 * 在post方法中接收消息的相应，因为用户消息都是以post方式传过来的
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置字符串的格式，避免中文乱码、
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //把信息返回给微信
        PrintWriter out = response.getWriter();
        try {
            //用户消息是以xml的格式传过来，这里把xml转换成map对象
            Map<String, String> map = ConvertUtil.xmlToMap(request);

            //获取map对象里面的参数
            String fromUserName = map.get("FromUserName");
            String toUserName = map.get("ToUserName");
            String content = map.get("Content");
            String msgType = map.get("MsgType");

            //根据msgType来判断消息的类型
            String message = null;
            //消息类型为“text”
            if(MessageConstant.MESSAGE_TEXT.equals(msgType)){
                //1.xyy 2.fty 3.图文  4.图片  5.语音 6.音乐 7.视频 ？.主菜单
                if("1".equals(content)){
                    message = MessageTemplate.initText(toUserName, fromUserName, MessageUtil.firstMenu());
                }else if("2".equals(content)){
                    message = MessageTemplate.initText(toUserName, fromUserName, MessageUtil.secondMenu());
                }else if("3".equals(content)){
                    message = MessageTemplate.initTextPic(toUserName, fromUserName, MessageUtil.thirdMenu());
                }else if("4".equals(content)){
                    message = MessageTemplate.initImage(toUserName, fromUserName, MessageUtil.fourthMenu());
                }else if("5".equals(content)){
                    message = MessageTemplate.initVoice(toUserName, fromUserName, MessageUtil.fifthMenu());
                }else if("6".equals(content)){
                    message = MessageTemplate.initMusic(toUserName, fromUserName, MessageUtil.sixthMenu());
                }else if("7".equals(content)){
                    message = MessageTemplate.initVideo(toUserName, fromUserName, MessageUtil.seventhMenu());
                }else if("8".equals(content)){
                    message = MessageTemplate.initText(toUserName, fromUserName, MessageUtil.eighthMenu());
                }else if("?".equals(content) || "？".equals(content)){
                    message = MessageTemplate.initText(toUserName, fromUserName, MessageTemplate.menuText());
                }else if(content.startsWith("翻译")){
                    String word = content.replaceAll("^翻译", "").trim();
                    if("".equals(word)){
                        message = MessageTemplate.initText(toUserName, fromUserName, MessageUtil.eighthMenu());
                    }else{
                        message = MessageTemplate.initText(toUserName, fromUserName, TransUtil.getTranslateResult(word, TransConstant.TRANS_FROM, TransConstant.TRANS_TO));
                    }
                }else{
                    message = MessageTemplate.initText(toUserName, fromUserName, "无法回复，输入‘?’查询菜单");
                }
            }

            //消息类型为“event”
            else if(MessageConstant.MESSAGE_EVENT.equals(msgType)){
                String eventType = map.get("Event");
                //如果是关注事件
                if(MessageConstant.MESSAGE_SUBSCRIBE.equals(eventType)){
                    //反馈主菜单
                    message = MessageTemplate.initText(toUserName, fromUserName, MessageTemplate.menuText());
                }
                //click事件
                else if("CLICK".equals(eventType)){
                    message = MessageTemplate.initText(toUserName, fromUserName, MessageTemplate.menuText());
                }
                //view事件
                else if("VIEW".equals(eventType)) {
                    //获取view设置的url链接
                    String url = map.get("EventKey");
                    message = MessageTemplate.initText(toUserName, fromUserName, url);
                }
                //扫码事件
                else if(MessageConstant.MESSAGE_SCANCODE.equals(eventType)) {
                    String key = map.get("EventKey");
                    message = MessageTemplate.initText(toUserName, fromUserName, key);
                }
            }
            //地理位置事件
            else if(MessageConstant.MESSAGE_LOCATION.equals(msgType)) {
                String label = map.get("Label");
                message = MessageTemplate.initText(toUserName, fromUserName, label);
            }

            //把消息传回给微信服务器
            out.print(message);
        } catch (DocumentException e){
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    /*
	 * 验证消息的确来自微信服务器
	 * 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带4个参数
	 * 开发者通过检验signature对请求进行校验
	 * 校验流程：
	 * 1）将token、timestamp、nonce三个参数进行字典序排序
	 * 2）将三个参数字符串拼接成一个字符串进行sha1加密
	 * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * 4)原样返回echostr参数内容，则接入生效
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //接收微信服务器端传过来的4个参数
        String signature = request.getParameter("signature");  //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
        String timestamp = request.getParameter("timestamp");  //时间戳
        String nonce = request.getParameter("nonce");          //随机数
        String echostr = request.getParameter("echostr");      //随机字符串

        //用于把echostr传回给微信端
        PrintWriter out = response.getWriter();
        //校验signature
        if(CheckServerUtil.checkSignature(signature, timestamp, nonce)){
            //原样返回echostr参数内容，则接入生效
            out.print(echostr);
        }
    }
}
