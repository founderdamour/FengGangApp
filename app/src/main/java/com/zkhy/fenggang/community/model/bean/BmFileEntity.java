package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <pre>
 *  创建:  梁玉涛 2019/4/23 on 11:06
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class BmFileEntity implements Serializable {
    private String bizId;
    private String titleStr;
    private String tip;
    private String bizType;
    private int contentType;

    private ArrayList<ImgSelectEntity> fileList;

    private HashMap<Integer, ImgSelectEntity> fileMap;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public ArrayList<ImgSelectEntity> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<ImgSelectEntity> fileList) {
        this.fileList = fileList;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public HashMap<Integer, ImgSelectEntity> getFileMap() {
        return fileMap;
    }

    public void setFileMap(HashMap<Integer, ImgSelectEntity> fileMap) {
        this.fileMap = fileMap;
    }
}
