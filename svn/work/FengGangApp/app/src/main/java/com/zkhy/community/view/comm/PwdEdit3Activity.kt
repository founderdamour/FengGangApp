package com.zkhy.community.view.comm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.community.R
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.UserPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.ActivityUtil
import kotlinx.android.synthetic.main.activity_pwd_edit3.*

class PwdEdit3Activity : TitleBarBaseActivity(), AndroidExt2View {

    var userId = ""
    private var resetType = "" //重置密码方式：1-修改密码 ，2-重置密码
    var account = ""

    private var presenter: UserPresenter? = null

    override fun getLayout(): Int = R.layout.activity_pwd_edit3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("修改密码")

        userId = intent.getStringExtra("userId")
        resetType = intent.getStringExtra("resetType")

        if ("1" == resetType) {
            userOldPwdItem.visibility = View.VISIBLE
        } else {
            userOldPwdItem.visibility = View.GONE
            accountItem.visibility = View.VISIBLE

            account = intent.getStringExtra("account")
            accountId.text = account
        }

        userPwd2Et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val txt1: String = userPwdEt.text.toString()
                val txt2: String = userPwd2Et.text.toString()
                nextBtn.isEnabled = StringUtil.isNotEmpty(txt1) && StringUtil.isNotEmpty(txt2)
            }
        })

        presenter = UserPresenter(this)

        nextBtn.setOnClickListener {

            val commReq = CommReq()
            commReq.userId = userId
            commReq.resetType = resetType

            val userOldPwd = userOldPwdEt.text.toString()
            if ("1" == resetType) {
                if (StringUtil.isEmpty(userOldPwd) || userOldPwd.length < 6 || userOldPwd.length > 18) {
                    ToastUtil.show("请输入原密码")
                    return@setOnClickListener
                }

                commReq.oldPassword = userOldPwd
            }

            if (!NetUtil.isConnected(this)) {
                ToastUtil.show("网络不可用")
                return@setOnClickListener
            }

            val userPwd = userPwdEt.text.toString()
            val userPwd2 = userPwd2Et.text.toString()

            if (StringUtil.isEmpty(userPwd) || userPwd.length < 6 || userPwd.length > 18) {
                ToastUtil.show("密码为6至18个字符")
                return@setOnClickListener
            }

            if (userPwd != userPwd2) {
                ToastUtil.show("两次输入的密码不一致")
                return@setOnClickListener
            }

            if (StringUtil.isEmpty(userId)) {
                ToastUtil.show("用户信息异常")
                return@setOnClickListener
            }

            commReq.newPassword = userPwd
            commReq.confirmPassword = userPwd2
            presenter?.changeUserPwd(commReq)//userId, userPwd, userPwd2
        }
    }

    override fun loadComplete(resultData: Any?) {
        if (resetType == "1") {
            // 修改密码
            ActivityUtil.finishAll() // 关闭程序
            DataCache.setAutoLogin(false) // 取消自动登录
            // 重新登录
            IntentUtil.openActivity(this, LoginActivity::class.java).start()
        } else {
            finish()
        }
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

    override fun load2Complete(resultData: Any?) {
    }
}
