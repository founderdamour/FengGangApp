package com.zkhy.fenggang.community.view.comm

import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.bean.RegisterEntity
import com.zkhy.fenggang.community.presenter.UserPresenter
import com.zkhy.fenggang.community.widget.LoadingDialog
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.CommUtil
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : TitleBarBaseActivity(), AndroidExt2View {

    override fun getLayout(): Int = R.layout.activity_register

    private var presenter: UserPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("注册")

        presenter = UserPresenter(this)

        // 提交
        registerBtn.setOnClickListener {
            doRegister()
        }

        // 登录
        finishBtn.setOnClickListener {
            finish()
        }

        // 免责声明
        agreeBtn.setOnClickListener {
            finish()
        }

        // 获取验证码
        verificationCodeBtn.setOnClickListener {
            initVerificationCode()
        }
    }

    private fun doRegister() {
        val account = userNameEt.text.toString()
        if (StringUtil.isEmpty(account) || account.length < 2 || account.length > 30) {
            ToastUtil.show("请正确填写账号信息")
            return
        }

        val userIDCard = userIDCardEt.text.toString()
        if (StringUtil.isEmpty(userIDCard)) {
            ToastUtil.show("请正确填写身份证号")
            return
        }

        val userPwd = userPwdEt.text.toString()
        val userPwd2 = userPwd2Et.text.toString()

        if (StringUtil.isEmpty(userPwd) || userPwd.length < 6 || userPwd.length > 18) {
            ToastUtil.show("密码为6至18个字符")
            return
        }

        if (userPwd != userPwd2) {
            ToastUtil.show("密码输入不一致")
            return
        }

        val userPhone = userPhoneEt.text.toString().trim()
        if (StringUtil.isEmpty(userPhone) || userPhone.length != 11 || !CommUtil.checkPhoneNum(userPhone)) {
            ToastUtil.show("手机号填写错误")
            return
        }
        val verificationCode = verificationCodeEt.text.toString()
        if (StringUtil.isEmpty(verificationCode)) {
            ToastUtil.show("验证码不能为空")
            return
        }

        val dto = CommReq()
        dto.isRegisterIs = true
        dto.account = account
        dto.password = userPwd
        dto.phone = userPhone
        dto.captcha = verificationCode
        dto.idcard = userIDCard

        presenter!!.registerUser(dto)
    }

    override fun loadComplete(result: Any) {
        loadingDismiss()

        val baseData = result as BaseData<RegisterEntity>

        if (baseData.errcode != 0) {
            showTip(baseData.errmsg)
        } else {
            showTip("注册成功")
            finish()
        }
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "",false)
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
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

    // 处理验证码
    private fun initVerificationCode() {
        val userPhone = userPhoneEt.text.toString().trim()
        if (TextUtils.isEmpty(userPhone) || userPhone.length != 11 || !CommUtil.checkPhoneNum(userPhone)) {
            ToastUtil.show("手机号码不正确")
            return
        }
        // 发送获取验证码请求
        presenter!!.registerCode(userPhone)
    }

    override fun load2Complete(resultData: Any?) {// 发送后再开始限制点击
        // 控制获取按钮
        MyTimer(60 * 1000, 1000).start()
    }
}
