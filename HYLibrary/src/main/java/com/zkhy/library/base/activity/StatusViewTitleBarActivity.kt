package com.zkhy.library.base.activity

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.zkhy.library.utils.ActivityUtil

/**
 * <pre>
 *  创建:  LiangYT 2018/6/7/007 on 14:06
 *  项目: Integration
 *  描述:
 *  更新:
 * <pre>
 */
abstract class StatusViewTitleBarActivity : StatusViewBaseActivity() {

    override fun getContentRetryListener(): View.OnClickListener = View.OnClickListener {
        loadData()
    }

    abstract fun loadData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)
        ActivityUtil.addActivity(this)
    }
}