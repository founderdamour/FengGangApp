package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/18 on 17:22
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PoliceStationRecordEntity {
    /**
     * addr : string
     * answerStatus : true
     * callLat : 0
     * callLon : 0
     * callPhone : string
     * callTime : 2018-12-26T08:50:06.476Z
     * callUserId : 0
     * id : 0
     * phone : string
     */

    private String addr;
    private boolean answerStatus;
    private double callLat;
    private double callLon;
    private String callPhone;
    private String callName;
    private String callTime;
    private String callUserId;
    private String id;
    private String phone;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public boolean isAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(boolean answerStatus) {
        this.answerStatus = answerStatus;
    }

    public double getCallLat() {
        return callLat;
    }

    public void setCallLat(double callLat) {
        this.callLat = callLat;
    }

    public double getCallLon() {
        return callLon;
    }

    public void setCallLon(double callLon) {
        this.callLon = callLon;
    }

    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getCallUserId() {
        return callUserId;
    }

    public void setCallUserId(String callUserId) {
        this.callUserId = callUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }
}
