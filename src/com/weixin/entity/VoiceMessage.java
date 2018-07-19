package com.weixin.entity;

/**
 * Created by xing on 2018/7/18.
 */
public class VoiceMessage extends BaseMessage{
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice Voice) {
        this.Voice = Voice;
    }
}
