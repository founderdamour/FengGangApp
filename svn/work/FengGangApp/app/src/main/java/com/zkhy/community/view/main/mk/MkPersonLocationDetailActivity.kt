package com.zkhy.community.view.main.mk

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.community.R
import com.zkhy.community.model.bean.MkMonitoringSystem2Entity
import com.zkhy.community.model.bean.MkMonitoringSystemEntity
import com.zkhy.community.model.bean.MkPersonLocationDetailEntity
import com.zkhy.community.presenter.MkPresenter
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.loading_view_activity.*

/**
 * 人员定位系统详情
 */
class MkPersonLocationDetailActivity : LoadingRecyclerViewBaseActivity(), AndroidView {

    private var mkPersonLocationDetailAdapter: MkPersonLocationDetailAdapter? = null
    private var mkPresenter: MkPresenter? = null
    private var kbh: String = ""

    override fun getLayout(): Int = R.layout.loading_view_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        kbh = intent.getStringExtra("kbh")

        setTitleBar("人员定位系统详情")
        initRecycleLinearView(loadingRecyclerView)
        loadingRecyclerView.emptyView = loadingView

        mkPersonLocationDetailAdapter = MkPersonLocationDetailAdapter(this, ArrayList())
        loadingRecyclerView.adapter = mkPersonLocationDetailAdapter

        mkPresenter = MkPresenter(this)


        refreshData()
    }

    override fun loadData(pageNo: Int) {
        mkPresenter!!.loadMkPersonLocationDetail(kbh)
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

        val result: ArrayList<MkPersonLocationDetailEntity> = resultData as ArrayList<MkPersonLocationDetailEntity>

        if (result.size > 0) {
            loadingRecyclerView.visibility = View.VISIBLE
            loadingTxtTip.visibility = View.GONE
            mkPersonLocationDetailAdapter!!.data = result
        } else {
            showTip("暂无数据")
        }

    }
}