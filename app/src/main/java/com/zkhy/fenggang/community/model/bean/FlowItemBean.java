package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/13 on 19:25
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class FlowItemBean implements Serializable {
    /**
     * id : 522722126369652801
     * guideId : 522721941786722337
     * flowName : 测试添加
     * content : 测试添加
     */

    private String id;
    private String guideId;
    private String flowName;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
