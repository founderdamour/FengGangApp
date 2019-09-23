package com.zkhy.community.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/13 on 19:24
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class FlowInfoEntity implements Serializable {

    /**
     * id : 522721941786722337
     * flowId : null
     * flowName : 测试添加
     * deptId : 1
     * deptName : 2
     * certifyType : wm_con_cer_type_imm
     * handleObj : wm_con_han_com
     * handleDate :
     * owerTheme : wm_con_th_sch
     * createTime : 2018-12-13 10:31:13
     * createUser : null
     * updateTime : 2018-12-13 10:31:13
     * updateUser : null
     * fileList : [{"id":"522722126369652801","guideId":"522721941786722337","flowName":"测试添加","content":"测试添加"}]
     */

    private String id;
    private Object flowId;
    private String flowName;
    private String deptId;
    private String deptName;
    private String certifyType;
    private String handleObj;
    private String handleDate;
    private String owerTheme;
    private String createTime;
    private Object createUser;
    private String updateTime;
    private Object updateUser;
    private boolean isNetHandle;// 是否可网上办理
    private String netHandleUrl;// 网上办理地址

    private ArrayList<FlowItemBean> fileList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getFlowId() {
        return flowId;
    }

    public void setFlowId(Object flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCertifyType() {
        return certifyType;
    }

    public void setCertifyType(String certifyType) {
        this.certifyType = certifyType;
    }

    public String getHandleObj() {
        return handleObj;
    }

    public void setHandleObj(String handleObj) {
        this.handleObj = handleObj;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public String getOwerTheme() {
        return owerTheme;
    }

    public void setOwerTheme(String owerTheme) {
        this.owerTheme = owerTheme;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Object createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Object getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Object updateUser) {
        this.updateUser = updateUser;
    }

    public ArrayList<FlowItemBean> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<FlowItemBean> fileList) {
        this.fileList = fileList;
    }

    public boolean isNetHandle() {
        return isNetHandle;
    }

    public void setNetHandle(boolean netHandle) {
        isNetHandle = netHandle;
    }

    public String getNetHandleUrl() {
        return netHandleUrl;
    }

    public void setNetHandleUrl(String netHandleUrl) {
        this.netHandleUrl = netHandleUrl;
    }
}
