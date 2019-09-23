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

public class LawyerInfoEntity{

    // "账号信息"
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
     * 联系电话
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 执业证号
     *
     * @mbggenerated
     */
    private String practiceNum;

    /**
     * 值班开始时间
     *
     * @mbggenerated
     */
    private Date dutyStartDate;

    /**
     * 值班结束时间
     *
     * @mbggenerated
     */
    private Date dutyEndDate;

    /**
     * 擅长
     *
     * @mbggenerated
     */
    private String skill;

    /**
     * 关联账号id
     *
     * @mbggenerated
     */
    private Long userId;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPracticeNum() {
        return practiceNum;
    }

    public void setPracticeNum(String practiceNum) {
        this.practiceNum = practiceNum;
    }

    public Date getDutyStartDate() {
        return dutyStartDate;
    }

    public void setDutyStartDate(Date dutyStartDate) {
        this.dutyStartDate = dutyStartDate;
    }

    public Date getDutyEndDate() {
        return dutyEndDate;
    }

    public void setDutyEndDate(Date dutyEndDate) {
        this.dutyEndDate = dutyEndDate;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}