package com.zkhy.community.model.bean;

import java.util.Date;

/**
 * <pre>
 *  创建:  梁玉涛 2019/7/29 on 17:08
 *  项目: WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */

public class HomeDocInfoEntity {

    private WmUserSaveReqDTO wmUserSaveReqDTO;

    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 附件id
     *
     * @mbggenerated
     */
    private Long attId;

    /**
     * 姓名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 职称
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 所属单位
     *
     * @mbggenerated
     */
    private String unit;

    /**
     * 所属科室
     *
     * @mbggenerated
     */
    private String dept;

    /**
     * 擅长
     *
     * @mbggenerated
     */
    private String skill;

    /**
     * 医生简介
     *
     * @mbggenerated
     */
    private String instruction;

    /**
     * 账号Id
     *
     * @mbggenerated
     */
    private Long userId;

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

    private static final long serialVersionUID = 1L;

    public WmUserSaveReqDTO getWmUserSaveReqDTO() {
        return wmUserSaveReqDTO;
    }

    public void setWmUserSaveReqDTO(WmUserSaveReqDTO wmUserSaveReqDTO) {
        this.wmUserSaveReqDTO = wmUserSaveReqDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttId() {
        return attId;
    }

    public void setAttId(Long attId) {
        this.attId = attId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}