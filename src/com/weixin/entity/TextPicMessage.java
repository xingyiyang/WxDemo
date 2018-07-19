package com.weixin.entity;

import java.util.List;

/**
 * Created by xing on 2018/7/18.
 */
public class TextPicMessage extends BaseMessage{
    private int ArticleCount; // 消息体数量
    private List<ItemMessage> Articles; //消息体集合

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<ItemMessage> getArticles() {
        return Articles;
    }

    public void setArticles(List<ItemMessage> articles) {
        Articles = articles;
    }
}
