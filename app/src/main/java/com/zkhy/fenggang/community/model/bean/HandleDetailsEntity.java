package com.zkhy.fenggang.community.model.bean;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/9 on 14:28
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class HandleDetailsEntity {

    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 流程Id 申请流程类型id(1.高龄补贴 2.一孩生育登记 3.二孩生育登记 4.汇川区残疾人证发放和管理 5.居住证明 6.关系证明 7.政审证明
     * 8.文化户口登记 9.小孩入学登记 10.零就业家庭认定 11.就业失业证办理 12.就业困难对象认定 )
     *
     * @mbggenerated
     */
    private int flowId;

    /**
     * 申请编号
     *
     * @mbggenerated
     */
    private String applyCode;

    /**
     * 办理类型(1:本人办理，2：代办帮办)
     *
     * @mbggenerated
     */
    private Integer handleType;

    /**
     * 申请用户Id
     *
     * @mbggenerated
     */
    private String applyUserId;

    /**
     * 申请时间
     *
     * @mbggenerated
     */
    private String applyTime;

    /**
     * 审批状态（最新，0：提交，1：通过，2、驳回）
     *
     * @mbggenerated
     */
    private Integer apprStatus;

    /**
     * 审批意见（最新）
     *
     * @mbggenerated
     */
    private String apprSuggest;

    /**
     * 审批时间（最新）
     *
     * @mbggenerated
     */

    /**
     * 审批人（最新）
     *
     * @mbggenerated
     */
    private Long apprUserId;

    /**
     * 婚姻状态(1：初婚，2：再婚，3：未婚) 就业办事-婚姻状态(0-未婚 1-已婚 2-离异 3-丧偶)
     *
     * @mbggenerated
     */
    private int marryStatus;


    /**
     * 省（区域Id）
     *
     * @mbggenerated
     */
    private String provinceCode;

    /**
     * 市（区域Id）
     *
     * @mbggenerated
     */
    private String cityCode;

    /**
     * 县（市、区）(区域Id)
     *
     * @mbggenerated
     */
    private String countyCode;

    /**
     * 乡镇街道（区域Id）
     *
     * @mbggenerated
     */
    private String townCode;

    /**
     * 所属社区code
     *
     * @mbggenerated
     */
    private String areaCode;

    /**
     * 所属社区名称
     *
     * @mbggenerated
     */
    private String areaName;

    /**
     * 街道详细地址
     *
     * @mbggenerated
     */
    private String addrDetail;


    /**
     * 用户名
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 电话
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 身份证号
     *
     * @mbggenerated
     */
    private String idcard;

    /**
     * 亲属类型(字典code)
     *
     * @mbggenerated
     */
    private String relatives;




    /**
     * 附件列表
     *
     * @mbggenerated
     */
    private Map<String, List<AttachmentEntity>> attachmentMap;




    /**
     * 出生日期
     *
     * @mbggenerated
     */
    private String birthDay;

    /**
     * 失业证编号
     *
     * @mbggenerated
     */
    private String unemploymentNum;

    /**
     * 工作状态类型:0-零就业 ,1-就业困难 ,2-就业失业
     *
     * @mbggenerated
     */
    private Integer workStatusType;

    /**
     * 申请就业困难人员的类型:0-“4050”人员,1-残疾人员,2-低收入家庭人员,3-按城镇人口安置的被征地农民,4-连续失业一年以上的人员
     *
     * @mbggenerated
     */
    private Integer wordTroubleType;


    /**
     * 就业办事-婚姻状态(0-未婚 1-已婚 2-离异 3-丧偶)
     *
     * @mbggenerated
     */
    private Integer employMarryStatus;

    /**
     * 政治面貌(0-群众 1-团员  2-党员)
     *
     * @mbggenerated
     */
    private Integer politicalStatus;

    /**
     * 户口性质（0-农业户口 1-非农业户口）
     *
     * @mbggenerated
     */
    private Integer accountCharacter;

    /**
     * 毕业院校
     *
     * @mbggenerated
     */
    private String graduateSchool;

    /**
     * 毕业日期
     *
     * @mbggenerated
     */
    private String graduateDate;

    /**
     * 毕业专业
     *
     * @mbggenerated
     */
    private String graduateMajor;

    /**
     * 常住地详细地址
     *
     * @mbggenerated
     */
    private String oftenLiveDetailAddr;

    /**
     * 户籍详细地址
     *
     * @mbggenerated
     */
    private String houseRegisterDetailAddr;

    /**
     * 学历（字典表code）
     *
     * @mbggenerated
     */
    private String education;

    /**
     * 性别 0：女  1：男
     *
     * @mbggenerated
     */
    private int sex;

    /**
     * 民族
     *
     * @mbggenerated
     */
    private String nation;

    /**
     * 审批环节：1-社区审批 2-街道审批 3-就业局审批
     *
     * @mbggenerated
     */
    private Integer apprLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public Integer getHandleType() {
        return handleType;
    }

    public void setHandleType(Integer handleType) {
        this.handleType = handleType;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getApprStatus() {
        return apprStatus;
    }

    public void setApprStatus(Integer apprStatus) {
        this.apprStatus = apprStatus;
    }

    public String getApprSuggest() {
        return apprSuggest;
    }

    public void setApprSuggest(String apprSuggest) {
        this.apprSuggest = apprSuggest;
    }

    public Long getApprUserId() {
        return apprUserId;
    }

    public void setApprUserId(Long apprUserId) {
        this.apprUserId = apprUserId;
    }

    public int getMarryStatus() {
        return marryStatus;
    }

    public void setMarryStatus(int marryStatus) {
        this.marryStatus = marryStatus;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getRelatives() {
        return relatives;
    }

    public void setRelatives(String relatives) {
        this.relatives = relatives;
    }

    public Map<String, List<AttachmentEntity>> getAttachmentMap() {
        return attachmentMap;
    }

    public void setAttachmentMap(Map<String, List<AttachmentEntity>> attachmentMap) {
        this.attachmentMap = attachmentMap;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getUnemploymentNum() {
        return unemploymentNum;
    }

    public void setUnemploymentNum(String unemploymentNum) {
        this.unemploymentNum = unemploymentNum;
    }

    public Integer getWorkStatusType() {
        return workStatusType;
    }

    public void setWorkStatusType(Integer workStatusType) {
        this.workStatusType = workStatusType;
    }

    public Integer getWordTroubleType() {
        return wordTroubleType;
    }

    public void setWordTroubleType(Integer wordTroubleType) {
        this.wordTroubleType = wordTroubleType;
    }

    public Integer getEmployMarryStatus() {
        return employMarryStatus;
    }

    public void setEmployMarryStatus(Integer employMarryStatus) {
        this.employMarryStatus = employMarryStatus;
    }

    public Integer getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(Integer politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public Integer getAccountCharacter() {
        return accountCharacter;
    }

    public void setAccountCharacter(Integer accountCharacter) {
        this.accountCharacter = accountCharacter;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    public String getGraduateMajor() {
        return graduateMajor;
    }

    public void setGraduateMajor(String graduateMajor) {
        this.graduateMajor = graduateMajor;
    }

    public String getOftenLiveDetailAddr() {
        return oftenLiveDetailAddr;
    }

    public void setOftenLiveDetailAddr(String oftenLiveDetailAddr) {
        this.oftenLiveDetailAddr = oftenLiveDetailAddr;
    }

    public String getHouseRegisterDetailAddr() {
        return houseRegisterDetailAddr;
    }

    public void setHouseRegisterDetailAddr(String houseRegisterDetailAddr) {
        this.houseRegisterDetailAddr = houseRegisterDetailAddr;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getApprLink() {
        return apprLink;
    }

    public void setApprLink(Integer apprLink) {
        this.apprLink = apprLink;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
