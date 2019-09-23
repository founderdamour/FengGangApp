package com.zkhy.fenggang.community.model.api;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/13 on 10:53
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PageReq<T> {

    /**
     * data : {"flowId":0,"userId":0}
     * pageNo : 0
     * pageSize : 0
     */

    private T data;
    private int pageNo;
    private int pageSize;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
