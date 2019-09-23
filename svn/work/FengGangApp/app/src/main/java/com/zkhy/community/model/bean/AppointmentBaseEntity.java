package com.zkhy.community.model.bean;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 14:49
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class AppointmentBaseEntity {
    /**
     * appointmentStateList : [{"currTime":"string","state":"string","weekDay":"string"}]
     * id : 0
     * windowName : string
     */

    private String id;
    private String windowName;
    private List<AppointmentStateEntity> appointmentStateList;
    private Date currDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    public List<AppointmentStateEntity> getAppointmentStateList() {
        return appointmentStateList;
    }

    public void setAppointmentStateList(List<AppointmentStateEntity> appointmentStateList) {
        this.appointmentStateList = appointmentStateList;
    }

    public Date getCurrDate() {
        return currDate;
    }

    public void setCurrDate(Date currDate) {
        this.currDate = currDate;
    }
}
