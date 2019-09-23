package com.zkhy.community.view.comm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.community.R
import com.zkhy.community.model.bean.WmUser
import com.zkhy.community.presenter.UserPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_pwd_edit1.*

class PwdEdit1Activity : TitleBarBaseActivity(), AndroidExt2View {
    private var presenter: UserPresenter? = null

    override fun getLayout(): Int = R.layout.activity_pwd_edit1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("找回账号及密码")

        presenter = UserPresenter(this)

        nextBtn.setOnClickListener {

            val account = userNameEt.text.toString()
            if (StringUtil.isEmpty(account)) {
                ToastUtil.show("请输入身份证号码")
                return@setOnClickListener
            }

            if (!NetUtil.isConnected(this)) {
                ToastUtil.show("网络不可用")
                return@setOnClickListener
            }

            presenter!!.getUserPhoneByAccount(account)
        }

        userNameEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val txt = userNameEt.text.toString()
                nextBtn.isEnabled = StringUtil.isNotEmpty(txt)
            }
        })
    }

    override fun loadComplete(resultData: Any?) {
        val wmUser: WmUser = resultData as WmUser
        if (StringUtil.isNotEmpty(wmUser.phone)) {

            IntentUtil.openActivity(this@PwdEdit1Activity, PwdEdit2Activity::class.java)
                .putStringExtra("userId", StringUtil.getNotNullValue(wmUser.userId))
                .putStringExtra("phone", StringUtil.getNotNullValue(wmUser.phone))
                .putStringExtra("account", StringUtil.getNotNullValue(wmUser.account))
                .finish(true)
                .start()

        } else {
            showTip("此用户无手机号")
        }
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在读取")
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }
}
