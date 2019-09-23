package com.zkhy.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.community.R
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_biz_handle_allowance.*

class BmHandleDisabilityCertificateHomeActivity : TitleBarBaseActivity(), View.OnClickListener {
    var id = ""

    override fun getLayout(): Int = R.layout.activity_bm_handle_disability_certificate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("残疾证办理")

        id = intent.getStringExtra("id")

        myselfHandleItem.setOnClickListener(this)
        otherHandleItem.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            myselfHandleItem -> {
                IntentUtil.openActivity(this@BmHandleDisabilityCertificateHomeActivity, BmHandleDisabilityCertificateSubmitActivity::class.java)
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
