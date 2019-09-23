package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 15:17
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class AppointmentEntity {

    /**
     * applyTime : 2018-12-12T07:14:05.604Z
     * appoDate : 2018-12-12T07:14:05.604Z
     * appoDateType : true
     * appoNo : string
     * appoNum : 0
     * createTime : 2018-12-12T07:14:05.604Z
     * createUser : 0
     * handleStatus : 0
     * handleTime : 2018-12-12T07:14:05.604Z
     * id : 0
     * updateTime : 2018-12-12T07:14:05.604Z
     * updateUser : 0
     * userId : 0
     * windowId : 0
     * windowName : string
     */



    private String applyTime;
    private String appoDate;
    private boolean appoDateType;
    private String appoNo;
    private int appoNum;
    private String createTime;
    private int createUser;
    private int handleStatus;
    private String handleTime;
    private int id;
    private String updateTime;
    private int updateUser;
    private int userId;
    private int windowId;
    private String windowName;

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
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

    public String getAppoNo() {
        return appoNo;
    }

    public void setAppoNo(String appoNo) {
        this.appoNo = appoNo;
    }

    public int getAppoNum() {
        return appoNum;
    }

    public void setAppoNum(int appoNum) {
        this.appoNum = appoNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public int getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(int handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWindowId() {
        return windowId;
    }

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }
}
