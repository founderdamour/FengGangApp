package com.sinothk.map.amap.location;

import com.amap.api.location.AMapLocation;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/1 on 0:17
 *  项目:  MapLocationByAMapLib
 *  描述:
 *  更新:
 * <pre>
 */
public class AMapLocationEntity {

    private int locationType;//获取当前定位结果来源，如网络定位结果，详见定位类型表

    private double latitude;//获取纬度
    private double longitude;//获取经度

    private float accuracy;//获取精度信息

    private String address;//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。

    private String country;//国家信息
    private String province;//省信息
    private String city;//城市信息
    private String district;//城区信息

    private String street;//街道信息
    private String streetNum;//街道门牌号信息

    private String cityCode;//城市编码
    private String adCode;//地区编码

    private String aoiName; //获取当前定位点的AOI信息

    private String buildingId;//获取当前室内定位的建筑物Id
    private String floor;//获取当前室内定位的楼层
    private int gpsAccuracyStatus;//获取GPS的当前状态
    private long locationTime; //获取定位时间

    private int code;//结果码
    private String msg;//结果信息

    public static AMapLocationEntity format(AMapLocation mapLoc) {

        if (mapLoc == null) {
            return null;
        } else {
            //
            AMapLocationEntity locationEntity = new AMapLocationEntity();
            locationEntity.setLocationType(mapLoc.getLocationType());

            locationEntity.setLatitude(mapLoc.getLatitude());
            locationEntity.setLongitude(mapLoc.getLongitude());
            locationEntity.setAccuracy(mapLoc.getAccuracy());

            locationEntity.setAddress(mapLoc.getAddress());

            locationEntity.setCountry(mapLoc.getCountry());
            locationEntity.setProvince(mapLoc.getProvince());
            locationEntity.setCity(mapLoc.getCity());
            locationEntity.setDistrict(mapLoc.getDistrict());

            locationEntity.setStreet(mapLoc.getStreet());
            locationEntity.setStreetNum(mapLoc.getStreetNum());

            locationEntity.setCityCode(mapLoc.getCityCode());
            locationEntity.setAdCode(mapLoc.getAdCode());

            locationEntity.setAoiName(mapLoc.getAoiName());

            locationEntity.setBuildingId(mapLoc.getBuildingId());
            locationEntity.setFloor(mapLoc.getFloor());

            locationEntity.setGpsAccuracyStatus(mapLoc.getGpsAccuracyStatus());
            locationEntity.setLocationTime(mapLoc.getTime());

            locationEntity.setCode(mapLoc.getErrorCode());
            locationEntity.setMsg(mapLoc.getErrorInfo());

            return locationEntity;
        }
    }

    public int getLocationType() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType = locationType;
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

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getAoiName() {
        return aoiName;
    }

    public void setAoiName(String aoiName) {
        this.aoiName = aoiName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getGpsAccuracyStatus() {
        return gpsAccuracyStatus;
    }

    public void setGpsAccuracyStatus(int gpsAccuracyStatus) {
        this.gpsAccuracyStatus = gpsAccuracyStatus;
    }

    public long getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(long locationTime) {
        this.locationTime = locationTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
