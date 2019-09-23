package com.zkhy.fenggang.community.view.comm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.WmUser
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : TitleBarBaseActivity(), View.OnClickListener {

    var user: WmUser? = null

    override fun getLayout(): Int = R.layout.activity_setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("设置")

        initView()
        setClickListener()
    }

    private fun initView() {
        user = DataCache.getUserInfo()
        if (user != null) {
            phoneNumValueTv.text = StringUtil.getNotNullValue(user!!.phone)
        }
    }

    private fun setClickListener() {
        phoneNumItem.setOnClickListener(this)
        safeItem.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            phoneNumItem -> {
            }

            safeItem -> {
                IntentUtil.openActivity(this, PwdEdit3Activity::class.java)
                    .putStringExtra("userId", user?.userId)
                    .putStringExtra("resetType", "1")
                    .finish(true)
                    .start()
            }
        }
    }
}
