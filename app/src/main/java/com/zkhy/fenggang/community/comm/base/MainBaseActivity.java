package com.zkhy.fenggang.community.comm.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.zkhy.fenggang.community.comm.plugin.version.VersionDialog;
import com.zkhy.library.utils.ActivityUtil;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/18 on 10:57
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public abstract class MainBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.addActivity(this);
    }

    /**
     * 获得版本信息
     *
     * @param mActivity
     * @param tipLastVersion
     */
    public void checkNewVersion(final Activity mActivity, final boolean tipLastVersion) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                VersionDialog.show(mActivity, tipLastVersion);
            }
        });
    }
}
