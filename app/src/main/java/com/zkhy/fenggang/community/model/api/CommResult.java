package com.zkhy.fenggang.community.model.api;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/13 on 10:56
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class CommResult {
    // 心愿统计
//    "wishCountApp": 6,
//    "myClaim": 0,
//    "myPublish": 0

    private int wishCountApp;
    private int myClaim;
    private int myPublish;

    public int getWishCountApp() {
        return wishCountApp;
    }

    public void setWishCountApp(int wishCountApp) {
        this.wishCountApp = wishCountApp;
    }

    public int getMyClaim() {
        return myClaim;
    }

    public void setMyClaim(int myClaim) {
        this.myClaim = myClaim;
    }

    public int getMyPublish() {
        return myPublish;
    }

    public void setMyPublish(int myPublish) {
        this.myPublish = myPublish;
    }
}
