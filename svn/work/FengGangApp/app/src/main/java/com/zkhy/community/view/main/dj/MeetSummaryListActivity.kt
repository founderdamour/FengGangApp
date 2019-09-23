package com.zkhy.community.view.main.dj

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.community.R
import com.zkhy.comm.base.StatusViewRecycleViewBaseActivity
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.api.PageData
import com.zkhy.community.model.api.PageReq
import com.zkhy.community.model.bean.ThreeMeetEntity
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.MeetClassPresenter
import com.zkhy.community.view.main.dj.adapters.MeetClassListAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_meet_class_list.*

class MeetSummaryListActivity : StatusViewRecycleViewBaseActivity(), AndroidExt2View {

    private var adapter: MeetClassListAdapter? = null
    private var pageNo: Int = 0

    private var presenter: MeetClassPresenter? = null

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "会议纪要")

    override fun getContentLayoutId(): Int = R.layout.activity_meet_class_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecycleLinearView(loadingRecyclerView)

        adapter = MeetClassListAdapter(this, ArrayList(), false)
        loadingRecyclerView.adapter = adapter
        adapter!!.setClickCallBack(ItemClickCallBack { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this@MeetSummaryListActivity, MeetingDetailsActivity::class.java)
                .putStringExtra("id", entity.id.toString())
                .start()
        })

        presenter = MeetClassPresenter(this)

        // ================================
        refreshData()
    }

    override fun refreshData() {
        loadType = LoadType.REFRESH
        loadData(1)
    }

    private fun loadData(pageNo: Int) {
        if (!NetUtil.isAvailable(this)) {
            ToastUtil.show(R.string.net_error)
            return
        }

        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20

        val comm = CommReq()
        comm.meetStatus = 5
        comm.orgId = -1
        comm.userId = DataCache.getUserId()
        comm.joinStatus = -1

        pageParam.data = comm
        presenter!!.loadMeetForMeList(pageParam)
    }

    override fun loadMoreData() {
        loadType = LoadType.LOAD_MORE
        loadData(pageNo)
    }

    override fun loadingShow() {
        if (loadType == LoadType.REFRESH) {
            StatusView.showLoading("正在获取数据...")
        }
    }

    override fun loadingDismiss() {
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

    override fun loadComplete(resultData: Any?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        val result: BaseData<PageData<ThreeMeetEntity>> = resultData as BaseData<PageData<ThreeMeetEntity>>

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                // 页
                val pageData = result.data
                // 数据
                val listData = pageData.list as ArrayList<ThreeMeetEntity>

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

}
