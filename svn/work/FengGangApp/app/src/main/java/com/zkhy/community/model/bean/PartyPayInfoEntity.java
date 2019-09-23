package com.zkhy.community.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/22 on 16:25
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PartyPayInfoEntity implements Serializable {

    /**
     * baseSalary : 0
     * id : 0
     * name : string
     * orgId : 0
     * orgName : string
     * payBase : 0
     * payModel : 0
     * payStatus : true
     * payType : 0
     * payableAmout : 0
     * yearMonth : string
     */

    private String baseSalary;
    private String id;
    private String name;
    private String orgId;
    private String orgName;
    private double payBase;
    private int payModel;
    private boolean payStatus;
    private int payType;
    private String payableAmout;
    private String yearMonth;
    private String updateTime;

    public String getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(String baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public double getPayBase() {
        return payBase;
    }

    public void setPayBase(double payBase) {
        this.payBase = payBase;
    }

    public int getPayModel() {
        return payModel;
    }

    public void setPayModel(int payModel) {
        this.payModel = payModel;
    }

    public boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPayableAmout() {
        return payableAmout;
    }

    public void setPayableAmout(String payableAmout) {
        this.payableAmout = payableAmout;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
