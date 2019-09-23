package com.zkhy.community.model.bean;


import java.util.List;
import java.util.Map;

public class ExtDjThreeMeet extends DjThreeMeet {
    /**
     * 党支部名称
     */
    private String partDeptName;
    /**
     * 负责人
     *
     * @mbggenerated
     */
    private String duty;
    /***
     * 附件详情
     */
    Map<String, List<AttachmentEntity>> files;

    public String getPartDeptName() {
        return partDeptName;
    }

    public void setPartDeptName(String partDeptName) {
        this.partDeptName = partDeptName;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Map<String, List<AttachmentEntity>> getFiles() {
        return files;
    }

    public void setFiles(Map<String, List<AttachmentEntity>> files) {
        this.files = files;
    }

    /**
     * 登录人参加状态（0：未读 1：待参加，会议没有开始也没有请假 2：已请假， 3：未参加，结束了未签到未请假，4：已参加，已签到
     *
     * @mbggenerated
     */
    private int joinStatus;

    public int getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(int joinStatus) {
        this.joinStatus = joinStatus;
    }
}
