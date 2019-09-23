package com.zkhy.community

import android.app.Application
import com.sinothk.comm.utils.AppUtil
import com.sinothk.comm.utils.LogUtil
import com.sinothk.comm.utils.PreferUtil
import com.sinothk.comm.utils.ToastUtil

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initUtils()

    }

    private fun initUtils() {
        ToastUtil.init(this)
        PreferUtil.init(this)
        AppUtil.init(this)
        LogUtil.init(this, true)
    }
}