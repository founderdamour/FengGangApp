package com.zkhy.fenggang.community.model.api;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 14:46
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class BaseData<T> {

    /**
     * data : {"appointmentStateList":[{"currTime":"string","state":"string","weekDay":"string"}],"id":0,"windowName":"string"}
     * errcode : 0
     * errmsg : string
     */

    private T data;
    private int errcode;
    private String errmsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
