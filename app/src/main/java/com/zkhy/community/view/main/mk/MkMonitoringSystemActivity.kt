package com.zkhy.community.view.main.mk

import android.os.Bundle
import com.sinothk.comm.utils.IntentUtil
import com.zkhy.community.R
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_mk_monitoring_system.*

/**
 * 煤矿监控系统
 */
class MkMonitoringSystemActivity : TitleBarBaseActivity() {

    override fun getLayout(): Int = R.layout.activity_mk_monitoring_system

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("煤矿监管")
        setListener()
    }

    private fun setListener() {
        monitoringSystemItem.setOnClickListener {
            IntentUtil.openActivity(this, MkMonitoringSystem2ListActivity::class.java)
                    .start()
        }

        personLocationItem.setOnClickListener {
            IntentUtil.openActivity(this, MkPersonLocationListActivity::class.java)
                    .start()
        }
    }
}