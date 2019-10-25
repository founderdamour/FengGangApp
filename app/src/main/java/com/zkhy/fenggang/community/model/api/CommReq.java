package com.zkhy.fenggang.community.model.api;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/13 on 10:56
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class CommReq {
//    flowId	integer($int64)
//    申请流程类型id(1:高龄补贴,2:一孩生育登记,3:二孩生育登记,4:汇川区残疾人证发放和管理 5:全部)
//
//    userId	integer($int64)
//    当前用户id

    private String id;
    private String deptId;
    private String userId;
    private int flowId;

    private String name;
    // 场馆特用
    private String palceStatus;//-1 所有
    private String useStatus; // -1

    private String startTime; // "2018-12-01 09:20:13"
    private String endTime; // "2018-12-15 09:20:13"

    private String quesClass;
    private String questType;
    private String question;

    // 获得律师事务所列表
    private String orgName;
    private String orgType;
    private String addr;

    private boolean fromApp;
    private double lat;
    private double lon;
    private double officeLat;
    private double officeLon;


    // 警务列表
    private String officeName;
    private String officeType;

    // "接收人id")
//    private Long userId;
    // "是否已读")
    private Boolean isRead;
    // "消息类型 #MsgsCenterType  WAIT:待办 NOTIFY:通知 PUBLICITY:公示公告 ")
//    private MsgsCenterType msgsType;


    // 注册
//    {
//        "registerIs": false,
//            "account": "liangyt",
//            "password": "123456",
//            "phone": "15285536458",
//            "captcha": "121212"
//    }
    private boolean registerIs;
    private String account;
    private String password;
    private String phone;
    private String captcha;

    private String idcard;

    //    name	string 姓名
    //    orgId	integer($int64) 党组织Id,-1:所有
    //    payStatus	integer($int32)  是否缴费(-1:所有,0:未交费，1：已缴费)
    //    selectType	integer($int32) 查询类型（1:为所有，2:当前登录用户）
    //    yearMonth	string 年月
    private int orgId;
    private int payStatus;
    private int selectType;
    private String yearMonth;

    //    areaCode	string
//    所属街道
//    flag	integer($int32)
//    状态标识：0、心愿清单 1、我的认领 2、我的发布
//    userId	integer($int64)
//    用户id
//    wishStatus		integer($int32)
//      状态： 0、待审核 1、待认领 2、进行中 3、已完成 4、已退回
    private String areaCode;
    private String informName;
    private String informType;

    private String flag;
    private String wishStatus;
    private String status;
    private String streetCode;
    /**
     * 心愿发布
     */
    private String applyName;
    private String applyPhone;
    private String wishContent;
    private String finshMessage;
    private String photo;

    /**
     * 密码修改
     */
    private String resetType; // 重置密码方式：1-修改密码 ，2-重置密码
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    // 会议部分
    private int meetStatus;

    // 登录人参加状态（0：未读 1：待参加，会议没有开始也没有请假 2：已请假， 3：未参加，结束了未签到未请假，4：已参加，已签到
    private int joinStatus;

    // 法律咨询
    private String consId;
    private String answer;
    private boolean fromWeb;

    /**
     * 党务公开
     */
    private String openPartyType;

    /**
     * 培训就业类型
     * <p>
     * 1培训2就业
     */
    private int type;

    private int publisState;

    public String getOpenPartyType() {
        return openPartyType;
    }

    public void setOpenPartyType(String openPartyType) {
        this.openPartyType = openPartyType;
    }

    public String getBasicSystemType() {
        return basicSystemType;
    }

    public void setBasicSystemType(String basicSystemType) {
        this.basicSystemType = basicSystemType;
    }

    private String basicSystemType;

    public CommReq() {
    }

    public CommReq(String userId, int flowId) {
        this.userId = userId;
        this.flowId = flowId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPalceStatus() {
        return palceStatus;
    }

    public void setPalceStatus(String palceStatus) {
        this.palceStatus = palceStatus;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getQuesClass() {
        return quesClass;
    }

    public void setQuesClass(String quesClass) {
        this.quesClass = quesClass;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public boolean isRegisterIs() {
        return registerIs;
    }

    public void setRegisterIs(boolean registerIs) {
        this.registerIs = registerIs;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public int getSelectType() {
        return selectType;
    }

    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getWishStatus() {
        return wishStatus;
    }

    public void setWishStatus(String wishStatus) {
        this.wishStatus = wishStatus;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public String getWishContent() {
        return wishContent;
    }

    public void setWishContent(String wishContent) {
        this.wishContent = wishContent;
    }

    public String getFinshMessage() {
        return finshMessage;
    }

    public void setFinshMessage(String finshMessage) {
        this.finshMessage = finshMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getInformName() {
        return informName;
    }

    public void setInformName(String informName) {
        this.informName = informName;
    }

    public String getInformType() {
        return informType;
    }

    public void setInformType(String informType) {
        this.informType = informType;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public int getMeetStatus() {
        return meetStatus;
    }

    public void setMeetStatus(int meetStatus) {
        this.meetStatus = meetStatus;
    }

    public boolean isFromApp() {
        return fromApp;
    }

    public void setFromApp(boolean fromApp) {
        this.fromApp = fromApp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getOfficeLat() {
        return officeLat;
    }

    public void setOfficeLat(double officeLat) {
        this.officeLat = officeLat;
    }

    public double getOfficeLon() {
        return officeLon;
    }

    public void setOfficeLon(double officeLon) {
        this.officeLon = officeLon;
    }

    public String getConsId() {
        return consId;
    }

    public void setConsId(String consId) {
        this.consId = consId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFromWeb() {
        return fromWeb;
    }

    public void setFromWeb(boolean fromWeb) {
        this.fromWeb = fromWeb;
    }

    public int getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(int joinStatus) {
        this.joinStatus = joinStatus;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPublisState() {
        return publisState;
    }

    public void setPublisState(int publisState) {
        this.publisState = publisState;
    }
}
