package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;


/**
 * @author tianai
 * @createTime 2018-01-30
 */
public class AreaDTO implements Serializable {

    /**
     * 主键id
     * @mbggenerated
     */
    private Long id;
    /**
     * 区域名称
     * @mbggenerated
     */
    private String name;
    /**
     * 地区编码
     *
     * @mbggenerated
     */
    private String code;
    /**
     * 父id
     * @mbggenerated
     */
    private Long parentId;
    /**
     * 经度
     *
     * @mbggenerated
     */
    private double longitude;

    /**
     * 维度
     *
     * @mbggenerated
     */
    private double latitude;

    /**
     * 全名
     *
     * @mbggenerated
     */
    private String fullName;

    /**
     * 排序
     *
     * @mbggenerated
     */
    private Integer orderNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
