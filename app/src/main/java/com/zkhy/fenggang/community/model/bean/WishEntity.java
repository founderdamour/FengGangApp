package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/24 on 9:55
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class WishEntity implements Serializable {
    /**
     * age : 0
     * applyUserId : 0
     * apprSuggest : string
     * area : string
     * areaCode : string
     * claimPhone : string
     * claimPhoto : string
     * claimTime : 2019-01-04T09:07:09.089Z
     * claimUserId : 0
     * claimUserName : string
     * finshMessage : string
     * finshTime : 2019-01-04T09:07:09.089Z
     * loCount : 0
     * name : string
     * orgName : string
     * phone : string
     * photo : string
     * publishTime : 2019-01-04T09:07:09.089Z
     * wishContent : string
     * wishStatus : 0
     */

    private String id;
    private int age;
    private String applyUserId;
    private String apprSuggest;
    private String street;
    private String area;
    private String createTime;
    private String areaCode;
    private String claimPhone;
    private String claimPhoto;
    private String claimTime;
    private String claimUserId;
    private String claimUserName;
    private String finshMessage;
    private String finshTime;
    private int loCount;
    private String name;
    private String orgName;
    private String phone;
    private String photo;
    private String wishContent;
    private int wishStatus;

    public WishEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApprSuggest() {
        return apprSuggest;
    }

    public void setApprSuggest(String apprSuggest) {
        this.apprSuggest = apprSuggest;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getClaimPhone() {
        return claimPhone;
    }

    public void setClaimPhone(String claimPhone) {
        this.claimPhone = claimPhone;
    }

    public String getClaimPhoto() {
        return claimPhoto;
    }

    public void setClaimPhoto(String claimPhoto) {
        this.claimPhoto = claimPhoto;
    }

    public String getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(String claimTime) {
        this.claimTime = claimTime;
    }

    public String getClaimUserId() {
        return claimUserId;
    }

    public void setClaimUserId(String claimUserId) {
        this.claimUserId = claimUserId;
    }

    public String getClaimUserName() {
        return claimUserName;
    }

    public void setClaimUserName(String claimUserName) {
        this.claimUserName = claimUserName;
    }

    public String getFinshMessage() {
        return finshMessage;
    }

    public void setFinshMessage(String finshMessage) {
        this.finshMessage = finshMessage;
    }

    public String getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(String finshTime) {
        this.finshTime = finshTime;
    }

    public int getLoCount() {
        return loCount;
    }

    public void setLoCount(int loCount) {
        this.loCount = loCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getWishContent() {
        return wishContent;
    }

    public void setWishContent(String wishContent) {
        this.wishContent = wishContent;
    }

    public int getWishStatus() {
        return wishStatus;
    }

    public void setWishStatus(int wishStatus) {
        this.wishStatus = wishStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
