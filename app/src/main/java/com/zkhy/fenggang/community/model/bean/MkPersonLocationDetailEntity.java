package com.zkhy.fenggang.community.model.bean;

/**
 * 人员定位系统详情
 */
public class MkPersonLocationDetailEntity {

    // 识别号
    private String point;
    // 安装位置
    private String wz;
    // 定义类型
    private String devid;
    // 当前人数
    private String k1;
    // 异常人数
    private String k2;
    // 额定人数
    private String k3;
    // 超员人数
    private String cy;
    // 通讯状态
    private String state;

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

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getK1() {
        return k1;
    }

    public void setK1(String k1) {
        this.k1 = k1;
    }

    public String getK2() {
        return k2;
    }

    public void setK2(String k2) {
        this.k2 = k2;
    }

    public String getK3() {
        return k3;
    }

    public void setK3(String k3) {
        this.k3 = k3;
    }

    public String getCy() {
        return cy;
    }

    public void setCy(String cy) {
        this.cy = cy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
