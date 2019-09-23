package com.zkhy.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/27 on 11:37
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class OnlineUserEntity {

    /**
     * id : 1
     * name : 喻克嘉
     * photo : http://58.16.181.23:10086/file/10086/2019/01/ed23e4e4-b7e0-4f05-b370-788bc6d1c1ca.jpg
     * workingAge : null
     * skill : 擅长
     */

    private String id;
    private String name;
    private String photo;
    private String workingAge;
    private String skill;

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
}
