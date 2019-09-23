package com.zkhy.community.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *  创建:  梁玉涛 2019/8/7 on 10:28
 *  项目: WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class MsgsCenterInfo implements Serializable {

    private Long id;

    /**
     * 业务id
     * 业务表的唯一id
     *
     * @mbggenerated
     */
    private String bizId;

    /**
     * 业务类型
     * #MsgsBizType.getCode()
     *
     * @mbggenerated
     */
    private String bizType;

    /**
     * 详细类型描述
     * #MsgsBizType
     *
     * @mbggenerated
     */
    private String bizTypeDescribe;

    /**
     * 消息类型 #MsgsCenterType WAIT:待办 NOTIFY:通知 PUBLICITY:公示公告
     *
     * @mbggenerated
     */
    private String msgsType;

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
     * 作者名称
     *
     * @mbggenerated
     */
    private String author;

    /**
     * 处理地址
     * 以http开头时直接跳转，否则与#c_application表拼接后跳转
     * http可带参数
     *
     * @mbggenerated
     */
    private String handlerUrl;

    /**
     * 处理参数
     *
     * @mbggenerated
     */
    private String handlerParams;

    /**
     * 是否单人处理
     *
     * @mbggenerated
     */
    private Boolean isSingleHandle;

    /**
     * 是否删除
     * 业务数据删除后，会调用接口删除该消息
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 应用id
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 应用名称
     *
     * @mbggenerated
     */
    private String appName;

    /**
     * 客户端对此消息的唯一标示,   若客户端对此消息进行了存储，这里推荐是那里的存储id
     * #MsgsClientFlag
     *
     * @mbggenerated
     */
    private String clientFlag;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 创建人id
     *
     * @mbggenerated
     */
    private Long createUser;

    private Date updateTime;

    private Long updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizTypeDescribe() {
        return bizTypeDescribe;
    }

    public void setBizTypeDescribe(String bizTypeDescribe) {
        this.bizTypeDescribe = bizTypeDescribe;
    }

    public String getMsgsType() {
        return msgsType;
    }

    public void setMsgsType(String msgsType) {
        this.msgsType = msgsType;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHandlerUrl() {
        return handlerUrl;
    }

    public void setHandlerUrl(String handlerUrl) {
        this.handlerUrl = handlerUrl;
    }

    public String getHandlerParams() {
        return handlerParams;
    }

    public void setHandlerParams(String handlerParams) {
        this.handlerParams = handlerParams;
    }

    public Boolean getSingleHandle() {
        return isSingleHandle;
    }

    public void setSingleHandle(Boolean singleHandle) {
        isSingleHandle = singleHandle;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClientFlag() {
        return clientFlag;
    }

    public void setClientFlag(String clientFlag) {
        this.clientFlag = clientFlag;
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
}

