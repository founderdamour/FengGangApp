package com.zkhy.fenggang.community.view.comm

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.presenter.UserPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.CommUtil
import kotlinx.android.synthetic.main.activity_pwd_edit2.*

class PwdEdit2Activity : TitleBarBaseActivity(), AndroidExt2View {
    var phone = ""
    var userId = ""
    var account = ""


    private var presenter: UserPresenter? = null

    override fun getLayout(): Int = R.layout.activity_pwd_edit2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("身份验证")
        phone = intent.getStringExtra("phone")
        userId = intent.getStringExtra("userId")
        account = intent.getStringExtra("account")

        userPhoneNumTv.text = StringUtil.getStarString(phone, 3, 7)

        verificationCodeEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val txt = verificationCodeEt.text.toString()
                nextBtn.isEnabled = StringUtil.isNotEmpty(txt)
            }
        })

        // 获取验证码
        verificationCodeBtn.setOnClickListener {
            initVerificationCode()
        }

        // 提交
        nextBtn.setOnClickListener {

            if (TextUtils.isEmpty(phone) || phone.length != 11 || !CommUtil.checkPhoneNum(phone)) {
                ToastUtil.show("手机号码不正确")
                return@setOnClickListener
            }

            val shortMsgCode = verificationCodeEt.text.toString()
            if (TextUtils.isEmpty(shortMsgCode)) {
                ToastUtil.show("请输入手机验证码")
                return@setOnClickListener
            }

            presenter?.findPwdShortMsgCheck(phone, shortMsgCode)
        }

        presenter = UserPresenter(this)
    }

    // 处理验证码
    private fun initVerificationCode() {
        if (TextUtils.isEmpty(phone) || phone.length != 11 || !CommUtil.checkPhoneNum(phone)) {
            ToastUtil.show("手机号码不正确")
            return
        }
        // 发送获取验证码请求
        presenter!!.resetPwdPhoneCode(phone)
    }

    override fun load2Complete(resultData: Any?) {// 发送后再开始限制点击
        // 控制获取按钮
        MyTimer(60 * 1000, 1000).start()
    }

    inner class MyTimer(millisInFuture: Long, countDownInterval: Long) :

        CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            verificationCodeBtn.isEnabled = false
            verificationCodeBtn.text = "已发送(" + millisUntilFinished / 1000 + ")"
        }

        override fun onFinish() {
            verificationCodeBtn.isEnabled = true
            verificationCodeBtn.text = "重新获取验证码"
        }
    }

    override fun loadComplete(resultData: Any?) {
        IntentUtil.openActivity(this, PwdEdit3Activity::class.java)
            .putStringExtra("userId", userId)
            .putStringExtra("resetType", "2")
            .putStringExtra("account", account)
            .finish(true)
            .start()
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在处理")
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
        loadingDismiss()
    }
}
