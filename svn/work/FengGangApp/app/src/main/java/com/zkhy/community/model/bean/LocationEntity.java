package com.zkhy.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2019/5/10 on 8:38
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class LocationEntity {
    /**
     * addr : string
     * lat : 0
     * lon : 0
     * userId : 0
     */

    private String addr;
    private double lat;
    private double lon;
    private String userId;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
