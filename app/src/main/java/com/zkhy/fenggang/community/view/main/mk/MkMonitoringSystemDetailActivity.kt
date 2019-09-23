package com.zkhy.fenggang.community.view.main.mk

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.zkhy.fenggang.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.MkMonitoringSystemDetailEntity
import com.zkhy.fenggang.community.presenter.MkPresenter
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.loading_view_activity.*

/**
 * 煤矿系统详情
 */
class MkMonitoringSystemDetailActivity : LoadingRecyclerViewBaseActivity(), AndroidView {

    private var mkMonitoringSystemDetailAdapter: MkMonitoringSystemDetailAdapter? = null
    private var mkPresenter: MkPresenter? = null
    private var kbh: String = ""

    override fun getLayout(): Int = R.layout.loading_view_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        setTitleBar("检测监管系统详情")
        kbh = intent.getStringExtra("kbh")

        initRecycleLinearView(loadingRecyclerView)
        loadingRecyclerView.emptyView = loadingView

        mkMonitoringSystemDetailAdapter = MkMonitoringSystemDetailAdapter(this, ArrayList())
        loadingRecyclerView.adapter = mkMonitoringSystemDetailAdapter

        mkPresenter = MkPresenter(this)


        refreshData()
    }

    override fun loadData(pageNo: Int) {
        mkPresenter!!.loadMkMonitoringSystemDetail(kbh)
    }

    override fun loadingShow() {
        if (loadType == LoadType.REFRESH) {
            loadingTxtTip.visibility = View.VISIBLE
            loadingTxtTip.text = "正在加载..."
        }
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        if (loadType == LoadType.REFRESH) {
            loadingTxtTip.visibility = View.VISIBLE
            loadingTxtTip.text = msg
        }
    }

    override fun loadComplete(resultData: Any?) {
        stopLoading(loadingRecyclerView, loadType)

        val result: ArrayList<MkMonitoringSystemDetailEntity> = resultData as ArrayList<MkMonitoringSystemDetailEntity>

        if (result.size > 0) {
            loadingRecyclerView.visibility = View.VISIBLE
            loadingTxtTip.visibility = View.GONE
            mkMonitoringSystemDetailAdapter!!.data = result
        } else {
            showTip("暂无数据")
        }

    }
}