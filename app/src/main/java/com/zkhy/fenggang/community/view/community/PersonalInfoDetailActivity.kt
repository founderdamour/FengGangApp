package com.zkhy.fenggang.community.view.community

import android.os.Bundle
import com.zkhy.fenggang.community.R
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidView

class PersonalInfoDetailActivity : TitleBarBaseActivity(), AndroidView {
    override fun getLayout(): Int {
        return R.layout.activity_personal_info_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("人员信息详情")
    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(resultData: Any?) {
    }
}