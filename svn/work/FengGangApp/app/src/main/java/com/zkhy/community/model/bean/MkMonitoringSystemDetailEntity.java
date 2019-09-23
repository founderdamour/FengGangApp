package com.zkhy.community.model.bean;

/**
 * 煤矿检测系统详情
 */
public class MkMonitoringSystemDetailEntity {

    // 检测点
    private String point;
    // 检测地址
    private String wz;
    // 检测值
    private String sssj;
    // 报警值
    private String alarm;

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }

    public String getSssj() {
        return sssj;
    }

    public void setSssj(String sssj) {
        this.sssj = sssj;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }
}
