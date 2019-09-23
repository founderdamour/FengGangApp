package com.zkhy.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/13 on 16:38
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PayObjectEntity {

    private int id;
    private int objIcon;
    private String objName;
    private int order;

    public PayObjectEntity() {
    }

    public PayObjectEntity(int id, int objIcon, String objName, int order) {
        this.id = id;
        this.objIcon = objIcon;
        this.objName = objName;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjIcon() {
        return objIcon;
    }

    public void setObjIcon(int objIcon) {
        this.objIcon = objIcon;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
