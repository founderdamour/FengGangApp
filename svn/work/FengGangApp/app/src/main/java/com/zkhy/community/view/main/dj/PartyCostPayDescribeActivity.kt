package com.zkhy.community.view.main.dj

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.community.R
import com.zkhy.community.model.bean.PartyPayInfoEntity
import com.zkhy.library.base.activity.TitleBarBaseActivity

/**
 * 党费缴纳主页
 */
class PartyCostPayDescribeActivity : TitleBarBaseActivity() {

    override fun getLayout(): Int = R.layout.activity_party_cost_pay_describe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("党费介绍")
    }
}
