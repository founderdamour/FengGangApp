package com.zkhy.fenggang.community.view.community

import android.os.Bundle
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.view.custom.VerificationCodeView
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_input_verification_code.*

class InputVerificationCodeActivity : TitleBarBaseActivity(), AndroidExt2View {
    override fun getLayout(): Int {
        return R.layout.activity_input_verification_code
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("输入验证码")
        verCodeView.setInputCompleteListener(object : VerificationCodeView.InputCompleteListener {
            override fun inputComplete() {
                submitVerCodeBtn.isEnabled = true
            }

            override fun deleteContent() {
                submitVerCodeBtn.isEnabled = false
            }
        })

        submitVerCodeBtn.setOnClickListener {
            ToastUtil.show(verCodeView.inputContent)
        }
    }

    override fun loadingDismiss() {
    }

    override fun loadComplete(resultData: Any?) {
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun loadingShow() {
    }

    override fun showTip(msg: String?) {
    }
}