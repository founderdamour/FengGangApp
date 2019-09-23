package com.zkhy.fenggang.community.view.main.dj

import android.os.Bundle
import com.zkhy.fenggang.community.R
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
