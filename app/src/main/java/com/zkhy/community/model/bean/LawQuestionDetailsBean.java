package com.zkhy.community.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/2 on 13:45
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class LawQuestionDetailsBean implements Serializable {

    /**
     * consultQuestion : string
     * id : 0
     * phone : string
     * quesTime : 2019-01-02T03:51:11.249Z
     * replyList : [{"answerUserName":"string","consTime":"2019-01-02T03:51:11.249Z","consultQuestion":"string","quesTime":"2019-01-02T03:51:11.249Z","question":"string","reType":0,"userName":"string"}]
     * userId : string
     * userName : string
     * userPhoto : string
     */

    private String consultQuestion;
    private String id;
    private String phone;
    private String quesTime;
    private String userId;
    private String userName;
    private String userPhoto;
    private String ansNum;
    private List<LawQuestionReplyBean> replyList;
    private int answerStatus; // 状态（0：未回答，1：追问 2：已回答，3：已结束）

    public String getConsultQuestion() {
        return consultQuestion;
    }

    public void setConsultQuestion(String consultQuestion) {
        this.consultQuestion = consultQuestion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuesTime() {
        return quesTime;
    }

    public void setQuesTime(String quesTime) {
        this.quesTime = quesTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public List<LawQuestionReplyBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<LawQuestionReplyBean> replyList) {
        this.replyList = replyList;
    }

    public int getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(int answerStatus) {
        this.answerStatus = answerStatus;
    }

    public String getAnsNum() {
        return ansNum;
    }

    public void setAnsNum(String ansNum) {
        this.ansNum = ansNum;
    }
}
