package com.zkhy.community.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * com.hengyunsoft.platform.biz.party.meeting
 * 版权：中科恒运软件科技股份有限公司贵阳分公司
 * 描述：会议成员
 * 修改人：gbl
 * 修改时间：2018/12/13
 * 修改内容：
 */
public class DjThreeMeetMemDTO implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 会议Id
     *
     * @mbggenerated
     */
    private Long meetId;

    /**
     * 人员Id
     *
     * @mbggenerated
     */
    private Long memId;

    /**
     * 参加状态（0：未参加，1：已参加，2：已请假）
     *
     * @mbggenerated
     */
    private Byte joinStatus;

    /**
     * 请假事由
     *
     * @mbggenerated
     */
    private String leaveReason;

    /**
     * 是否签到(0：未签到，1：已签到)
     *
     * @mbggenerated
     */
    private Boolean siginStatus;

    /**
     * 签到时间
     *
     * @mbggenerated
     */
    private Date signTime;
    /***
     * 通知消息是否已读
     */
    private boolean msgIsRead;
    /***
     * 通知阅读时间
     */
    private Date ackData;

    private Long userId;

    /***
     * 姓名
     */
    private String name;
    /***
     * 电话
     */
    private String phone;

    /***
     * 头像
     */
    private String photo;

    /***
     * 党组织名称
     */
    private String partyOrg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeetId() {
        return meetId;
    }

    public void setMeetId(Long meetId) {
        this.meetId = meetId;
    }

    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }

    public Byte getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(Byte joinStatus) {
        this.joinStatus = joinStatus;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Boolean getSiginStatus() {
        return siginStatus;
    }

    public void setSiginStatus(Boolean siginStatus) {
        this.siginStatus = siginStatus;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public boolean isMsgIsRead() {
        return msgIsRead;
    }

    public void setMsgIsRead(boolean msgIsRead) {
        this.msgIsRead = msgIsRead;
    }

    public Date getAckData() {
        return ackData;
    }

    public void setAckData(Date ackData) {
        this.ackData = ackData;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPartyOrg() {
        return partyOrg;
    }

    public void setPartyOrg(String partyOrg) {
        this.partyOrg = partyOrg;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}