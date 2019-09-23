package com.zkhy.community.model.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/29 on 11:56
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class ThreeMeetEntity {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 会议名称
     *
     * @mbggenerated
     */
    private String meetName;

    /**
     * 会议类型（字典表code）
     *
     * @mbggenerated
     */
    private String meetType;

    /**
     * 负责人
     *
     * @mbggenerated
     */
    private Long memId;

    /**
     * 签到限制距离(单位米)
     *
     * @mbggenerated
     */
    private Integer signLimit;

    /**
     * 计划开始日期
     *
     * @mbggenerated
     */
    private Date planStartDate;

    /**
     * 计划截止日期
     *
     * @mbggenerated
     */
    private Date planEndDate;

    /**
     * 党组织Id
     *
     * @mbggenerated
     */
    private Long orgId;

    /**
     * 会议地点
     *
     * @mbggenerated
     */
    private String meetAddr;

    /**
     * 地点经度
     *
     * @mbggenerated
     */
    private Double meetLon;

    /**
     * 地点纬度
     *
     * @mbggenerated
     */
    private Double meetLat;

    /**
     * 会议内容
     *
     * @mbggenerated
     */
    private String meetContent;

    /**
     * 会议实际开始日期
     *
     * @mbggenerated
     */
    private Date actuaStartlDate;

    /**
     * 提醒时间（1：会前一天，2：会前一周，3：开始就提醒）
     *
     * @mbggenerated
     */
    private Integer remindType;

    /**
     * 主持人
     *
     * @mbggenerated
     */
    private String compere;

    /**
     * 记录人
     *
     * @mbggenerated
     */
    private String recorder;

    /**
     * 到会指导员
     *
     * @mbggenerated
     */
    private String instructor;

    /**
     * 主讲人及身份
     *
     * @mbggenerated
     */
    private String speaker;

    /**
     * 是否已经通知
     *
     * @mbggenerated
     */
    private Boolean noticeIs;

    /**
     * 通知时间
     *
     * @mbggenerated
     */
    private Date noticeTime;

    /**
     * 会议状态（0：未启动，1：未开始，2：进行中，3：已结束，4：已取消，5：已归档）
     *
     * @mbggenerated
     */
    private int meetStatus;

    /**
     * 登录人参加状态（0：未读 1：待参加，会议没有开始也没有请假 2：已请假， 3：未参加，结束了未签到未请假，4：已参加，已签到
     *
     * @mbggenerated
     */
    private int joinStatus;

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
     * 党支部名称
     */
    private String partDeptName;
    /**
     * 负责人
     *
     * @mbggenerated
     */
    private String duty;
    /***
     * 附件详情
     */
    Map<String, List<AttachmentEntity>> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }

    public String getMeetType() {
        return meetType;
    }

    public void setMeetType(String meetType) {
        this.meetType = meetType;
    }

    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }

    public Integer getSignLimit() {
        return signLimit;
    }

    public void setSignLimit(Integer signLimit) {
        this.signLimit = signLimit;
    }



    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getMeetAddr() {
        return meetAddr;
    }

    public void setMeetAddr(String meetAddr) {
        this.meetAddr = meetAddr;
    }

    public Double getMeetLon() {
        return meetLon;
    }

    public void setMeetLon(Double meetLon) {
        this.meetLon = meetLon;
    }

    public Double getMeetLat() {
        return meetLat;
    }

    public void setMeetLat(Double meetLat) {
        this.meetLat = meetLat;
    }

    public String getMeetContent() {
        return meetContent;
    }

    public void setMeetContent(String meetContent) {
        this.meetContent = meetContent;
    }


    public Integer getRemindType() {
        return remindType;
    }

    public void setRemindType(Integer remindType) {
        this.remindType = remindType;
    }

    public String getCompere() {
        return compere;
    }

    public void setCompere(String compere) {
        this.compere = compere;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public Boolean getNoticeIs() {
        return noticeIs;
    }

    public void setNoticeIs(Boolean noticeIs) {
        this.noticeIs = noticeIs;
    }

    public Integer getMeetStatus() {
        return meetStatus;
    }

    public void setMeetStatus(Integer meetStatus) {
        this.meetStatus = meetStatus;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getPartDeptName() {
        return partDeptName;
    }

    public void setPartDeptName(String partDeptName) {
        this.partDeptName = partDeptName;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Map<String, List<AttachmentEntity>> getFiles() {
        return files;
    }

    public void setFiles(Map<String, List<AttachmentEntity>> files) {
        this.files = files;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public Date getActuaStartlDate() {
        return actuaStartlDate;
    }

    public void setActuaStartlDate(Date actuaStartlDate) {
        this.actuaStartlDate = actuaStartlDate;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setMeetStatus(int meetStatus) {
        this.meetStatus = meetStatus;
    }

    public int getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(int joinStatus) {
        this.joinStatus = joinStatus;
    }
}
