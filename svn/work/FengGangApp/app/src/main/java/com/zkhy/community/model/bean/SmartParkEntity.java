package com.zkhy.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2019/2/28 on 15:47
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class SmartParkEntity {
    private String id;

    private String parkName;
    private int parkDistance;
    private String area;
    private String address;

    private double lat;
    private double lng;

    public SmartParkEntity() {
    }

    public SmartParkEntity(String parkName, int parkDistance, String area, String address, double lat, double lng) {
        this.parkName = parkName;
        this.parkDistance = parkDistance;
        this.area = area;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getParkDistance() {
        return parkDistance;
    }

    public void setParkDistance(int parkDistance) {
        this.parkDistance = parkDistance;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
