package com.zkhy.community.view.main.lm

import android.os.Bundle
import com.sinothk.comm.utils.StringUtil
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.NearbyActiveEntity
import com.zkhy.community.presenter.LMPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_lm_nearby_active_details.*

class LmNearbyActiveDetailsActivity : TitleBarBaseActivity(), AndroidView {

    private var id = ""
    private var presenter: LMPresenter? = null

    override fun getLayout(): Int = R.layout.activity_lm_nearby_active_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("活动详情")
        id = intent.getStringExtra("id")
        presenter = LMPresenter(this)
        presenter!!.loadNearbyActiveDetails(id)
    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(resultData: Any?) {

        val result: BaseData<NearbyActiveEntity> = resultData as BaseData<NearbyActiveEntity>

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

    private fun showView(data: NearbyActiveEntity) {
        ImageLoader.show(this, data.aroundImg, imgIv)

        nameValueTv.text = StringUtil.getNotNullValue(data.name)
        contentTv.text = StringUtil.getNotNullValue(data.content)

        startTimeValueTv.text = StringUtil.getNotNullValue(data.startTime)
        endTimeValueTv.text = StringUtil.getNotNullValue(data.endTime)

        organizerValueTv.text = StringUtil.getNotNullValue(data.organizer)
        addressTv.text = StringUtil.getNotNullValue(data.addr)
    }
}
