package com.zkhy.community.view.main.km

import android.os.Bundle
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.KMPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_house_doc_q_a_add.*
import android.text.Editable
import android.text.TextWatcher
import com.sinothk.comm.utils.NetUtil


/**
 * 发起咨询
 */
class HouseDocQAAddActivity : TitleBarBaseActivity(), AndroidExt2View {

    override fun load2Complete(resultData: Any?) {
    }

    private var presenter: KMPresenter? = null
    override fun getLayout(): Int = R.layout.activity_house_doc_q_a_add

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setTitleBar("我要咨询", "历史咨询") {
//            IntentUtil.openActivity(this@HouseDocQAAddActivity, HouseDocQAHistoryActivity::class.java).start()
//        }
        setTitleBar("我要咨询")

        initView()
        submitBtn.setOnClickListener {
            submit()
        }
        presenter = KMPresenter(this)
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

        presenter?.addMyDocConsult(content)
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
