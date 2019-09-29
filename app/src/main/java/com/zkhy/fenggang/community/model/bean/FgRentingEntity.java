package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 房屋租赁
 */
public class FgRentingEntity implements Serializable {

    private String id;

    private String address;

    private String price;

    private String areaSize;

    private String phone;

    private String deptName;

    // 0未发布1已发布
    private int publisState;

    private String publishContent;

    private Date publishTime;

    private String publishUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getPublisState() {
        return publisState;
    }

    public void setPublisState(int publisState) {
        this.publisState = publisState;
    }

    public String getPublishContent() {
        return publishContent;
    }

    public void setPublishContent(String publishContent) {
        this.publishContent = publishContent;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public String getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(String areaSize) {
        this.areaSize = areaSize;
    }
}
