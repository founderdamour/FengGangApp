package com.zkhy.fenggang.community.model.bean;

import java.util.Date;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/24 on 20:14
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PhotoShotEntity {

    /**
     * areaCode : string
     * createTime : 2019-03-24T10:00:59.594Z
     * createUser : 0
     * dealDeptName : string
     * doneTime : 2019-03-24T10:00:59.594Z
     * evaluateStar : 0
     * id : 0
     * ingStatus : 0
     * latitude : 0
     * longitude : 0
     * overdueTime : 0
     * phone : string
     * questionAddress : string
     * questionCode : string
     * questionDesc : string
     * remark : string
     * replyTime : 2019-03-24T10:00:59.594Z
     * requireDoneTime : 2019-03-24T10:00:59.594Z
     * status : 0
     * summary : string
     * updateTime : 2019-03-24T10:00:59.594Z
     * updateUser : 0
     * userId : 0
     * userName : string
     * worryLevel : 0
     */

    private String areaCode;
    private Date createTime;
    private long createUser;
    private String dealDeptName;
    private String doneTime;
    private int evaluateStar;
    private long id;
    private int ingStatus;
    private double latitude;
    private double longitude;
    private long overdueTime;
    private String phone;
    private String questionAddress;
    private String questionCode;
    private String questionDesc;
    private String remark;
    private String replyTime;
    private String requireDoneTime;
    private int status; // 处理状态(0待分派1进行中2已完成)
    private String summary;
    private Date updateTime;
    private long updateUser;
    private long userId;
    private String userName;
    private int worryLevel;

    private int dealStatus;  //

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    public String getDealDeptName() {
        return dealDeptName;
    }

    public void setDealDeptName(String dealDeptName) {
        this.dealDeptName = dealDeptName;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public int getEvaluateStar() {
        return evaluateStar;
    }

    public void setEvaluateStar(int evaluateStar) {
        this.evaluateStar = evaluateStar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIngStatus() {
        return ingStatus;
    }

    public void setIngStatus(int ingStatus) {
        this.ingStatus = ingStatus;
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

    public long getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(long overdueTime) {
        this.overdueTime = overdueTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestionAddress() {
        return questionAddress;
    }

    public void setQuestionAddress(String questionAddress) {
        this.questionAddress = questionAddress;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getRequireDoneTime() {
        return requireDoneTime;
    }

    public void setRequireDoneTime(String requireDoneTime) {
        this.requireDoneTime = requireDoneTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(long updateUser) {
        this.updateUser = updateUser;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWorryLevel() {
        return worryLevel;
    }

    public void setWorryLevel(int worryLevel) {
        this.worryLevel = worryLevel;
    }

    public int getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(int dealStatus) {
        this.dealStatus = dealStatus;
    }
}
