package com.zkhy.community.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.hengyunsoft.platform.biz.party.meeting
 * 版权：中科恒运软件科技股份有限公司贵阳分公司
 * 描述：会议详情
 * 修改人：gbl
 * 修改时间：2018/12/13
 * 修改内容：
 */
public class DjThreeMeetInfoDTO {

    // "会议信息")
    private ExtDjThreeMeet meetDTO;

    // "成员信息")
    private List<DjThreeMeetMemDTO> meetMemDTO;

    // "已读成员")
    private ArrayList<DjThreeMeetMemDTO> redMeetMemDTO;

    // "未读读成员")
    private ArrayList<DjThreeMeetMemDTO> notRedMeetMemDTO;

    // "已读成员")
    private ArrayList<DjThreeMeetMemDTO> joinsMeetMemDTO;

    // "未读读成员")
    private ArrayList<DjThreeMeetMemDTO> leaveMeetMemDTO;

    // "会议人数")
    private Integer totalNum;

    // "参加人")
    private Integer joinsNum;

    // "请假人数")
    private Integer leaveNum;

    //    // "会议提醒时间 1:开始时提醒,2:会前一天,3:会前一周")
//    private Integer notice;

    // "未读认识")
    private int notRednum;

    // "已读认识")
    private int redNum;

    public ExtDjThreeMeet getMeetDTO() {
        return meetDTO;
    }

    public void setMeetDTO(ExtDjThreeMeet meetDTO) {
        this.meetDTO = meetDTO;
    }

    public List<DjThreeMeetMemDTO> getMeetMemDTO() {
        return meetMemDTO;
    }

    public void setMeetMemDTO(List<DjThreeMeetMemDTO> meetMemDTO) {
        this.meetMemDTO = meetMemDTO;
    }

    public ArrayList<DjThreeMeetMemDTO> getRedMeetMemDTO() {
        return redMeetMemDTO;
    }

    public void setRedMeetMemDTO(ArrayList<DjThreeMeetMemDTO> redMeetMemDTO) {
        this.redMeetMemDTO = redMeetMemDTO;
    }

    public ArrayList<DjThreeMeetMemDTO> getNotRedMeetMemDTO() {
        return notRedMeetMemDTO;
    }

    public void setNotRedMeetMemDTO(ArrayList<DjThreeMeetMemDTO> notRedMeetMemDTO) {
        this.notRedMeetMemDTO = notRedMeetMemDTO;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getJoinsNum() {
        return joinsNum;
    }

    public void setJoinsNum(Integer joinsNum) {
        this.joinsNum = joinsNum;
    }

    public Integer getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(Integer leaveNum) {
        this.leaveNum = leaveNum;
    }

    public int getNotRednum() {
        return notRednum;
    }

    public void setNotRednum(int notRednum) {
        this.notRednum = notRednum;
    }

    public int getRedNum() {
        return redNum;
    }

    public void setRedNum(int redNum) {
        this.redNum = redNum;
    }

    public ArrayList<DjThreeMeetMemDTO> getJoinsMeetMemDTO() {
        return joinsMeetMemDTO;
    }

    public void setJoinsMeetMemDTO(ArrayList<DjThreeMeetMemDTO> joinsMeetMemDTO) {
        this.joinsMeetMemDTO = joinsMeetMemDTO;
    }

    public ArrayList<DjThreeMeetMemDTO> getLeaveMeetMemDTO() {
        return leaveMeetMemDTO;
    }

    public void setLeaveMeetMemDTO(ArrayList<DjThreeMeetMemDTO> leaveMeetMemDTO) {
        this.leaveMeetMemDTO = leaveMeetMemDTO;
    }
}
