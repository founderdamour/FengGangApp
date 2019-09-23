package com.zkhy.community.model.bean;

import java.io.Serializable;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/8 on 16:16
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class LoveHeartEntity implements Serializable {

    /**
     * content : string
     * fromSource : string
     * id : 0
     * proName : string
     * pubTime : string
     * sourceUrl : string
     */

    private String content;
    private String fromSource;
    private String id;
    private String proName;
    private String pubTime;
    private String sourceUrl;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromSource() {
        return fromSource;
    }

    public void setFromSource(String fromSource) {
        this.fromSource = fromSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
