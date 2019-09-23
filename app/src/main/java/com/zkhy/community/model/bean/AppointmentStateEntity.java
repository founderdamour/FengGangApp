package com.zkhy.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 14:51
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class AppointmentStateEntity {
    /**
     * currTime : string
     * state : string
     * weekDay : string
     */

    private String currTime;
    private String state;
    private String weekDay;
    private String currDate;

    private boolean selected;

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
