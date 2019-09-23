package com.zkhy.fenggang.community.view.community

import android.os.Bundle
import android.text.TextUtils
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.fenggang.comm.util.FormatUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.UserPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_get_verification_code.*

class GetVerificationCodeActivity : TitleBarBaseActivity(), AndroidExt2View {

    private var userPresenter: UserPresenter? = null

    override fun getLayout(): Int {
        return R.layout.activity_get_verification_code
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("验证码")
        init()
    }

    private fun init() {
        userPresenter = UserPresenter(this)
        val userInfo = DataCache.getUserInfo()
        val phone = userInfo.phone
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.show("为获取密保手机号")
            finish()
            return
        }
        tipTv.text = "请发送短信进行身份验证，请输入完整的密保手机号码${FormatUtil.replacePhoneNum(phone)}"

        getVerCodeBtn.setOnClickListener {

            val inputPhone = phoneNumberEt.text.toString()
            if (TextUtils.isEmpty(inputPhone) || inputPhone.length < 11) {
                ToastUtil.show("请输入完整的手机号")
                return@setOnClickListener
            }
            if (phone != inputPhone) {
                ToastUtil.show("与密保手机号不一致，请重新输入")
                return@setOnClickListener
            }

            userPresenter!!.registerCode(inputPhone)
        }
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "获取验证码")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }

    override fun loadComplete(resultData: Any?) {
    }

    override fun load2Complete(resultData: Any?) {

    }
}