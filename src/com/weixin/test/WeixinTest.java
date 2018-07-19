package com.weixin.test;

import com.weixin.constant.TransConstant;
import com.weixin.entity.AccessToken;
import com.weixin.util.MenuUtil;
import com.weixin.util.TransUtil;
import com.weixin.util.UploadUtil;
import com.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

import java.io.IOException;

/**
 * Created by xing on 2018/7/18.
 */
public class WeixinTest {

    public static void main(String[] args) {
        AccessToken accessToken = WeixinUtil.getAccessToken();
        System.out.println(accessToken.getToken());

        //uploadVideo(accessToken);

        try {
            String menu = JSONObject.fromObject(MenuUtil.initMenu()).toString();
            int resultcode = MenuUtil.createMenu(accessToken.getToken(), menu);
            if(resultcode == 0){
                System.out.println("创建菜单成功");
            }else{
                System.out.println("errorcode: "+resultcode);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //上传图片
    public static void uploadImg(AccessToken accessToken){
        String path = "H:/workspace/idea/WxDemo/web/resources/images/zxh.jpg";
		try {
			String mediaId = UploadUtil.upload(path, accessToken.getToken(), "image");
			System.out.println(mediaId);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    //上传音乐
    public static void uploadMusic(AccessToken accessToken){
        String path = "H:/workspace/idea/WxDemo/web/resources/images/zxh.jpg";
        try {
            String mediaId = UploadUtil.upload(path, accessToken.getToken(), "thumb");
            System.out.println(mediaId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //上传视频
    public static void uploadVideo(AccessToken accessToken){
        String path = "H:/workspace/idea/WxDemo/web/resources/video/zxh.mp4";
        try {
            String mediaId = UploadUtil.upload(path, accessToken.getToken(), "video");
            System.out.println(mediaId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //翻译测试
    public static void transTest(){
        String q = "中国篮球比中国足球打得好";
        String transresult = TransUtil.getTranslateResult(q, TransConstant.TRANS_FROM, TransConstant.TRANS_TO);
        System.out.println(transresult);

    }
}
