package com.zkhy.community.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *   任务处理情况表
 *
 * 表名：b_bm_shot_task_deal
 *
 * @mbggenerated do_not_delete_during_merge
 * addModelClassComment 242 
 */
//  "任务处理情况表"
public class BmShotTaskDeal implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 任务id
     *
     * @mbggenerated
     */
    private Long taskId;

    /**
     * 完成时间
     *
     * @mbggenerated
     */
    private Date doneTime;

    /**
     * 完成情况
     *
     * @mbggenerated
     */
    private String doneDesc;

    /**
     * 处理人
     *
     * @mbggenerated
     */
    private Long doneUser;

    /**
     * 审核状态(0未审核,1通过,2不通过)
     *
     * @mbggenerated
     */
    private Integer approvalStatus;

    /**
     * 审核描述
     *
     * @mbggenerated
     */
    private String approvalDesc;

    /**
     * 审核时间
     *
     * @mbggenerated
     */
    private Date approvalTime;

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

    // "附件列表"
    private Map<String, List<AttachmentEntity>> attachments;

    //------------- 手动新增字段，请写在此后面 -------------
    private static final long serialVersionUID = 1L;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    public String getDoneDesc() {
        return doneDesc;
    }

    public void setDoneDesc(String doneDesc) {
        this.doneDesc = doneDesc;
    }

    public Long getDoneUser() {
        return doneUser;
    }

    public void setDoneUser(Long doneUser) {
        this.doneUser = doneUser;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalDesc() {
        return approvalDesc;
    }

    public void setApprovalDesc(String approvalDesc) {
        this.approvalDesc = approvalDesc;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Map<String, List<AttachmentEntity>> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, List<AttachmentEntity>> attachments) {
        this.attachments = attachments;
    }
}

