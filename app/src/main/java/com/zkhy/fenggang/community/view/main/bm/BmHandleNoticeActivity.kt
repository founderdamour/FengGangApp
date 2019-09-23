package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import com.zkhy.fenggang.community.R
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_bm_handle_notice.*

abstract class BmHandleNoticeActivity : TitleBarBaseActivity() {

    override fun getLayout(): Int = R.layout.activity_bm_handle_notice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("办理须知")

        noticeContentTv.text = getNoticeContent()
    }

    abstract fun getNoticeContent(): String
}
