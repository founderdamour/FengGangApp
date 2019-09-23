package com.zkhy.community.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2019/6/5 on 10:07
 *  项目: WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class NoticeTipEntity implements Serializable {

    /**
     * 新推送的消息数量
     *
     * @mbggenerated
     */
    private Integer newNum;

    /**
     * 未读消息数量
     *
     * @mbggenerated
     */
    private Integer noReadNum;

    /**
     * 新推送的消息列表
     *
     * @mbggenerated
     */
    private List<NoticeMsgEntity> msgsCenterInfos;

    public Integer getNewNum() {
        return newNum;
    }

    public void setNewNum(Integer newNum) {
        this.newNum = newNum;
    }

    public Integer getNoReadNum() {
        return noReadNum;
    }

    public void setNoReadNum(Integer noReadNum) {
        this.noReadNum = noReadNum;
    }

    public List<NoticeMsgEntity> getMsgsCenterInfos() {
        return msgsCenterInfos;
    }

    public void setMsgsCenterInfos(List<NoticeMsgEntity> msgsCenterInfos) {
        this.msgsCenterInfos = msgsCenterInfos;
    }
}
