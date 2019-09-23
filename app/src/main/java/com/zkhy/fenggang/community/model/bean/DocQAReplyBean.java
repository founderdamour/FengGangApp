package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/2 on 17:30
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class DocQAReplyBean implements Serializable {

    /**
     * userName : liyuanyuan1
     * userId : 527800750307295169
     * userPhoto : null
     * consultQuestion : 在线医生咨询！
     * answerStatus : 1
     * quesTime : 2019-01-02 17:18:36
     * reType : 1
     * answerUserName : liyuanyuan1
     * answerUserId : 527800750307295169
     * answerUserPhoto : null
     * question : 1111
     * consTime : 2019-01-02 17:39:51
     */

    private String userName;
    private String userId;
    private Object userPhoto;
    private String consultQuestion;
    private String answerStatus;
    private String quesTime;
    private int reType;
    private String answerUserName;
    private String answerUserId;
    private String answerUserPhoto;
    private String question;
    private String consTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(Object userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getConsultQuestion() {
        return consultQuestion;
    }

    public void setConsultQuestion(String consultQuestion) {
        this.consultQuestion = consultQuestion;
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }

    public String getQuesTime() {
        return quesTime;
    }

    public void setQuesTime(String quesTime) {
        this.quesTime = quesTime;
    }

    public int getReType() {
        return reType;
    }

    public void setReType(int reType) {
        this.reType = reType;
    }

    public String getAnswerUserName() {
        return answerUserName;
    }

    public void setAnswerUserName(String answerUserName) {
        this.answerUserName = answerUserName;
    }

    public String getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(String answerUserId) {
        this.answerUserId = answerUserId;
    }

    public String getAnswerUserPhoto() {
        return answerUserPhoto;
    }

    public void setAnswerUserPhoto(String answerUserPhoto) {
        this.answerUserPhoto = answerUserPhoto;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getConsTime() {
        return consTime;
    }

    public void setConsTime(String consTime) {
        this.consTime = consTime;
    }
}
