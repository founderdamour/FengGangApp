package com.zkhy.community.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *   随手拍流水记录表
 *
 * 表名：b_bm_shot_stream
 *
 * @mbggenerated do_not_delete_during_merge
 * addModelClassComment 242 
 */
// "随手拍流水记录表")
public class BmShotStream implements Serializable {
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
     * 环节(0提交问题1指派2部门处理3确认部门处理完成4反馈给提交者)
     *
     * @mbggenerated
     */
    private Integer currStage;

    /**
     * 操作用户id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 操作用户所属部门
     *
     * @mbggenerated
     */
    private Long deptId;

    /**
     * 操作描述
     *
     * @mbggenerated
     */
    private String opDesc;

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

    public Integer getCurrStage() {
        return currStage;
    }

    public void setCurrStage(Integer currStage) {
        this.currStage = currStage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
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