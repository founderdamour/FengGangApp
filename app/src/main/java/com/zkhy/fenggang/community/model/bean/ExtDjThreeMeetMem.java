package com.zkhy.fenggang.community.model.bean;


public class ExtDjThreeMeetMem extends DjThreeMeetMem {
    /**
     * y用户id
     */
    private Long userId;
    /***
     * 姓名
     */
    private String name;
    /***
     * 电话
     */
    private String phone;
    /***
     * 党组织名称
     */
    private String partyOrg;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPartyOrg() {
        return partyOrg;
    }

    public void setPartyOrg(String partyOrg) {
        this.partyOrg = partyOrg;
    }
}
