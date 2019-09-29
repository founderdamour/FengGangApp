package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 培训就业实体
 */
public class TrainEmploymentEntity implements Serializable {

    private String id;

    // 1培训2就业
    private int type;

    // 0未发布1已发布
    private int publisState;

    private String publishContent;

    private Date publishTime;

    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPublisState() {
        return publisState;
    }

    public void setPublisState(int publisState) {
        this.publisState = publisState;
    }

    public String getPublishContent() {
        return publishContent;
    }

    public void setPublishContent(String publishContent) {
        this.publishContent = publishContent;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
