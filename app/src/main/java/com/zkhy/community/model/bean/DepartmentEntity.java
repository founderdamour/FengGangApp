package com.zkhy.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/10 on 17:25
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class DepartmentEntity {
    /**
     * id : 31
     * unitName : 汇川区民宗局
     * provinceId : 1
     * linkPerson :
     * linkPhone : null
     * pid : null
     */

    private String id;
    private String unitName;
    private String provinceId;
    private String linkPerson;
    private Object linkPhone;
    private Object pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public Object getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(Object linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Object getPid() {
        return pid;
    }

    public void setPid(Object pid) {
        this.pid = pid;
    }
}
