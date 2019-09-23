package com.zkhy.library.mvp;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/5 on 16:51
 *  项目:  WuMinAndroid
 *  描述: 通用View
 *  更新:
 * <pre>
 */
public interface AndroidView {


    void loadingShow();

    void loadingDismiss();

    void showTip(String msg);

    void loadComplete(Object resultData);
}
