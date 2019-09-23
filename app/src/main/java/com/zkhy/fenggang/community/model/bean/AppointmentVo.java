package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 18:06
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class AppointmentVo {

    /**
     * applyTypeCode : string
     * appoDate : 2018-12-12T10:06:19.794Z
     * appoDateType : true
     */

    private String applyTypeCode;
    private String appoDate;
    private boolean appoDateType;

    public AppointmentVo() {
    }

    public AppointmentVo(String applyTypeCode, String appoDate, boolean appoDateType) {
        this.applyTypeCode = applyTypeCode;
        this.appoDate = appoDate;
        this.appoDateType = appoDateType;
    }

    public String getApplyTypeCode() {
        return applyTypeCode;
    }

    public void setApplyTypeCode(String applyTypeCode) {
        this.applyTypeCode = applyTypeCode;
    }

    public String getAppoDate() {
        return appoDate;
    }

    public void setAppoDate(String appoDate) {
        this.appoDate = appoDate;
    }

    public boolean isAppoDateType() {
        return appoDateType;
    }

    public void setAppoDateType(boolean appoDateType) {
        this.appoDateType = appoDateType;
    }
}
