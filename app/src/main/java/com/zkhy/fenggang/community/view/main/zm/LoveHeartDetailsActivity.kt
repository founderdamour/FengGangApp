package com.zkhy.fenggang.community.view.main.zm

import android.os.Bundle
import android.text.Html
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.comm.plugin.activity.WebPageActivity
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.LoveHeartEntity
import com.zkhy.fenggang.community.presenter.ZMExtPresenter
import com.zkhy.library.mvp.AndroidExt3View
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_love_heart_details.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  预约项目
 *  更新:
 * <pre>
 */
class LoveHeartDetailsActivity : StatusViewBaseActivity(), AndroidExt3View {

    var id: String? = null

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            StatusView.showLoading("记载中...")
            refreshView()
        }
    }

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "爱心筹集详情")

    override fun getContentLayoutId(): Int = R.layout.activity_love_heart_details

    var presenter: ZMExtPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        id = intent.getStringExtra("id")

        presenter = ZMExtPresenter(this)

        StatusView.showLoading("记载中...")
        refreshView()
    }

    private fun refreshView() {
        presenter?.loadLoveHeartDetails(id)
    }

    private fun initView(entity: LoveHeartEntity) {

        titleTv.text = StringUtil.getNotNullValue(entity.proName, "")
        titleTimeTv.text = StringUtil.getNotNullValue(entity.pubTime, "")
        val content = StringUtil.getNotNullValue(entity.content)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            contentTv.text = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
        } else {
            contentTv.text = Html.fromHtml(content)
        }

        fromValueTv.text = StringUtil.getNotNullValue(entity.fromSource)

        showAllBtn.setOnClickListener {
            val url = entity.sourceUrl
            if (StringUtil.isNotEmpty(url) && url.contains("http")) {
                IntentUtil.openActivity(this@LoveHeartDetailsActivity, WebPageActivity::class.java)
                    .putStringExtra("webUrl", url)
                    .putStringExtra("webTitle", StringUtil.getNotNullValue(entity.fromSource, "爱心筹集"))
                    .start()
            } else {
                ToastUtil.show("链接不可用")
            }
        }
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在加载")
    }

    override fun showTip(msg: String?) {
        loadingDismiss()
        ToastUtil.show(msg)
    }

    override fun loadComplete(resultData: Any?) {
        // 获取详情
        val result = resultData as BaseData<LoveHeartEntity>
        if (result.errcode == 0) {
            if (result.data != null) {
                initView(result.data)
                StatusView.showContent()
            } else {
                StatusView.showEmptyData("暂无数据")
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun load3Complete(resultData: Any?) {
    }
}
