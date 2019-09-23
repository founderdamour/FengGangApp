package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/15 on 18:08
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class LawCountEntity {

    /**
     * 个人咨询数
     *
     * @mbggenerated
     */
    private int consultNum;

    /**
     * 获取每天解答问题数量
     *
     * @mbggenerated
     */
    private int ansNum;

    /**
     * 系统法律服务机构数量
     *
     * @mbggenerated
     */
    private int orgNum;

    public int getConsultNum() {
        return consultNum;
    }

    public void setConsultNum(int consultNum) {
        this.consultNum = consultNum;
    }

    public int getAnsNum() {
        return ansNum;
    }

    public void setAnsNum(int ansNum) {
        this.ansNum = ansNum;
    }

    public int getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(int orgNum) {
        this.orgNum = orgNum;
    }
}
