package com.zkhy.fenggang.community.view.main.dj

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.view.status.statusViews.StatusView
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.ThreeMeetEntity
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.MeetClassPresenter
import com.zkhy.fenggang.community.view.main.dj.adapters.MeetClassListAdapter
import com.zkhy.library.base.activity.TitleBarWhitRecycleViewBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_meet_class_home.*

class MeetClassHomeActivity : TitleBarWhitRecycleViewBaseActivity(), View.OnClickListener, AndroidExt2View {
    private var pageNo: Int = 1
    private var adapter: MeetClassListAdapter? = null
    private var presenter: MeetClassPresenter? = null

//    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLCR(this, "三会一课", "更多") {
//        IntentUtil.openActivity(this@MeetClassHomeActivity, MeetClassFilterActivity::class.java)
//            .start()//MeetClassForAllActivity
//        //
//    }
//
//    override fun getContentLayoutId(): Int = R.layout.activity_meet_class_home

    override fun getLayout(): Int = R.layout.activity_meet_class_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitleBar("三会一课", "更多") {
            IntentUtil.openActivity(this@MeetClassHomeActivity, MeetClassFilterActivity::class.java)
                .start()//MeetClassForAllActivity
        }

        initRecycleLinearView(loadingRecyclerView)
        loadingRecyclerView.emptyView = emptyView

        adapter = MeetClassListAdapter(this, ArrayList(), false)
        loadingRecyclerView.adapter = adapter
        adapter!!.setClickCallBack(ItemClickCallBack { _, entity ->
            if (entity == null) {
                return@ItemClickCallBack
            }

            IntentUtil.openActivity(this@MeetClassHomeActivity, MeetingDetailsActivity::class.java)
                .putStringExtra("id", entity.id.toString())
                .start()
        })

        presenter = MeetClassPresenter(this)

        // ================================
        setListener()
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun setListener() {
        // 我的会议
        myMeetItem.setOnClickListener(this)
        meetRecordItem.setOnClickListener(this)
    }

    private fun loadData(pageNo: Int) {

        if (!NetUtil.isAvailable(this)) {
            ToastUtil.show(R.string.net_error)
            return
        }

        val userId: String = DataCache.getUserId()

        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = pageNo
        pageParam.pageSize = 20

        val comm = CommReq()
        comm.meetStatus = -1
        comm.orgId = -1
        comm.userId = userId
        comm.joinStatus = -1

        pageParam.data = comm
        presenter!!.loadMeetForAllList(pageParam)
    }

    override fun refreshData() {
        loadType = LoadType.REFRESH
        loadData(1)
    }

    override fun loadMoreData() {
        loadType = LoadType.LOAD_MORE
        loadData(pageNo)
    }

    override fun onClick(v: View?) {
        when (v) {
            myMeetItem -> { // 我的会议
                IntentUtil.openActivity(this@MeetClassHomeActivity, MeetClassForMeActivity::class.java).start()
            }

            meetRecordItem -> { // 会议纪要
                IntentUtil.openActivity(this@MeetClassHomeActivity, MeetSummaryListActivity::class.java).start()
            }
        }
    }

    override fun loadingDismiss() {

    }

    override fun loadComplete(resultData: Any?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)

        val result: BaseData<PageData<ThreeMeetEntity>> = resultData as BaseData<PageData<ThreeMeetEntity>>
        StatusView.showContent()

        if (result.errcode == 0) {
            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
//
                if (loadType == LoadType.REFRESH) {
                    adapter?.setData(ArrayList())
                } else {
                    showTip("暂无数据")
                }
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
//        if (loadType == LoadType.REFRESH) {
//            StatusView.showLoading("正在获取数据...")
//        }
    }

    override fun showTip(msg: String?) {
        // 设置：通用
        stopLoading(loadingRecyclerView, loadType)
//        if (loadType == LoadType.REFRESH) {
//            StatusView.showError(msg)
//        } else {
        ToastUtil.show(msg)
//        }
    }
}
