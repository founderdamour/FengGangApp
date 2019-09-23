package com.zkhy.fenggang.community.view.main.mk

import android.os.Bundle
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.base.LoadingRecyclerViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.MkMonitoringSystemEntity
import com.zkhy.fenggang.community.presenter.MkPresenter
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.loading_view_activity.*

/**
 * 煤矿系统矿点列表 （未用）
 */
class MkMonitoringSystemListActivity : LoadingRecyclerViewBaseActivity(), AndroidView {

    private var mkMonitoringSystemListAdapter: MkMonitoringSystemListAdapter? = null
    private var mkPresenter: MkPresenter? = null

    override fun getLayout(): Int = R.layout.loading_view_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        setTitleBar("检测监管系统")

        initRecycleLinearView(loadingRecyclerView)
        loadingRecyclerView.emptyView = loadingView

        mkMonitoringSystemListAdapter = MkMonitoringSystemListAdapter(this, ArrayList())
        loadingRecyclerView.adapter = mkMonitoringSystemListAdapter

        mkMonitoringSystemListAdapter!!.setClickCallBack(ItemClickCallBack<MkMonitoringSystemEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this, MkMonitoringSystem2ListActivity::class.java)
                    .putStringExtra("kbh", entity.kbh)
                    .start()
        })

        mkPresenter = MkPresenter(this)


        refreshData()
    }

    override fun loadData(pageNo: Int) {
        mkPresenter!!.loadMkMonitoringSystemList("1")
    }

    override fun loadingShow() {
        if (loadType == LoadType.REFRESH) {
            loadingTxtTip.text = "正在加载..."
        }
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        if (loadType == LoadType.REFRESH) {
            loadingTxtTip.text = msg
        }
    }

    override fun loadComplete(resultData: Any?) {
        stopLoading(loadingRecyclerView, loadType)

        val result: ArrayList<MkMonitoringSystemEntity> = resultData as ArrayList<MkMonitoringSystemEntity>

        if (result.size > 0) {
            mkMonitoringSystemListAdapter!!.data = result
        } else {
            showTip("暂无数据")
        }

    }
}