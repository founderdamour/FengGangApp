package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/17 on 22:02
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class LawQuestionEntity implements Serializable {

    /**
     * id : 523540839595509153
     * question : 测试3
     * questType : 2
     * quesClass : 2
     * answer : 测试33
     * createTime : 2018-12-15 16:44:23
     * createUser : 1
     * updateTime : 2018-12-15 16:44:23
     * updateUser : 1
     * <p>
     * "ansCount": 0,
     * "consTime": "string",
     * "id": 0,
     * "question": "string"
     */

    private String id;
    private String question;
    private String questType;
    private String quesClass;
    private String answer;
    private String createTime;
    private String createUser;
    private String updateTime;
    private String updateUser;

    private String ansCount; // 回答问题人数
    private String consTime; // 咨询时间

    private boolean isSelected = false; // 是否选中


    // 详情


//    "consultQuestion": "string",
//            "id": 0,
//            "phone": "string",
//            "quesTime": "2019-01-02T03:51:11.249Z",
//            "replyList": [
//    {
//        "answerUserName": "string",
//            "consTime": "2019-01-02T03:51:11.249Z",
//            "consultQuestion": "string",
//            "quesTime": "2019-01-02T03:51:11.249Z",
//            "question": "string",
//            "reType": 0,
//            "userName": "string"
//    }
//    ],
//            "userId": "string",
//            "userName": "string",
//            "userPhoto": "string"


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getQuesClass() {
        return quesClass;
    }

    public void setQuesClass(String quesClass) {
        this.quesClass = quesClass;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getAnsCount() {
        return ansCount;
    }

    public void setAnsCount(String ansCount) {
        this.ansCount = ansCount;
    }

    public String getConsTime() {
        return consTime;
    }

    public void setConsTime(String consTime) {
        this.consTime = consTime;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
