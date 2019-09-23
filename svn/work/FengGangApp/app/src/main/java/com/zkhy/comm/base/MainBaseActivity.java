package com.zkhy.comm.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.sinothk.comm.utils.ToastUtil;
import com.sinothk.map.amap.location.AMapLocationCallback;
import com.sinothk.map.amap.location.AMapLocationEntity;
import com.sinothk.map.amap.location.MapLocationHelper;
import com.zkhy.comm.plugin.version.VersionDialog;
import com.zkhy.community.MainActivity;
import com.zkhy.community.model.cache.DataCache;
import com.zkhy.community.model.cache.KeyWord;
import com.zkhy.library.utils.ActivityUtil;

import java.util.*;

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
    public void onBackPressed() {
        //实现Home键效果
        //super.onBackPressed();这句话一定要注掉,不然又去调用默认的back处理方式了
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.addActivity(this);

        // 处理人员定时获取位置
        locationCheck();
        // 定时器
        timer = new Timer();
        timer.schedule(task, 5 * 60 * 1000, 5 * 60 * 1000);//5 * 60 * 1000
    }

    protected abstract void locationCheck();

    /**
     * 获得版本信息
     *
     * @param mActivity
     * @param tipLastVersion
     */
    public void checkNewVersion(final Activity mActivity, final boolean tipLastVersion) {
        mActivity.runOnUiThread(() -> VersionDialog.show(mActivity, tipLastVersion));
    }


    /**
     * ======================================== 定时器 =====================================
     */
    private Timer timer;

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Log.e("TimerTask = ", new Date().getTime() + "");

            doTimerEvent();
        }
    };

    protected abstract void doTimerEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
