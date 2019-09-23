package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 20:23
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PersonWorkEntity {

    /**
     * description:
     * 人办新增请求DTO
     * <p>
     * addrDetail	string
     * 街道详细地址
     * <p>
     * applyUserId	integer($int64)
     * 申请用户Id
     * <p>
     * cityId	integer($int64)
     * 市（区域Id）
     * <p>
     * county	integer($int64)
     * 县（市、区）(区域Id)
     * <p>
     * flowId	integer($int32)
     * 申请流程类型id(1:高龄补贴,2:一孩生育登记,3:二孩生育登记,4:汇川区残疾人证发放和管理)
     * <p>
     * handleType	boolean
     * 办理类型(1:本人办理，2：代办帮办)
     * <p>
     * id	integer($int64)
     * 主键
     * <p>
     * marryStatus	integer($int32)
     * 婚姻状态(1：初婚，2：再婚，3：未婚)
     * <p>
     * provinceId	integer($int64)
     * 省（区域Id）
     * <p>
     * town	integer($int64)
     * 乡镇街道（区域Id）
     */

    private String addrDetail;
    private String applyCode;
    private String applyTime;
    private String applyUserId;
    private int apprStatus;
    private String apprSuggest;
    private String apprTime;
    private String apprUserId;
    private int cityId;
    private int county;
    private String createTime;
    private String createUser;
    private int flowId;
    private boolean handleType;
    private String id;
    private int provinceId;
    private int town;
    private String updateTime;
    private String updateUser;

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

    public int getApprStatus() {
        return apprStatus;
    }

    public void setApprStatus(int apprStatus) {
        this.apprStatus = apprStatus;
    }

    public String getApprSuggest() {
        return apprSuggest;
    }

    public void setApprSuggest(String apprSuggest) {
        this.apprSuggest = apprSuggest;
    }

    public String getApprTime() {
        return apprTime;
    }

    public void setApprTime(String apprTime) {
        this.apprTime = apprTime;
    }

    public String getApprUserId() {
        return apprUserId;
    }

    public void setApprUserId(String apprUserId) {
        this.apprUserId = apprUserId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
