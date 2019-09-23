package com.zkhy.community.view.main.lm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.view.status.base.ProgressView
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.StadiumEntity
import com.zkhy.community.presenter.LMPresenter
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_lm_stadium_details.*

class LmStadiumDetailsActivity : StatusViewBaseActivity(), AndroidView {

    private var id: String = ""
    private var title: String = ""

    private var presenter: LMPresenter? = null

    override fun getTitleBarView(): View =
        TitleBarViewCreator.createTitleLC(this, StringUtil.getNotNullValue(title, "文体场所"))

    override fun getContentLayoutId(): Int {
        return R.layout.activity_lm_stadium_details
    }

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            presenter!!.loadStadiumDetails(id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        presenter = LMPresenter(this)
        presenter!!.loadStadiumDetails(id)
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
        val result: BaseData<StadiumEntity> = resultData as BaseData<StadiumEntity>

        if (result.errcode == 0) {
            if (result.data == null) {
                StatusView.showEmptyData("暂无数据")
            } else {
                showView(result.data)
                StatusView.showContent()
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }

    private fun showView(entity: StadiumEntity) {

        nameValueTv.text = StringUtil.getNotNullValue(entity.name)

        statusValueTv.text = if (entity.palceStatus == 0) {
            "正常"
        } else {
            "异常"
        }

        if (!entity.isFreeStatus) {
            payValueTv.text = "免费"

            payInfoLine.visibility = View.GONE
            payInfoItem.visibility = View.GONE
        } else {
            payValueTv.text = "收费"

            payInfoLine.visibility = View.VISIBLE
            payInfoItem.visibility = View.VISIBLE
            payInfoValueTv.text = StringUtil.getNotNullValue(entity.chargeDetail)
        }

        if (entity.cpSprotsList != null) {// 项目
            var sportObj = ""
            for (obj in entity.cpSprotsList) {
                sportObj += obj.name + "，"
            }
            if (sportObj.isNotEmpty()) {
                sportObj = sportObj.substring(0, sportObj.length - 1)
            }
            sportObjTv.text = sportObj
        }

        stadiumAddTv.text = StringUtil.getNotNullValue(entity.addr)

        galleryValueTv.text = StringUtil.getNotNullValue("${entity.gallery}人")
    }
}
