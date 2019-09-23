package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.R
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_biz_handle_allowance.*

class BmHandleAllowanceActivity : TitleBarBaseActivity(), View.OnClickListener {

    var id = ""

    override fun getLayout(): Int = R.layout.activity_biz_handle_allowance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("高龄补贴")

        id = intent.getStringExtra("id")

        myselfHandleItem.setOnClickListener(this)
        otherHandleItem.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            myselfHandleItem -> {
                IntentUtil.openActivity(this@BmHandleAllowanceActivity, BmHandleAllowanceSubmitActivity::class.java)
                    .putStringExtra("id", id)
                    .finish(true)
                    .start()
            }

            otherHandleItem -> {
                ToastUtil.show("暂未开放")
            }
        }
    }
}
