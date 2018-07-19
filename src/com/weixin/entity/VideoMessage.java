package com.weixin.entity;

/**
 * Created by xing on 2018/7/18.
 */
public class VideoMessage extends BaseMessage{
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        this.Video = video;
    }

}
