package com.zkhy.fenggang.community.view.community

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.zkhy.fenggang.community.R
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_status_view_list.*

class PersonalInfoActivity : TitleBarBaseActivity(), AndroidView {
    override fun getLayout(): Int {
        return R.layout.activity_personal_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("人员信息")
        init()
    }

    fun init() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

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