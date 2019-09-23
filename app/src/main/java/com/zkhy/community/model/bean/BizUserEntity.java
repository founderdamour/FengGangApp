package com.zkhy.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/12 on 9:52
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class BizUserEntity {
    /**
     * id : 540468456441515105
     * name : 汤君平
     * photo : http://58.16.181.23:10086/file/10086/2019/01/23aa49ae-978e-4822-a4ff-3e457051c9be.jpg
     * workingAge :
     * skill : 各种法律咨询问题
     * phone
     */

    private String id;
    private String bizId;
    private String name;
    private String photo;
    private String workingAge;
    private String skill;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getWorkingAge() {
        return workingAge;
    }

    public void setWorkingAge(String workingAge) {
        this.workingAge = workingAge;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
}
