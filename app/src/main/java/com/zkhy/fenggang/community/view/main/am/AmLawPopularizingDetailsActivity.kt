package com.zkhy.fenggang.community.view.main.am

import android.os.Bundle
import android.text.Html
import android.view.View
import com.zkhy.fenggang.community.R
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.LawActivityBean
import com.zkhy.fenggang.community.presenter.AMPresenter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_am_law_popularizing_details.*

class AmLawPopularizingDetailsActivity : StatusViewBaseActivity(), AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    var id = ""
    private var presenter: AMPresenter? = null

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "详情")

    override fun getContentLayoutId(): Int {
        return R.layout.activity_am_law_popularizing_details
    }

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            refreshData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)
        id = intent.getStringExtra("id")

        presenter = AMPresenter(this)

        // ================================
        refreshData()
    }

    private fun refreshData() {
        if (StringUtil.isNotEmpty(id)) {
            presenter!!.getLawActivityById(id)
        }
    }

    override fun loadingShow() {
        StatusView.showLoading("正在获取数据...")
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        StatusView.showError(msg)
    }

    override fun loadComplete(resultData: Any?) {
        val result: BaseData<LawActivityBean> = resultData as BaseData<LawActivityBean>

        if (result.errcode == 0) {

            if (result.data == null) {
                showTip("暂无数据")
            } else {
                showView(result.data)
                StatusView.showContent()
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }

    private fun showView(data: LawActivityBean) {
        nameValueTv.text = StringUtil.getNotNullValue(data.name)
        addressValueTv.text = StringUtil.getNotNullValue(data.addr)
        timeValueTv.text = StringUtil.getNotNullValue(data.startTime)
        timeEndValueTv.text = StringUtil.getNotNullValue(data.endTime)

        if (StringUtil.isNotEmpty(data.content)) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                contentTv.text = Html.fromHtml(data.content, Html.FROM_HTML_MODE_LEGACY)
            } else {
                contentTv.text = (Html.fromHtml(data.content))
            }
        } else {
            contentTv.text = "暂无描述"
        }
    }
}
