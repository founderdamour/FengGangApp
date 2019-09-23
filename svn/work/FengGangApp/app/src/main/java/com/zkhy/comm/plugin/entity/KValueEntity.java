package com.zkhy.comm.plugin.entity;

import java.io.Serializable;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/8 on 12:49
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class KValueEntity implements Serializable {

    private String key;
    private String value;

    public KValueEntity() {
    }

    public KValueEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
