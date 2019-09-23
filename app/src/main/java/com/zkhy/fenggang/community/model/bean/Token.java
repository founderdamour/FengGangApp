package com.zkhy.fenggang.community.model.bean;

import java.io.Serializable;

/**
 * @author tyh
 * @createTime 2017-12-15 11:22
 */
public class Token implements Serializable {

    /**
     * token
     */
    private String token;
    /**
     * 有效时间：单位：秒
     */
    private int expire;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}
