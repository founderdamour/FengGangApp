package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/24 on 10:43
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class HouseDocQAEntity implements Serializable {

    /**
     * answer : string
     * createTime : 2018-12-24T02:41:47.402Z
     * createUser : 0
     * id : 0
     * quesClass : string
     * questType : string
     * question : string
     * updateTime : 2018-12-24T02:41:47.402Z
     * updateUser : 0
     *
     *  "id": null,
     *       "question": "皮肤过敏",
     *       "ansCount": 0,
     *       "consTime": "2018-12-25 09:09:31.0"
     */

    private String answer;
    private String createTime;
    private String createUser;
    private String id;
    private String quesClass;
    private String questType;
    private String question;
    private String updateTime;
    private String updateUser;

    private String ansCount;
    private String consTime;

    private boolean isSelected = false; // 是否选中

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuesClass() {
        return quesClass;
    }

    public void setQuesClass(String quesClass) {
        this.quesClass = quesClass;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
