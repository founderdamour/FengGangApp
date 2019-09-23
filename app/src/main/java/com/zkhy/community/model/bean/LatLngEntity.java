package com.zkhy.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/30 on 17:52
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class LatLngEntity {

    public  double latitude;
    public  double longitude;

    public LatLngEntity() {
    }

    public LatLngEntity(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
