package com.zkhy.community.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *  创建:  梁玉涛 2019/5/28 on 15:20
 *  项目: WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class WechatEntity implements Serializable {
    /**
     * 图文消息的标题
     */
    private String title;
    /**
     * 图文消息的封面图片素材id（必须是永久mediaID）
     */
    private String thumbMediaId;
    /**
     * 作者
     */
    private String author;
    /**
     * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
     */
    private String digest;
    /**
     * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
     */
    private String content;
    /**
     * 图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
     */

    private String url;
    /**
     * 是否显示封面，0为false，即不显示，1为true，即显示
     */
    private int showCoverPic;

    /**
     * 封面图片 url
     */
    private String thumbUrl;


    private Integer totalCount;

    private Date updateTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getShowCoverPic() {
        return showCoverPic;
    }

    public void setShowCoverPic(int showCoverPic) {
        this.showCoverPic = showCoverPic;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
