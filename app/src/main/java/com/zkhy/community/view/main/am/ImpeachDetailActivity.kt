package com.zkhy.community.view.main.am

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.DateUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.community.R
import com.zkhy.community.model.bean.ImpeachEntity
import com.zkhy.community.presenter.AMPresenter
import com.zkhy.library.base.activity.StatusViewTitleBarActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_am_impeach_detail.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/5/9 on 11:09
 *  项目:  WuMinAndroid
 *  描述:  网上信访
 *  更新:
 * <pre>
 */
class ImpeachDetailActivity : StatusViewTitleBarActivity(), AndroidExt2View {
    var id = ""
    var presenter: AMPresenter? = null

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "信访详情")

    override fun getContentLayoutId(): Int = R.layout.activity_am_impeach_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getStringExtra("id")

        presenter = AMPresenter(this)

        loadData()
    }

    override fun loadData() {
        StatusView.showLoading()
        presenter?.loadImpeach(id)
    }

    private fun initView(data: ImpeachEntity) {
        userNameValueEt.text = StringUtil.getNotNullValue(data.name)
        userIDValueEt.text = StringUtil.getNotNullValue(data.idcard, "暂无")
        userPhoneValueEt.text = StringUtil.getNotNullValue(data.phone, "暂无")

        userAddressValueTv.text = StringUtil.getNotNullValue(data.addr, "暂无")
        titleValueTv.text = StringUtil.getNotNullValue(data.title, "暂无")
        contentValueTv.text = StringUtil.getNotNullValue(data.content, "暂无")

        when (data.status) { //untreated=未处理 complete = 已处理
            "untreated" -> {
                checkValueTv.text = "未处理"
                checkValueTv.setTextColor(resources.getColor(R.color.status_doing))

                dealContentItem.visibility = View.GONE
            }
            "complete" -> {
                checkValueTv.text = "已处理"
                checkValueTv.setTextColor(resources.getColor(R.color.status_success))

                dealContentItem.visibility = View.VISIBLE

                dealContentValueTv.text = StringUtil.getNotNullValue(data.dealContent)

                if (data.dealTime == null) {
                    dealContentTimeTv.text = "无处理时间"
                } else {
                    dealContentTimeTv.text = DateUtil.getDateStrByDate(data.dealTime, "yyyy-MM-dd")
                }
            }
            else -> {
                checkValueTv.text = "未定义"
                dealContentItem.visibility = View.GONE
            }
        }
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun loadComplete(resultData: Any?) {
        if (resultData != null) {
            val data: ImpeachEntity = resultData as ImpeachEntity
            StatusView.showContent()
            initView(data)
        }
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在提交")
    }

    override fun showTip(msg: String?) {
        StatusView.showError(msg)
    }
}