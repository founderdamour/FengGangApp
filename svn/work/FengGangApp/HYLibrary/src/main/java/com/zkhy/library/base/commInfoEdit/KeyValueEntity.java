package com.zkhy.library.base.commInfoEdit;

import java.io.Serializable;

public class KeyValueEntity implements Serializable {

    private String title;
    private String content;
    private int requestCode;

    private String inputTip;
    private String inputHint;

    private int maxSize;
    private int minSize;

    public KeyValueEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public String getInputTip() {
        return inputTip;
    }

    public void setInputTip(String inputTip) {
        this.inputTip = inputTip;
    }

    public String getInputHint() {
        return inputHint;
    }

    public void setInputHint(String inputHint) {
        this.inputHint = inputHint;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }
}
