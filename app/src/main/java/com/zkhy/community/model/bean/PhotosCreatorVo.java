package com.zkhy.community.model.bean;

import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/24 on 16:06
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PhotosCreatorVo {

    /**
     * id : 11111
     * latitude : 0
     * longitude : 0
     * questionAddress : 高科一号
     * questionDesc : 问题多多啊
     * areaCode : shljd_wjsq
     */
    private long id;
    private long questionId;
    private long dealTaskId;
//    private long deptId;

    private double latitude;
    private double longitude;
    private String questionAddress;
    private String questionDesc;
    private String areaCode;
    private String userId;
    private List<Integer> stateList;

    private boolean dealMyOrg;

    // "现场工作人员处理状态(0待处理1已处理)")//查询未实现
    private Integer dealStatus;


    private String doneDesc;

    private Long imgId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getQuestionAddress() {
        return questionAddress;
    }

    public void setQuestionAddress(String questionAddress) {
        this.questionAddress = questionAddress;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<Integer> getStateList() {
        return stateList;
    }

    public void setStateList(List<Integer> stateList) {
        this.stateList = stateList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public boolean isDealMyOrg() {
        return dealMyOrg;
    }

    public void setDealMyOrg(boolean dealMyOrg) {
        this.dealMyOrg = dealMyOrg;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getDoneDesc() {
        return doneDesc;
    }

    public void setDoneDesc(String doneDesc) {
        this.doneDesc = doneDesc;
    }

    public long getDealTaskId() {
        return dealTaskId;
    }

    public void setDealTaskId(long dealTaskId) {
        this.dealTaskId = dealTaskId;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    //    public long getDeptId() {
//        return deptId;
//    }
//
//    public void setDeptId(long deptId) {
//        this.deptId = deptId;
//    }
}
