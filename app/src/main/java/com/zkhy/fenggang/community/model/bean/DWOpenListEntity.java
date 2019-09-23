package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 党务列表实体
 */
public class DWOpenListEntity implements Serializable {

    private Long id;

    private String title;

    private Long partyOrgId;

    private Date startTime;

    private String content;

    private Long publishUserId;

    private String result;

    private Date createTime;

    private String partyOrgName;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPartyOrgId() {
        return partyOrgId;
    }

    public void setPartyOrgId(Long partyOrgId) {
        this.partyOrgId = partyOrgId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPartyOrgName() {
        return partyOrgName;
    }

    public void setPartyOrgName(String partyOrgName) {
        this.partyOrgName = partyOrgName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
