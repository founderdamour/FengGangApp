package com.zkhy.community.view.comm

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sinothk.comm.utils.*
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.permission.PermissionManager
import com.sinothk.permission.PermissionResultListener
import com.sinothk.permission.lib.PermissionsUtil
import com.zkhy.community.MainActivity
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.TerminalLoginDTO
import com.zkhy.community.model.bean.WmUser
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.UserPresenter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.ActivityUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AndroidExt2View {

    private var presenter: UserPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        StatusBarUtil.transparencyBar(this)

        initView()

        presenter = UserPresenter(this)

        loginBtn.setOnClickListener {
            requestAppPermission()
        }

        registerBtn.setOnClickListener {
            IntentUtil.openActivity(this, RegisterActivity::class.java).start()
        }

        pwdEditBtn.setOnClickListener {
            IntentUtil.openActivity(this, PwdEdit1Activity::class.java).start()
        }
    }

    private fun initView() {
        val accountStr = DataCache.getUserAccount()
        userNameEt.setText(accountStr)
        ViewUtil.focusMoveToEnd(userNameEt)
    }

    private fun requestAppPermission() {
        val tip = PermissionsUtil.TipInfo("授权提示", "获取必须的手机权限不全！", "放弃使用", "授权")

        if (PermissionManager.haveAllPermission(this, permissions)) {
            readyLogin()
        } else {
            PermissionManager.requestAllPermission(this, object : PermissionResultListener {
                override fun permissionFail(p0: Array<out String>?) {
                    ToastUtil.show("放弃使用")
                    finish()
                }

                override fun permissionSuccess(p0: Array<out String>?) {
                    requestAppPermission()
                }
            }, permissions, true, tip)
        }
    }

    private fun readyLogin() {

        val account = userNameEt.text.toString()
        val userPwd = userPwdEt.text.toString()

        if (!NetUtil.isAvailable(this)) {
            ToastUtil.show(resources.getString(R.string.net_error))
            return
    }

        if (StringUtil.isEmpty(account)) {
            ToastUtil.show("账号信息填写不完整")
            return
        }

        if (StringUtil.isEmpty(userPwd)) {
            ToastUtil.show("密码信息填写不完整")
            return
        }

        presenter?.login(account, userPwd)
    }

    override fun loadComplete(result: Any?) {
        val baseData = result as BaseData<TerminalLoginDTO>
        if (baseData.errcode != 0) {
            showTip(baseData.errmsg)
        } else {
            DataCache.saveUserToken(baseData.data)

            if (baseData.data.user != null && baseData.data.user.id > 0) {
                presenter?.loadingUserInfo()
            } else {
                ToastUtil.show("用户信息有误")
            }
        }
    }

    override fun load2Complete(resultData: Any?) {
        val baseData = resultData as BaseData<WmUser>
        if (baseData.errcode != 0) {
            showTip(baseData.errmsg)
        } else {
            DataCache.saveLoginUserInfo(baseData.data)

            DataCache.setAutoLogin(true)

            ActivityUtil.finishAll()

            IntentUtil.openActivity(this@LoginActivity, MainActivity::class.java).finish(true)
                .putIntExtra(MainActivity.FROM_TYPE, MainActivity.FROM_LOGIN)
                .start()
        }
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在登录")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }

    private val permissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.CAMERA,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
    )
}
