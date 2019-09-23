package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 20:23
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PersonWorkVo {
    /**
     * addrDetail : string
     * applyUserId : 0
     * cityId : 0
     * county : 0
     * flowId : 0
     * handleType : true
     * id : 0
     * marryStatus : 0
     * provinceId : 0
     * town : 0
     */
    private String idcard;
    private String phone;
    private String name;

    private String addrDetail;
    private String areaCode;
    private String applyUserId;
    private int cityId;
    private int county;
    private int flowId;
    private int handleType;
    private String id;
    private int marryStatus;//婚姻状态(1：初婚，2：再婚，3：未婚)
    private int provinceId;
    private int town;
    private String apprUserId;

    //
    private String oftenLiveDetailAddr; // 常住地详细地址
    private String houseRegisterDetailAddr; // 户籍详细地址
    private String sex; // 性别 0：女 1：男
    private String unemploymentNum; // 失业证编号
    private String education; // 学历（字典表code）
    private String birthDay; // 生日
    private String nation;    //民族

    private int politicalStatus;//政治面貌(0-群众 1-团员 2-党员)
    private String graduateDate; //	毕业日期
    private String graduateSchool; //毕业院校
    private String graduateMajor; //毕业专业
    private int employMarryStatus; // 婚姻状态

    private int accountCharacter; //户口性质（0-农业户口 1-非农业户口）

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public int getHandleType() {
        return handleType;
    }

    public void setHandleType(int handleType) {
        this.handleType = handleType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMarryStatus() {
        return marryStatus;
    }

    public void setMarryStatus(int marryStatus) {
        this.marryStatus = marryStatus;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getTown() {
        return town;
    }

    public void setTown(int town) {
        this.town = town;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUnemploymentNum() {
        return unemploymentNum;
    }

    public void setUnemploymentNum(String unemploymentNum) {
        this.unemploymentNum = unemploymentNum;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(int politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public int getAccountCharacter() {
        return accountCharacter;
    }

    public void setAccountCharacter(int accountCharacter) {
        this.accountCharacter = accountCharacter;
    }

    public String getGraduateMajor() {
        return graduateMajor;
    }

    public void setGraduateMajor(String graduateMajor) {
        this.graduateMajor = graduateMajor;
    }

    public String getApprUserId() {
        return apprUserId;
    }

    public void setApprUserId(String apprUserId) {
        this.apprUserId = apprUserId;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployMarryStatus() {
        return employMarryStatus;
    }

    public void setEmployMarryStatus(int employMarryStatus) {
        this.employMarryStatus = employMarryStatus;
    }
}
