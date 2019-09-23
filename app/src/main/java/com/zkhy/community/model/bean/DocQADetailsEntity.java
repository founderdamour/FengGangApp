package com.zkhy.community.model.bean;

import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/2 on 17:28
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class DocQADetailsEntity {
    /**
     * id : 530072430501167265
     * userName : liyuanyuan1
     * userId : 527800750307295169
     * userPhoto : null
     * answerStatus
     * phone : 13117477178
     * consultQuestion : 在线医生咨询！
     * ansNum : 1
     * quesTime : 2019-01-02 17:18:36
     * replyList : [{"userName":"liyuanyuan1","userId":"527800750307295169","userPhoto":null,"consultQuestion":"在线医生咨询！","answerStatus":"1","quesTime":"2019-01-02 17:18:36","reType":1,"answerUserName":"liyuanyuan1","answerUserId":"527800750307295169","answerUserPhoto":null,"question":"1111","consTime":"2019-01-02 17:39:51"}]
     */

    private String id;
    private String userName;
    private String userId;
    private Object userPhoto;
    private String phone;
    private String consultQuestion;
    private String ansNum;
    private String quesTime;
    private int answerStatus; // 状态（0：未回答，1：追问 2：已回答，3：已结束）

    private List<DocQAReplyBean> replyList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConsultQuestion() {
        return consultQuestion;
    }

    public void setConsultQuestion(String consultQuestion) {
        this.consultQuestion = consultQuestion;
    }

    public String getAnsNum() {
        return ansNum;
    }

    public void setAnsNum(String ansNum) {
        this.ansNum = ansNum;
    }

    public String getQuesTime() {
        return quesTime;
    }

    public void setQuesTime(String quesTime) {
        this.quesTime = quesTime;
    }

    public List<DocQAReplyBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<DocQAReplyBean> replyList) {
        this.replyList = replyList;
    }

    public int getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(int answerStatus) {
        this.answerStatus = answerStatus;
    }
}
