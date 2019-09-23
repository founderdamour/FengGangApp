package com.zkhy.fenggang.community.model.bean;

import java.util.Date;

/**
 * <pre>
 *  创建:  梁玉涛 2019/7/29 on 17:14
 *  项目: WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class WmUserSaveReqDTO {

    /**
     * 用于绑定角色
     */
    private String roleCode;

    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 账号
     *
     * @mbggenerated
     */
    private String account;

    /**
     * 是否完善信息
     *
     * @mbggenerated
     */
    private Boolean perfectInfoIs;

    /**
     * 密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 验证码
     *
     * @mbggenerated
     */
    private String captcha;

    /**
     * 是否为app注册
     *
     * @mbggenerated
     */
    private Boolean registerIs;

    /**
     * 所属区域code
     *
     * @mbggenerated
     */
    private String areaCode;

    /**
     * 账号Id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 姓名
     *
     * @mbggenerated
     */
    private String name;

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
    private Date appLastHeartbeatTime;

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
    private Date createTime;

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
    private Date updateTime;

    /**
     * 更新人
     *
     * @mbggenerated
     */
    private Long updateUser;

    /**
     * 照片
     *
     * @mbggenerated
     */
    private String photo;

    private static final long serialVersionUID = 1L;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean getPerfectInfoIs() {
        return perfectInfoIs;
    }

    public void setPerfectInfoIs(Boolean perfectInfoIs) {
        this.perfectInfoIs = perfectInfoIs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Boolean getRegisterIs() {
        return registerIs;
    }

    public void setRegisterIs(Boolean registerIs) {
        this.registerIs = registerIs;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getAppLastHeartbeatTime() {
        return appLastHeartbeatTime;
    }

    public void setAppLastHeartbeatTime(Date appLastHeartbeatTime) {
        this.appLastHeartbeatTime = appLastHeartbeatTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
