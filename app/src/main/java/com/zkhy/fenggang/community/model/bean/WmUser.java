package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 五民普通用户表
 * <p>
 * 表名：b_wm_user
 *
 * @mbggenerated do_not_delete_during_merge
 * addModelClassComment 242
 */
//五民普通用户表
public class WmUser implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 账号
     *
     * @mbggenerated
     */
    private String account;

    /**
     * 所属区域id
     *
     * @mbggenerated
     */
    private Long areaId;

    /**
     * 账号Id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 姓名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 照片
     *
     * @mbggenerated
     */
    private String photo;

    /**
     * 年龄
     *
     * @mbggenerated
     */
    private Long age;

    /**
     * 身份证号
     *
     * @mbggenerated
     */
    private String idcard;

    /**
     * 籍贯
     *
     * @mbggenerated
     */
    private String nativePlace;

    /**
     * 街道详细地址
     *
     * @mbggenerated
     */
    private String addrDetail;

    /**
     * 手机号
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 性别
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * 民族
     *
     * @mbggenerated
     */
    private String nation;

    /**
     * 出生日期
     *
     * @mbggenerated
     */
    private String birthday;

    /**
     * 工作单位
     *
     * @mbggenerated
     */
    private String orgName;

    /**
     * 学历（字典表code）
     *
     * @mbggenerated
     */
    private String education;

    /**
     * app用户最后心跳时间
     *
     * @mbggenerated
     */
    private String appLastHeartbeatTime;

    /**
     * 电子邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private Long createUser;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * 更新人
     *
     * @mbggenerated
     */
    private Long updateUser;

    private String areaCode; // 所属社区Code
    // "所属区域名称"
    private String areaName;
    //  "所有区域的父区域id")
    private String parentId;
    //  用户角色(编码)列表"
    private List<String> roles;

    private String areaFullName; // 所属区域全名称
    private String longitude; // 所属区域经度
    private String latitude; // "所属区域维度"


    private String streetName; // 所属街道
    private String streetCode; // 所属街道Code

    private boolean appIs; // 是否是手机端

    /**
     * 五民普通用户表
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAppLastHeartbeatTime() {
        return appLastHeartbeatTime;
    }

    public void setAppLastHeartbeatTime(String appLastHeartbeatTime) {
        this.appLastHeartbeatTime = appLastHeartbeatTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAreaFullName() {
        return areaFullName;
    }

    public void setAreaFullName(String areaFullName) {
        this.areaFullName = areaFullName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public boolean isAppIs() {
        return appIs;
    }

    public void setAppIs(boolean appIs) {
        this.appIs = appIs;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}