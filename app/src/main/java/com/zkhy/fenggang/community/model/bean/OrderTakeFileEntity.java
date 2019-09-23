package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/8 on 18:19
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class OrderTakeFileEntity implements Serializable {

    /**
     * applyTypeCode : string
     * appoDate : 2019-01-08T10:15:29.623Z
     * codeName : string
     * handleTime : 2019-01-08T10:15:29.623Z
     * windowId : 0
     * windowName : string
     */

    private String applyTypeCode;
    private String appoDate;
    private String codeName;
    private String handleTime;
    private String windowId;
    private String windowName;

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

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getWindowId() {
        return windowId;
    }

    public void setWindowId(String windowId) {
        this.windowId = windowId;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }
}
