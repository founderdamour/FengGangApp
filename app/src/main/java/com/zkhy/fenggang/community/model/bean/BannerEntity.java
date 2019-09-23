package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/4 on 15:16
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class BannerEntity {

    /**
     * id : 0
     * orderIndex : 0
     * url : string
     * openUrl	string 跳转地址
     *
     */

    private String id;
    private int orderIndex;
    private String url;
    private String openUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }
}
