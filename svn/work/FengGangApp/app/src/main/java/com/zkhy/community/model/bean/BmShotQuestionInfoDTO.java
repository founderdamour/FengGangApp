package com.zkhy.community.model.bean;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * com.hengyunsoft.platform.biz.shot
 * 版权：中科恒运软件科技股份有限公司贵阳分公司
 * 描述：
 * 修改人：gbl
 * 修改时间：2019/3/23
 * 修改内容：
 */
public class BmShotQuestionInfoDTO {

    //  "联系人")
    private String userName;
    //  "联系电话")
    private String phone;
    //  "照片")
    private String photo;


    //  "流程详细")
    private List<BmShotTaskInfoDTO> shotTasks;
    //  "流水详细")
    private List<BmShotStream> streams;

    // "附件列表")
    private Map<String, List<AttachmentEntity>> attachments;

    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 问题编号
     *
     * @mbggenerated
     */
    private String questionCode;

    /**
     * 紧急程度(0普通1紧急2特急)
     *
     * @mbggenerated
     */
    private Integer worryLevel;

    /**
     * 提交问题的用户id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 处理状态(0待分派1进行中2已完成)
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 进行中的小状态(0未完成1待审核2已审核3已回复)
     *
     * @mbggenerated
     */
    private Integer ingStatus;

    /**
     * 问题描述
     *
     * @mbggenerated
     */
    private String questionDesc;

    /**
     * 所属区域code
     *
     * @mbggenerated
     */
    private String areaCode;

    /**
     * 问题详细地址
     *
     * @mbggenerated
     */
    private String questionAddress;

    /**
     * 经度
     *
     * @mbggenerated
     */
    private Double longitude;

    /**
     * 维度
     *
     * @mbggenerated
     */
    private Double latitude;

    /**
     * 回复时间
     *
     * @mbggenerated
     */
    private Date replyTime;

    /**
     * 总结
     *
     * @mbggenerated
     */
    private String summary;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 完成时间
     *
     * @mbggenerated
     */
    private Date doneTime;

    /**
     * 指定完成时间
     *
     * @mbggenerated
     */
    private Date requireDoneTime;

    /**
     * 评价分数(星星)
     *
     * @mbggenerated
     */
    private Integer evaluateStar;

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
     * 当前登录人处理任务Id
     */
    private Long dealTaskId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<BmShotTaskInfoDTO> getShotTasks() {
        return shotTasks;
    }

    public void setShotTasks(List<BmShotTaskInfoDTO> shotTasks) {
        this.shotTasks = shotTasks;
    }

    public List<BmShotStream> getStreams() {
        return streams;
    }

    public void setStreams(List<BmShotStream> streams) {
        this.streams = streams;
    }

    public Map<String, List<AttachmentEntity>> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, List<AttachmentEntity>> attachments) {
        this.attachments = attachments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public Integer getWorryLevel() {
        return worryLevel;
    }

    public void setWorryLevel(Integer worryLevel) {
        this.worryLevel = worryLevel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIngStatus() {
        return ingStatus;
    }

    public void setIngStatus(Integer ingStatus) {
        this.ingStatus = ingStatus;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getQuestionAddress() {
        return questionAddress;
    }

    public void setQuestionAddress(String questionAddress) {
        this.questionAddress = questionAddress;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    public Date getRequireDoneTime() {
        return requireDoneTime;
    }

    public void setRequireDoneTime(Date requireDoneTime) {
        this.requireDoneTime = requireDoneTime;
    }

    public Integer getEvaluateStar() {
        return evaluateStar;
    }

    public void setEvaluateStar(Integer evaluateStar) {
        this.evaluateStar = evaluateStar;
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

    public Long getDealTaskId() {
        return dealTaskId;
    }

    public void setDealTaskId(Long dealTaskId) {
        this.dealTaskId = dealTaskId;
    }
}
