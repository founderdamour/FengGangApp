package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 参会人员表
 * <p>
 * 表名：b_dj_three_meet_mem
 *
 * @mbggenerated do_not_delete_during_merge
 * addModelClassComment 242
 */
public class DjThreeMeetMem implements Serializable {
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
     * 签到地址
     *
     * @mbggenerated
     */
    private String signAddr;

    /**
     * 参加状态（0：未读 1：待参加，会议没有开始也没有请假 2：已请假， 3：未参加，结束了未签到未请假，4：已参加，已签到
     *
     * @mbggenerated
     */
    private Integer joinStatus;

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

    /**
     * 查看会议详情时间
     *
     * @mbggenerated
     */
    private Date ackData;

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

    //------------- 手动新增字段，请写在此后面 -------------


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

    public String getSignAddr() {
        return signAddr;
    }

    public void setSignAddr(String signAddr) {
        this.signAddr = signAddr;
    }

    public Integer getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(Integer joinStatus) {
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

    public Date getAckData() {
        return ackData;
    }

    public void setAckData(Date ackData) {
        this.ackData = ackData;
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