package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 16:48
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class AppointmentDayEntity {

    /**
     * amTime : 12:19-08:19
     * pmTime : 17:19-13:19
     * amState : true
     * pmState : true
     * amAppointmentNum : 2
     * amSurplusNum : 7
     * pmAppointmentNum : 0
     * pmSurplusNum : 9
     */

    private String amTime;
    private String pmTime;
    private boolean amState;
    private boolean pmState;
    private int amAppointmentNum;
    private int amSurplusNum;
    private int pmAppointmentNum;
    private int pmSurplusNum;

    public String getAmTime() {
        return amTime;
    }

    public void setAmTime(String amTime) {
        this.amTime = amTime;
    }

    public String getPmTime() {
        return pmTime;
    }

    public void setPmTime(String pmTime) {
        this.pmTime = pmTime;
    }

    public boolean isAmState() {
        return amState;
    }

    public void setAmState(boolean amState) {
        this.amState = amState;
    }

    public boolean isPmState() {
        return pmState;
    }

    public void setPmState(boolean pmState) {
        this.pmState = pmState;
    }

    public int getAmAppointmentNum() {
        return amAppointmentNum;
    }

    public void setAmAppointmentNum(int amAppointmentNum) {
        this.amAppointmentNum = amAppointmentNum;
    }

    public int getAmSurplusNum() {
        return amSurplusNum;
    }

    public void setAmSurplusNum(int amSurplusNum) {
        this.amSurplusNum = amSurplusNum;
    }

    public int getPmAppointmentNum() {
        return pmAppointmentNum;
    }

    public void setPmAppointmentNum(int pmAppointmentNum) {
        this.pmAppointmentNum = pmAppointmentNum;
    }

    public int getPmSurplusNum() {
        return pmSurplusNum;
    }

    public void setPmSurplusNum(int pmSurplusNum) {
        this.pmSurplusNum = pmSurplusNum;
    }
}
