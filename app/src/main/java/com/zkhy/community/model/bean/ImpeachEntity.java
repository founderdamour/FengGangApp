package com.zkhy.community.model.bean;

import java.util.Date;

/**
 * <pre>
 *  创建:  梁玉涛 2019/5/10 on 11:49
 *  项目:  WuMinAndroid
 *  描述: 网上信访表
 *  更新:
 * <pre>
 */
public class ImpeachEntity {


    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 标题
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 内容
     *
     * @mbggenerated
     */
    private String content;

    /**
     * 姓名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 提交时间
     *
     * @mbggenerated
     */
    private Date commitTime;

    /**
     * 联系电话
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 身份证
     *
     * @mbggenerated
     */
    private String idcard;

    /**
     * 地址
     *
     * @mbggenerated
     */
    private String addr;

    /**
     * #枚举类 untreated=未处理 complete = 已完成
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 处理时间
     *
     * @mbggenerated
     */
    private Date dealTime;

    /**
     * 处理内容
     *
     * @mbggenerated
     */
    private String dealContent;

    /**
     * 处理人(!b_wu_user的userId)
     *
     * @mbggenerated
     */
    private Long wuUserId;

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
     * app web 标识
     *
     * @mbggenerated
     */
    private String from;

    private Date beginTime;

    private Date endTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealContent() {
        return dealContent;
    }

    public void setDealContent(String dealContent) {
        this.dealContent = dealContent;
    }

    public Long getWuUserId() {
        return wuUserId;
    }

    public void setWuUserId(Long wuUserId) {
        this.wuUserId = wuUserId;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
