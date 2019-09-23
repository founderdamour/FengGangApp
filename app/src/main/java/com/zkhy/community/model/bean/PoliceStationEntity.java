package com.zkhy.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/18 on 17:22
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PoliceStationEntity {

    /**
     * addr : string
     * id : 0
     * markStatus : true
     * officeName : string
     * officeType : string
     * phone : string
     * telephone : string
     * "officeLon": "106.6947555542",
     *         "officeLat": "26.5989651565"
     */
    private String addr;
    private String id;
    private boolean markStatus;
    private String officeName;
    private String officeType;
    private String phone;
    private String telephone;
    private double officeLon;
    private double officeLat;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMarkStatus() {
        return markStatus;
    }

    public void setMarkStatus(boolean markStatus) {
        this.markStatus = markStatus;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getOfficeLon() {
        return officeLon;
    }

    public void setOfficeLon(double officeLon) {
        this.officeLon = officeLon;
    }

    public double getOfficeLat() {
        return officeLat;
    }

    public void setOfficeLat(double officeLat) {
        this.officeLat = officeLat;
    }
}
