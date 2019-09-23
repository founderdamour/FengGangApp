package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/13 on 11:07
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class HandleProgressEntity {
    /**
     * addrDetail : string
     * applyCode : string
     * applyTime : 2018-12-13T06:00:26.482Z
     * applyUserId : 0
     * applyUserName : 0
     * apprStatus : 0
     * cityId : 0
     * county : 0
     * flowId : 0
     * flowName : string
     * handleType : true
     * id : 0
     * idcard : string
     * phone : string
     * provinceId : 0
     * town : 0
     * userName : string
     */

    /**
     * addrDetail	string
     * 街道详细地址
     *
     * applyUserId	integer($int64)
     * 申请用户Id
     *
     * cityId	integer($int64)
     * 市（区域Id）
     *
     * county	integer($int64)
     * 县（市、区）(区域Id)
     *
     * flowId	integer($int32)
     * 申请流程类型id
     * 申请流程类型id(0:全部 1.高龄补贴 2.一孩生育登记 3.二孩生育登记 4.汇川区残疾人证发放和管理 5.居住证明 6.关系证明  7.政审证明 8.文化户口登记 9.小孩入学登记)
     *
     * 全部由5  改为了0
     *
     * handleType	boolean
     * 办理类型(1:本人办理，2：代办帮办)
     *
     * id	integer($int64)
     * 主键
     *
     * marryStatus	integer($int32)
     * 婚姻状态(1：初婚，2：再婚，3：未婚)
     *
     * provinceId	integer($int64)
     * 省（区域Id）
     *
     * town	integer($int64)
     * 乡镇街道（区域Id）
     */
    private String addrDetail;
    private String applyCode;
    private String applyTime;
    private String applyUserId;
    private int applyUserName;
    private int apprStatus;
    private int apprLink;

    private int marryStatus;
    private int cityId;
    private int county;
    private int flowId;
    private String flowName;
    private boolean handleType;
    private String id;
    private String idcard;
    private String phone;
    private int provinceId;
    private int town;
    private String userName;
    private String handleDeptName;
    private String apprSuggest;

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public int getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(int applyUserName) {
        this.applyUserName = applyUserName;
    }

    public int getApprStatus() {
        return apprStatus;
    }

    public void setApprStatus(int apprStatus) {
        this.apprStatus = apprStatus;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public boolean isHandleType() {
        return handleType;
    }

    public void setHandleType(boolean handleType) {
        this.handleType = handleType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getTown() {
        return town;
    }

    public void setTown(int town) {
        this.town = town;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMarryStatus() {
        return marryStatus;
    }

    public void setMarryStatus(int marryStatus) {
        this.marryStatus = marryStatus;
    }

    public String getHandleDeptName() {
        return handleDeptName;
    }

    public void setHandleDeptName(String handleDeptName) {
        this.handleDeptName = handleDeptName;
    }

    public String getApprSuggest() {
        return apprSuggest;
    }

    public void setApprSuggest(String apprSuggest) {
        this.apprSuggest = apprSuggest;
    }

    public int getApprLink() {
        return apprLink;
    }

    public void setApprLink(int apprLink) {
        this.apprLink = apprLink;
    }
}
