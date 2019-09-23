package com.zkhy.community.model.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * com.hengyunsoft.platform.biz.shot
 * 版权：中科恒运软件科技股份有限公司贵阳分公司
 * 描述：问题处理任务
 * 修改人：gbl
 * 修改时间：2019/3/23
 * 修改内容：
 */
public class BmShotTaskInfoDTO{

    // "处理部门"
    private String dealDeptName;

    // "处理人"
    private String dealUserName;

    //  "超期时间，小时计算不足1小时算0,小与或者等于0表示未超期"
    private Integer overdueTime;

    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 问题id
     *
     * @mbggenerated
     */
    private Long questionId;

    /**
     * 处理部门(组织id)
     *
     * @mbggenerated
     */
    private Long deptId;

    /**
     * 任务描述
     *
     * @mbggenerated
     */
    private String taskDesc;

    /**
     * 紧急程度(0普通1紧急2特急)
     *
     * @mbggenerated
     */
    private int worryLevel;

    /**
     * 指定完成时间
     *
     * @mbggenerated
     */
    private Date requireDoneTime;

    /**
     * 完成时间
     *
     * @mbggenerated
     */
    private Date doneTime;

    /**
     * 完成状态状态(0未审核1已审核)
     *
     * @mbggenerated
     */
    private Integer approvalState;

    /**
     * 处理状态(0未完成1已完成2已撤销)
     *
     * @mbggenerated
     */
    private Integer status;

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

    private List<BmShotTaskDeal> taskDeals;

    private Map<String, List<AttachmentEntity>> attachments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public int getWorryLevel() {
        return worryLevel;
    }

    public void setWorryLevel(int worryLevel) {
        this.worryLevel = worryLevel;
    }

    public Date getRequireDoneTime() {
        return requireDoneTime;
    }

    public void setRequireDoneTime(Date requireDoneTime) {
        this.requireDoneTime = requireDoneTime;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    public Integer getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(Integer approvalState) {
        this.approvalState = approvalState;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getDealDeptName() {
        return dealDeptName;
    }

    public void setDealDeptName(String dealDeptName) {
        this.dealDeptName = dealDeptName;
    }

    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    public Integer getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Integer overdueTime) {
        this.overdueTime = overdueTime;
    }

    public Map<String, List<AttachmentEntity>> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, List<AttachmentEntity>> attachments) {
        this.attachments = attachments;
    }

    public List<BmShotTaskDeal> getTaskDeals() {
        return taskDeals;
    }

    public void setTaskDeals(List<BmShotTaskDeal> taskDeals) {
        this.taskDeals = taskDeals;
    }
}
