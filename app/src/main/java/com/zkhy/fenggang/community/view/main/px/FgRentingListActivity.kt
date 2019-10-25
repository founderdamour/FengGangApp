package com.zkhy.fenggang.community.view.main.px

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.comm.base.StatusViewRecycleViewBaseActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.FgRentingEntity
import com.zkhy.fenggang.community.presenter.PXPresenter
import com.zkhy.fenggang.community.view.main.px.adapter.FgRentingListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_fg_renting_list.*

/**
 * 房屋租赁列表
 */
class FgRentingListActivity : StatusViewRecycleViewBaseActivity(), AndroidExt2View {

    private var presenter: PXPresenter? = null
    private var adapter: FgRentingListAdapter? = null
    private var pageNo: Int = 0

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "房屋租赁")

    override fun getContentLayoutId(): Int = R.layout.activity_fg_renting_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)
        initRecycleLinearView(loadingRecyclerView)

        adapter = FgRentingListAdapter(ArrayList())
        loadingRecyclerView.adapter = adapter

        adapter!!.setClickCallBack(ItemClickCallBack<FgRentingEntity> { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this@FgRentingListActivity, FgRentingDetailActivity::class.java)
                    .putSerializableExtra("entity", entity)
                    .start()
        })
        presenter = PXPresenter(this)

        refreshData()
    }

    override fun refreshData() {
        loadType = LoadType.REFRESH
        loadData(1)
    }

    private fun loadData(pageNo: Int) {
        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20

        val commReq = CommReq()
        commReq.publisState = 1
        pageParam.data = commReq

        presenter!!.loadFgRentingList(pageParam)
    }

    override fun loadMoreData() {
        loadType = LoadType.LOAD_MORE
        loadData(pageNo)
    }

    override fun loadingDismiss() {
    }

    override fun loadComplete(resultData: Any?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        val result: BaseData<PageData<FgRentingEntity>> = resultData as BaseData<PageData<FgRentingEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<FgRentingEntity>

                if (loadType == LoadType.REFRESH) {
                    adapter?.setData(listData)
                } else {
                    adapter?.addData(listData)
                }
                StatusView.showContent()

                // 设置：通用
                pageNo = pageData.nextPage
                if (pageData.isHasNextPage) {
                    loadingRecyclerView.setLoadingMoreEnabled(true)
                } else {
                    loadingRecyclerView.setLoadingMoreEnabled(false)
                }
            }
        } else {
            showTip(result.errmsg)
        }
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun loadingShow() {
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)
        if (loadType == LoadType.REFRESH) {
            StatusView.showError(msg)
        } else {
            ToastUtil.show(msg)
        }
    }
}