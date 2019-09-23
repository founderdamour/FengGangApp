package com.zkhy.fenggang.community.view.main.mk

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.MkMonitoringSystem2Entity
import com.zkhy.fenggang.community.presenter.MkPresenter
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.loading_view_activity.*

/**
 *
 */
class MkPersonLocationListActivity : LoadingRecyclerViewBaseActivity(), AndroidView {

    private var mkPersonLocationListAdapter: MkPersonLocationListAdapter? = null
    private var mkPresenter: MkPresenter? = null

    override fun getLayout(): Int = R.layout.loading_view_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        setTitleBar("人员定位系统", "井下人员大屏") {
            IntentUtil.openActivity(this, MkPersonShowActivity::class.java)
                    .putStringExtra("kbh", "1")
                    .start()
        }

        initRecycleLinearView(loadingRecyclerView)
        loadingRecyclerView.emptyView = loadingView

        mkPersonLocationListAdapter = MkPersonLocationListAdapter(this, ArrayList())
        loadingRecyclerView.adapter = mkPersonLocationListAdapter

        mkPersonLocationListAdapter!!.setClickCallBack(ItemClickCallBack<MkMonitoringSystem2Entity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this, MkPersonLocationDetailActivity::class.java)
                    .putStringExtra("kbh", entity.kbh)
                    .start()
        })

        mkPresenter = MkPresenter(this)


        refreshData()
    }

    override fun loadData(pageNo: Int) {
        mkPresenter!!.loadMkPersonLocationList("1")
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

        val result: ArrayList<MkMonitoringSystem2Entity> = resultData as ArrayList<MkMonitoringSystem2Entity>

        if (result.size > 0) {
            loadingRecyclerView.visibility = View.VISIBLE
            loadingTxtTip.visibility = View.GONE
            mkPersonLocationListAdapter!!.data = result
        } else {
            showTip("暂无数据")
        }

    }
}