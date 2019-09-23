package com.zkhy.fenggang.community.view.main.am

import android.os.Bundle
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.AMPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_am_law_consult_add.*

/**
 * 发起咨询
 */
class AmLawConsultAddActivity : TitleBarBaseActivity(), AndroidExt2View{
    override fun load2Complete(resultData: Any?) {
    }

    private var presenter: AMPresenter? = null
    override fun getLayout(): Int = R.layout.activity_am_law_consult_add

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setTitleBar("我要咨询", "历史咨询") {
//            IntentUtil.openActivity(this@AmLawConsultAddActivity, AmLawConsultHistoryActivity::class.java).start()
//        }

        setTitleBar("我要咨询")

        initView()
        submitBtn.setOnClickListener {
            submit()
        }
        presenter = AMPresenter(this)
    }

    private fun initView() {
        val currUser = DataCache.getUserInfo()
        nameValueTv.text = StringUtil.getNotNullValue(currUser.name, "当前用户")
        phoneValueTv.text = StringUtil.getNotNullValue(currUser.phone, "暂无")
        StringUtilExt.setEditTextInhibitInputSpaChat(contentTv)
    }

    private fun submit() {
        val content: String = contentTv.text.toString()

        if (!NetUtil.isAvailable(this)) {
            ToastUtil.show(R.string.net_error)
            return
        }

        if (StringUtil.isEmpty(content)) {
            ToastUtil.show("请输入问题内容 ...")
            return
        }

        if (content.length > 1000) {
            ToastUtil.show("问题内容字数应在1000字符以内...")
            return
        }

        presenter?.addMyLawConsult(content)
    }

    override fun loadComplete(result: Any?) {
        val baseData = result as BaseData<Boolean>
        if (baseData.errcode == 0) {
            if (baseData.data) {
                showTip("新增成功")
                finish()
            } else {
                showTip(baseData.errmsg)
            }
        } else {
            showTip(baseData.errmsg)
        }
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在提交")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }
}
